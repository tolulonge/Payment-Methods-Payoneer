package com.example.payoneer.repository;

import com.example.payoneer.model.ListResult;
import com.example.payoneer.network.ApiService;

import java.util.List;

import retrofit2.Call;

/**
 * Handles the network call
 */
public class MainRepositoryImpl implements MainRepository {
    public ApiService apiService;
    public MainRepositoryImpl(ApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public Call<ListResult> getListResult() {
        return apiService.getListResult();
    }
}
