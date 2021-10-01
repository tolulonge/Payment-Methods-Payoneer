package com.example.payoneer.model.validation;

import com.example.payoneer.model.PayoneerItems;

import java.util.List;

public class Validation {
   public static boolean checkPayoneerMethodsList(List<PayoneerItems> payoneerItemsList){
       return payoneerItemsList.size() > 0;
   }
}
