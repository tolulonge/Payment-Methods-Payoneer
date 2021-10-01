package com.example.payoneer.repository;

import com.example.payoneer.model.ListResult;

import java.util.List;

import retrofit2.Call;

public interface MainRepository {

    Call<ListResult> getListResult();
}
