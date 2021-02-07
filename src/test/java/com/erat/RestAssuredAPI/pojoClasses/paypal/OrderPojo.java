package com.erat.RestAssuredAPI.pojoClasses.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPojo {
    private String intent;
    @JsonProperty("purchase_units")
    private List<PurchaseUnitsPojo> purchaseUnits;
}
