package com.example.payoneer.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.payoneer.model.ListResult;
import com.example.payoneer.network.ApiError;
import com.example.payoneer.network.ErrorUtils;
import com.example.payoneer.repository.MainRepository;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private final MainRepository mainRepository;
   public MainViewModel(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }

    private final MutableLiveData<ListResult> listResult = new MutableLiveData<>();
    public LiveData<ListResult> getListResult() {
        return listResult;
    }

    private final MutableLiveData<String> responseMessage = new MutableLiveData<>();
    public LiveData<String> getResponseMessage() {
        return responseMessage;
    }

    public void loadUsers() {
        // Do an asynchronous operation to fetch listOfUsers.
        try{
            Call<ListResult> call = mainRepository.getListResult();
            call.enqueue(new Callback<ListResult>() {
                @Override
                public void onResponse(@NonNull Call<ListResult> call, @NonNull Response<ListResult> response) {
                    if (response.isSuccessful()){
                     //   int statusCode = response.code();
                        ListResult listResultVal = response.body();
                        listResult.postValue(listResultVal);
                        responseMessage.postValue("Success Fetching Payment Methods");
                    }else{
                        /**
                         * Error Handling Scenario 1: Handles errors 404 and 500 only and
                         * displays a default Unknown Error Occured
                         */
//                        switch (response.code()){
//                            case 404:
//                                responseMessage.postValue("Not Found");
//                            case 500:
//                                responseMessage.postValue("Internal Server Error");
//                            default:
//                                responseMessage.postValue("Unknown Error Occured");
//                        }

                        /**
                         * Error Handling Scenario 2: Handles all kind of errors and if error body
                         * not found displays a default Unknown Error
                         */
                        try {
                            if (response.errorBody() != null)
                            responseMessage.postValue(response.errorBody().string());
                        } catch (IOException e) {
                            responseMessage.postValue("Unknown Error");
                            e.printStackTrace();
                        }
                        /**
                         * Error Handling Scenario 3: Throws an error(
                         * com.google.gson.JsonSyntaxException: java.lang.IllegalStateException:
                         * Expected BEGIN_OBJECT but was NUMBER at line 1 column 4 path $)
                         * which could be because the response.errorBody starts with a number and
                         * should have be designed as a Json object
                         */
//                        ApiError apiError = ErrorUtils.parseError(response);
//                        responseMessage.postValue(apiError.getMessage());

                    }


                }

                @Override
                public void onFailure(@NonNull Call<ListResult> call, @NonNull Throwable t) {
                    responseMessage.postValue("Unable to resolve host, check your internet connection");
                }
            });
        }catch (Exception exception) {
            responseMessage.postValue(exception.toString());
        }
    }
}
