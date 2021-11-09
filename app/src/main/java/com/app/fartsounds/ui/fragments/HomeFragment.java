package com.app.fartsounds.ui.fragments;

import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fartsounds.R;
import com.app.fartsounds.adapters.SoundsListAdapter;
import com.app.fartsounds.models.AdsManager;
import com.app.fartsounds.models.SoundModel;
import com.ironsource.mediationsdk.IronSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLayoutManager;



    private SoundsListAdapter soundModelListAdapter;
    private List<SoundModel> soundModelList;
    private MediaPlayer mp;
    private int selectedIndex = -1;

    FrameLayout frameLayout;


    LinearLayout linearLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //getActivity().setTitle(getString(R.string.app_name));
        recyclerView = view.findViewById(R.id.recycler_view);
        mp = new MediaPlayer();
        IronSource.loadInterstitial();
        listSoundFilesToView();


    }



    private void listSoundFilesToView() {
        soundModelList = getAllSoundFilesFromAssets();
        verticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayoutManager);
        soundModelListAdapter = new SoundsListAdapter(this, soundModelList, getActivity());
        recyclerView.setAdapter(soundModelListAdapter);
    }

    private List<SoundModel> getAllSoundFilesFromAssets() {
        final List<SoundModel> tempSoundList = new ArrayList<>();
        tempSoundList.add(new SoundModel(R.string.s1,"The Fart which Smells Like Rose", "le_pet_a_la_rose_1.mp3", "Le Pet à la Rose", getDuration("le_pet_a_la_rose_1.mp3")));
        tempSoundList.add(new SoundModel(R.string.s2,"The Air Fart", "le_pet_aerien_2.mp3", "Le Pet Aérien", getDuration("le_pet_aerien_2.mp3")));
        tempSoundList.add(new SoundModel(R.string.s3,"The Fart of the Future", "le_pet_allonge_3.mp3", "Le Pet Allongé", getDuration("le_pet_allonge_3.mp3")));
        tempSoundList.add(new SoundModel(R.string.s4,"The Atomic Fart", "le_pet_atomique_4.mp3", "Le Pet Atomique", getDuration("le_pet_atomique_4.mp3")));
        tempSoundList.add(new SoundModel(R.string.s5,"The Classic Fart", "le_pet_classique_5.mp3", "Le Pet Classique", getDuration("le_pet_classique_5.mp3")));
        tempSoundList.add(new SoundModel(R.string.s6,"The Fart in the Toilet Bowl", "le_pet_dans_la_cuvette_6.mp3", "Le Pet dans la cuvette", getDuration("le_pet_dans_la_cuvette_6.mp3")));
        tempSoundList.add(new SoundModel(R.string.s7,"The Fart of the Year", "le_pet_de_bourrin_7.mp3", "Le Pet de Bourrin", getDuration("le_pet_de_bourrin_7.mp3")));
        tempSoundList.add(new SoundModel(R.string.s8,"The Fart of the Duck", "le_pet_de_canard_8.mp3", "Le Pet de Canard", getDuration("le_pet_de_canard_8.mp3")));
        tempSoundList.add(new SoundModel(R.string.s9,"The Discreet Fart", "le_pet_discret_9.mp3", "Le Pet Discret", getDuration("le_pet_discret_9.mp3")));
        tempSoundList.add(new SoundModel(R.string.s10,"The Crappy Fart", "le_pet_foireux_10.mp3", "Le Pet Foireux", getDuration("le_pet_foireux_10.mp3")));
        tempSoundList.add(new SoundModel(R.string.s11,"The Forced Fart", "le_pet_force_11.mp3", "Le Pet Forcé", getDuration("le_pet_force_11.mp3")));
        tempSoundList.add(new SoundModel(R.string.s12,"Stealthy Fart", "le_pet_furtif_12.mp3", "Le Pet Furtif", getDuration("le_pet_furtif_12.mp3")));
        tempSoundList.add(new SoundModel(R.string.s13,"The Fart Mastodon", "le_pet_mastodonte_13.mp3", "Le Pet Mastodonte", getDuration("le_pet_mastodonte_13.mp3")));
        tempSoundList.add(new SoundModel(R.string.s14,"The Long Fart", "le_pet_prolonge_2_14.mp3", "Le Pet Prolongé", getDuration("le_pet_prolonge_2_14.mp3")));
        tempSoundList.add(new SoundModel(R.string.s15,"The Fart Tight but Prolonged", "le_pet_serre_mais_prolonge_15.mp3", "Le Pet Serré mais prolongé", getDuration("le_pet_serre_mais_prolonge_15.mp3")));
        tempSoundList.add(new SoundModel(R.string.s16,"The Sneaky Fart", "le_pet_sournois_16.mp3", "Le Pet Sournois", getDuration("le_pet_sournois_16.mp3")));
        tempSoundList.add(new SoundModel(R.string.s17,"The Shy Fart", "le_pet_timide2_17.mp3", "Le Pet Timide", getDuration("le_pet_timide2_17.mp3")));
        tempSoundList.add(new SoundModel(R.string.s18,"The Little Fart", "le_simili_pet_18.mp3", "Le Simili Pet", getDuration("le_simili_pet_18.mp3")));
        tempSoundList.add(new SoundModel(R.string.s19,"The Single Fart", "le_simple_pet_19.mp3", "Le Simple Pet", getDuration("le_simple_pet_19.mp3")));

        return tempSoundList;
    }

    public String getDuration(String filename) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        AssetFileDescriptor d = null;
        try {
            d = requireContext().getAssets().openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmr.setDataSource(d.getFileDescriptor(), d.getStartOffset(), d.getLength());
        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long dur = Long.parseLong(duration);
        String seconds = String.valueOf((dur % 60000) / 1000);
        Log.d("filename===========>", filename);
        Log.d("seconds===========>", seconds);
        mmr.release();
        if (seconds.equals("0")) seconds = "1";
        return seconds;
    }

    public void playSound(String filename, int index, boolean isPlaying) {
        if (index == selectedIndex) {
            if (mp.isPlaying())
                mp.pause();
            else mp.start();
        }
        if (!isPlaying) {
            for (int i = 0; i < soundModelList.size(); i++) {
                soundModelList.get(i).setPlaying(false);
            }
            soundModelList.get(index).setPlaying(true);
            soundModelListAdapter.setData(soundModelList);
            soundModelListAdapter.notifyDataSetChanged();

            if (mp != null) mp.release();
            AssetFileDescriptor afd = null;
            try {
                afd = getResources().getAssets().openFd(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mp = new MediaPlayer();
            try {
                assert afd != null;
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mp.start();
            selectedIndex = index;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp!=null) mp.release();
    }




}