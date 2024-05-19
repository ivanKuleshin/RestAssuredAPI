package com.erat.RestAssuredAPI.utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import io.restassured.http.*;
import lombok.extern.slf4j.Slf4j;

import com.google.gson.Gson;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;

@Slf4j
public final class RestClient extends BaseTest {

    private static final String BASE_URL = properties.getProperty("basePayPalURI");
    private String authorizationValue;
    private RequestSpecification requestSpecification;
    private final AtomicInteger gettingNewTokenCounter = new AtomicInteger(5);
    private static final String QUESTION_REGEXP = "\\?";
    private static final String QUESTION_CHAR = "?";
    private static final String AUTHORIZATION = "Authorization";

//    public RestClient() {
//        RestAssured.defaultParser = Parser.JSON;
//        updateHeaders();
//    }

    private Response sendRequestForHttpMethod(RequestTypes httpMethod, String url, RequestSpecification requestSpecification) {
        Response response;
        switch (httpMethod) {
            case GET:
                response = requestSpecification.get(url);
                log.info("[GET] request was sent for URI: {}", url);
                break;
            case POST:
                response = requestSpecification.post(url);
                log.info("[POST] request was sent for URI: {}", url);
                break;
            case PUT:
                response = requestSpecification.put(url);
                log.info("[PUT] request was sent for URI: {}", baseURI + basePath + url);
                break;
            case DELETE:
                response = requestSpecification.delete(url);
                log.info("[DELETE] request was sent for URI: {}", baseURI + basePath + url);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + httpMethod);
        }

        log.info("Response: {}", response.asPrettyString());
        return response;
    }
    public Response sendRequestWithSpecifications(RequestSpecification requestSpecification, RequestTypes httpMethod, String uri){
        return sendRequestForHttpMethod(httpMethod, uri, requestSpecification);
    }

    public Response sendRequestWithAuthorization(RequestTypes httpMethod, String uri) {
        return sendRequestWithAuthorization(httpMethod, null, uri);
    }

    public <t> Response sendRequestWithAuthorization(RequestTypes httpMethod, t entity, String uri) {
        createRequestSpecification();
        log.info("{} {} \n {}", httpMethod, uri,
                entity == null ? "" : new Gson().toJson(entity));

        Response response;
        if (!Objects.isNull(entity)) {
            requestSpecification.body(entity);
        }
        response = sendRequestForHttpMethod(httpMethod, uri, requestSpecification);
        return response;
    }

    /**
     * Returns default authorization token that gives access to all Rest Controllers
     *
     * @return authorization token
     */
    public String getAuthorizationToken() {
        Response response = given().param("grant_type", "client_credentials").auth().preemptive().basic(properties.getProperty("payPalUserName"),
                properties.getProperty("payPalPassword")).post(properties.getProperty("basePayPalURI") + properties.getProperty("getPayPalTokenURI"));

        if (Objects.nonNull(response.jsonPath().get("access_token"))) {
            return response.jsonPath().get("access_token");
        }
        throw new RuntimeException("Cannot get access token");
    }

    public void createRequestSpecification() {
        requestSpecification = given().contentType(ContentType.JSON).auth().oauth2(getAuthorizationToken());
    }

//    public Response sendRequest(RequestTypes httpMethod, String template, Map<String, Object> paramsMap) {
//        createRequestSpecification();
//        String correctedTemplate = getCorrectedTemplate(template, paramsMap);
//        Map<String, Object> params = getTargetParameters(correctedTemplate, new HashMap<>(paramsMap));
//        client.pathParams(params).params(params);
//        log.info("{} {}", httpMethod, BASE_URL + correctedTemplate);
//        Response response = sendRequestForHttpMethod(httpMethod, client.contentType(ContentType.JSON),
//                BASE_URL + correctedTemplate);
//        if (gettingNewTokenCounter.get() == 0) {
//            gettingNewTokenCounter.set(5);
//            return response;
//        }
//        if (response.getStatusCode() == 401) {
//            gettingNewTokenCounter.decrementAndGet();
//            updateHeaders();
//            return sendRequest(httpMethod, template, paramsMap);
//        }
//        gettingNewTokenCounter.set(5);
//        return response;
//    }

//    private String getCorrectedTemplate(String template, Map<String, Object> paramsMap) {
//        if (!template.startsWith("/")) {
//            template = "/" + template;
//        }
//        if (template.contains(QUESTION_CHAR)) {
//            template = removeExcessiveQueryParams(template, paramsMap);
//            if (!template.endsWith(QUESTION_CHAR)) {
//                template = removeNullQueryParam(template, paramsMap);
//            }
//        }
//        return template.replaceAll("&{2,}", "&").replace("?&", QUESTION_CHAR).replaceAll("&$|\\?$", "");
//    }

