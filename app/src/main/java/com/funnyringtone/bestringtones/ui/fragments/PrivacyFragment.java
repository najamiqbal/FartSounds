package com.funnyringtone.bestringtones.ui.fragments;


import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.funnyringtone.bestringtones.R;
import com.funnyringtone.bestringtones.utils.AppConstants;

public class PrivacyFragment extends Fragment {
    View view;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_privacy ,container,false);
        textView=view.findViewById(R.id.tv_company);
        textView.setText(Html.fromHtml(AppConstants.company));
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("About Us");
        super.onViewCreated(view, savedInstanceState);
    }
}
