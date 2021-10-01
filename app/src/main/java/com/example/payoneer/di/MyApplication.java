package com.example.payoneer.di;

import android.app.Application;

import com.example.payoneer.network.ApiService;
import com.example.payoneer.network.ServiceGenerator;
import com.example.payoneer.repository.MainRepository;
import com.example.payoneer.repository.MainRepositoryImpl;
import com.example.payoneer.viewmodel.MainViewModelFactory;

// Custom Application class that needs to be specified
public class MyApplication extends Application {

    // creates the apiService client from the ServiceGenerator class
   static ApiService client = ServiceGenerator.createService(ApiService.class);

   private static final MainRepository mainRepository = new MainRepositoryImpl(client);

    // mainViewModelFactory is not private; it'll be exposed in order to create the viewModel
    public static MainViewModelFactory mainViewModelFactory = new MainViewModelFactory(mainRepository);
}