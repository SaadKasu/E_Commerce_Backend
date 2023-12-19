package com.ecommerce_project.product_service.Utility;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneralUtility {
    public static List<String> convertOptionalToStrings(Optional<List<String>> optionalStrings){
        List<String> strings;
        if (optionalStrings.isEmpty()){
            strings = new ArrayList<>();
            strings.add("No Data Returned");
        }
        else{
            strings = optionalStrings.get();
        }
        return strings;
    }
}
