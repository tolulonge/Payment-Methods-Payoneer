package com.example.payoneer.viewmodel;

import com.example.payoneer.repository.MainRepository;

// Definition of a Factory interface with a function to create objects of a type
interface Factory<T> {
    T create();
}

// Factory for MainViewModel.
// Since MainViewModel depends on MainRepository, in order to create instances of
// MainViewModel, you need an instance of MainRepository that you pass as a parameter.
public class MainViewModelFactory implements Factory {

    private final MainRepository mainRepository;

    public MainViewModelFactory(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public MainViewModel create() {
        return new MainViewModel(mainRepository);
    }
}
