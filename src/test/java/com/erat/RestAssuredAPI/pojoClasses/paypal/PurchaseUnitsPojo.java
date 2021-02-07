package com.erat.RestAssuredAPI.pojoClasses.paypal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseUnitsPojo {
    private AmountPojo amount;

    public PurchaseUnitsPojo(String currencyCode, String value){
        this.amount = new AmountPojo(currencyCode, value);
    }
}
