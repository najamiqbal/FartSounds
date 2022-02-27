package com.funnyringtone.bestringtones.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.funnyringtone.bestringtones.MainActivity;
import com.funnyringtone.bestringtones.MyApplication;
import com.funnyringtone.bestringtones.R;
import com.funnyringtone.bestringtones.utils.LangKeyWords;
import com.funnyringtone.bestringtones.utils.LanguagePref;

public class LanguageFragment extends Fragment {

    private RelativeLayout enLayout;
    private RelativeLayout frLayout;
    private ImageView enCheck;
    private ImageView frCheck;

    private MyApplication myApplication;
    private String langKey;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        langKey = LanguagePref.getLang(getActivity());
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_language, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        enLayout = view.findViewById(R.id.en);
        frLayout = view.findViewById(R.id.fr);
        enCheck = view.findViewById(R.id.en_check);
        frCheck = view.findViewById(R.id.fr_check);

        if (langKey.equalsIgnoreCase(LangKeyWords.ENGLISH)) {
            enCheck.setVisibility(View.VISIBLE);
            frCheck.setVisibility(View.GONE);
        } else {
            enCheck.setVisibility(View.GONE);
            frCheck.setVisibility(View.VISIBLE);
        }


        enLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LanguagePref.getLang(getActivity()).equals(LangKeyWords.ENGLISH)) {
                    LanguagePref.persistLang(getActivity(), "us");
                    enCheck.setVisibility(View.VISIBLE);
                    frCheck.setVisibility(View.GONE);
                    MyApplication.setDefaultLocale(getActivity());
                    restartApp();
                }
            }
        });

        frLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LanguagePref.getLang(getActivity()).equals(LangKeyWords.FRENCH)) {
                    LanguagePref.persistLang(getActivity(), "fr");
                    enCheck.setVisibility(View.GONE);
                    frCheck.setVisibility(View.VISIBLE);
                    MyApplication.setDefaultLocale(getActivity());
                    restartApp();
                }
            }
        });
    }

    private void restartApp() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
