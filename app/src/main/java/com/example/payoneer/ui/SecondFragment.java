package com.example.payoneer.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payoneer.R;
import com.example.payoneer.adapter.PayoneerListAdapter;
import com.example.payoneer.databinding.FragmentSecondBinding;
import com.example.payoneer.di.MyApplication;
import com.example.payoneer.model.ApplicableNetwork;
import com.example.payoneer.model.PayoneerItems;
import com.example.payoneer.model.validation.Validation;
import com.example.payoneer.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private List<PayoneerItems> payoneerItemsList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = binding.recyclerViewList;

        payoneerItemsList = new ArrayList<>();
        // Gets mainViewFactory from the MyApplication class
        mainViewModel = MyApplication.mainViewModelFactory.create();
        getListResult();




        mainViewModel.getResponseMessage().observe(getViewLifecycleOwner(),msg->{
            binding.progressBar.setVisibility(View.GONE);

            if(!msg.equals("Success Fetching Payment Methods")){
                binding.errorTxt.setVisibility(View.VISIBLE);
                MaterialDialog mDialog = new MaterialDialog.Builder(requireActivity())
                        .setTitle("Error")
                        .setMessage(msg)
                        .setCancelable(true)
                        .setPositiveButton("Retry", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                // Retry Operation
                                getListResult();
                                binding.progressBar.setVisibility(View.VISIBLE);
                                dialogInterface.dismiss();
                            }
                        })
                        .build();

                // Show Dialog
                mDialog.show();
            }else{
                Snackbar.make(view,msg,Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(getResources().getColor(R.color.green_700))
                        .show();
            }
        });
    }

    private void getListResult(){
        binding.errorTxt.setVisibility(View.GONE);
        mainViewModel.loadUsers();
        mainViewModel.getListResult().observe(getViewLifecycleOwner(), listResult -> {
            if (listResult != null && !listResult.getNetworks().getApplicable().isEmpty() ){
                List<ApplicableNetwork> applicableNetworkList = listResult.getNetworks().getApplicable();
                for (int i = 0; i < applicableNetworkList.size(); i++) {
                    PayoneerItems payoneerItems = new PayoneerItems(applicableNetworkList.get(i).getLabel(),applicableNetworkList.get(i).getMethod(),applicableNetworkList.get(i).getLinks());
                    payoneerItemsList.add(payoneerItems);
                }

                if(Validation.checkPayoneerMethodsList(payoneerItemsList)){
                    binding.progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                PayoneerListAdapter adapter = new PayoneerListAdapter(requireContext(), payoneerItemsList);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView.setAdapter(adapter);

            }else {
                Snackbar.make(binding.recyclerViewList,"An error occurred",Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(getResources().getColor(R.color.green_700))
                        .show();

            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}