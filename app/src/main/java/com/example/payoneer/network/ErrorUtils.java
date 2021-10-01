package com.example.payoneer.network;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

// This classes parses the error to return a type of ApiError
public class ErrorUtils {

    public static ApiError parseError(Response<?> response){
        Converter<ResponseBody, ApiError> converter = ServiceGenerator.retrofit.responseBodyConverter(
                ApiError.class, new Annotation[0]
        );

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        }catch (IOException e){
            return new ApiError();
        }
        return error;
    }
}
