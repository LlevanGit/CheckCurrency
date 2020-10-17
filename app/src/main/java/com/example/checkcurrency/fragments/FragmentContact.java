package com.example.checkcurrency.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.checkcurrency.R;

public class FragmentContact extends Fragment {
    private Button btnFeedback;
    private Button btnReport;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.contact_fragment_layout, viewGroup, false);
        btnFeedback=view.findViewById(R.id.feedback_btn);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btnReport=view.findViewById(R.id.report_btn);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