//    private String removeExcessiveQueryParams(String template, Map<String, Object> paramsMap) {
//        String pathPart = template.split(QUESTION_REGEXP)[0];
//        String[] queryParamStrings = template.split(QUESTION_REGEXP)[1].split("&");
//        for (String queryParamString : queryParamStrings) {
//            String[] queryParam = queryParamString.split("=");
//            if (queryParam.length != 2) {
//                throw new RuntimeException("Malformed query parameter: " + queryParamString);
//            }
//            if (!paramsMap.containsKey(extractParamKey(queryParam[1]))) {
//                String queryPart = template.split(QUESTION_REGEXP)[1];
//                template = pathPart + QUESTION_CHAR + queryPart.substring(0, queryPart.indexOf(queryParamString))
//                        + queryPart.substring(queryPart.indexOf(queryParamString) + queryParamString.length());
//            }
//        }
//        return template;
//    }

//    private String removeNullQueryParam(String template, Map<String, Object> paramsMap) {
//        String queryPart = template.split(QUESTION_REGEXP)[1];
//        String pathPart = template.split(QUESTION_REGEXP)[0];
//        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
//            if (entry.getValue() == null) {
//                template = pathPart + QUESTION_CHAR + queryPart.substring(0, queryPart.indexOf(entry.getKey()))
//                        + queryPart.substring(queryPart.lastIndexOf(entry.getKey()) + entry.getKey().length() + 1);
//            }
//        }
//        return template;
//    }

//    public <T> Response sendRequest(RequestTypes httpMethod, String template, Map<String, Object> paramsMap, T entity) {
//        return sendRequest(httpMethod, template, paramsMap, entity, null);
//    }

//    public <T> Response sendRequest(RequestTypes httpMethod, String template, Map<String, Object> paramsMap, T entity,
//                                    Map<String, String> additionalRequestHeaders) {
//        String correctedTemplate = getCorrectedTemplate(template, paramsMap);
//        Response response;
//        log.info("{} {} \n {}", httpMethod, BASE_URL + correctedTemplate,
//                entity == null ? "" : new Gson().toJson(entity));
//        if (null == additionalRequestHeaders) {
//            createRequestSpecification();
//            client.pathParams(getTargetParameters(correctedTemplate, new HashMap<>(paramsMap)));
//            response = sendRequestForHttpMethod(httpMethod,
//                    client.contentType(ContentType.JSON).body(new Gson().toJson(entity)), BASE_URL + correctedTemplate);
//        } else {
//            response = sendRequestForHttpMethod(httpMethod,
//                    RestAssured.given().headers(getHeaders(additionalRequestHeaders))
//                            .contentType(ContentType.JSON).body(new Gson().toJson(entity))
//                            .pathParams(getTargetParameters(correctedTemplate, new HashMap<>(paramsMap))),
//                    BASE_URL + correctedTemplate);
//        }
//        if (gettingNewTokenCounter.get() == 0) {
//            gettingNewTokenCounter.set(5);
//            return response;
//        }
//        if (response.getStatusCode() == 401) {
//            gettingNewTokenCounter.decrementAndGet();
//            updateHeaders();
//            return sendRequest(httpMethod, template, paramsMap, entity);
//        }
//        gettingNewTokenCounter.set(5);
//        return response;
//    }

//    private Map<String, Object> getTargetParameters(String template, Map<String, Object> paramsMap) {
//        Map<String, Object> pathParams = new HashMap<>();
//        for (Map.Entry<String, Object> param : paramsMap.entrySet()) {
//            if (template.contains(param.getKey())) {
//                pathParams.put(param.getKey(), param.getValue());
//            }
//        }
//        return pathParams;
//    }

//    private String extractParamKey(String s) {
//        if (!s.contains("{") || !s.contains("}")) {
//            throw new RuntimeException("Malformed parameter key: " + s + ". Key should be inside curly braces");
//        }
//        return s.substring(s.indexOf('{') + 1, s.indexOf('}'));
//    }

//    private Headers getHeaders() {
//        return new Headers(new Header(AUTHORIZATION, authorizationValue));
//    }

//    private Headers getHeaders(Map<String, String> additionalParams) {
//        List<Header> headerList = new ArrayList<>();
//        if (!additionalParams.containsKey(AUTHORIZATION)) {
//            headerList.add(new Header(AUTHORIZATION, authorizationValue));
//        }
//        for (Map.Entry<String, String> param : additionalParams.entrySet()) {
//            headerList.add(new Header(param.getKey(), param.getValue()));
//        }
//        return new Headers(headerList);
//    }


//    private void updateHeaders() {
//        try {
//            authorizationValue = getAuthorizationToken();
//        } catch (RuntimeException tee) {
//            log.error(tee.getMessage());
//        }
//    }
}