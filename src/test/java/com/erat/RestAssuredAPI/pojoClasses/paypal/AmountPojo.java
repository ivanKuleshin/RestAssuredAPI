package com.erat.RestAssuredAPI.pojoClasses.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountPojo {
    @JsonProperty("currency_code")
    private String currencyCode;
    private String value;
}
