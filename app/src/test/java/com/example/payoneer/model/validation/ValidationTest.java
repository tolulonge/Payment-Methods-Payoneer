package com.example.payoneer.model.validation;

import static org.junit.Assert.*;

import com.example.payoneer.model.PayoneerItems;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationTest {
    private List<PayoneerItems> payoneerItemsList;

    @Before
    public void setUp() throws Exception {
        Map<String, URL> links = new HashMap<>();
        links.put("logo", new URL("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/mistercash.png"));
        payoneerItemsList = new ArrayList<>();
        payoneerItemsList.add(new PayoneerItems("Paypal","credit_card", links
        ));
    }

    @Test
    public void check_if_payoneer_items_list_not_empty_returns_true(){
        // Given a list of payoneerItems
        //When
        boolean result = Validation.checkPayoneerMethodsList(payoneerItemsList);

        assertTrue(result);
    }
}