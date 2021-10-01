package com.example.payoneer.network;

import com.example.payoneer.model.ListResult;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("shared-test/lists/listresult.json")
    Call<ListResult> getListResult();

}
