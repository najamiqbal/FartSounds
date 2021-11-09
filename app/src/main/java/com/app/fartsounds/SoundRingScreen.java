package com.app.fartsounds;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.fartsounds.models.SoundModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SoundRingScreen extends AppCompatActivity implements View.OnClickListener {

    TextView name, duration, set_ringtone, set_contacttone,set_smstone,set_alarm;
    String tunename, durationvalue;
    boolean isplaying;
    ImageView playimg;
    private MediaPlayer mp;
    int soundposition;
    private int selectedIndex = -1;
    private List<SoundModel> soundModelList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ring_screen);
        Intent intent = getIntent();

        // check intent is null or not
        if(intent != null){
            tunename = intent.getStringExtra("name");
            durationvalue = intent.getStringExtra("duration");
            isplaying = Boolean.parseBoolean(intent.getStringExtra("isplaying"));
            soundposition = Integer.parseInt((intent.getStringExtra("position")));
        }
        else{
            Toast.makeText(SoundRingScreen.this, "Intent is null", Toast.LENGTH_SHORT).show();
        }
        init();
        playimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SoundRingScreen.this, ""+soundposition, Toast.LENGTH_SHORT).show();
                playSound(tunename,soundposition,isplaying);
            }
        });
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

    private void init() {
        mp = new MediaPlayer();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(tunename);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        soundModelList = getAllSoundFilesFromAssets();
        name=findViewById(R.id.name);
        playimg=findViewById(R.id.img);
        set_ringtone=findViewById(R.id.set_rintone);
        set_contacttone=findViewById(R.id.set_rintonecontact);
        set_smstone=findViewById(R.id.set_sms);
        set_alarm=findViewById(R.id.set_alarm);
        duration=findViewById(R.id.duration);
        name.setText(tunename);
        duration.setText("00:0"+durationvalue);
        set_ringtone.setOnClickListener(this);
        set_contacttone.setOnClickListener(this);
        set_smstone.setOnClickListener(this);
        set_alarm.setOnClickListener(this);

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
            d = this.getAssets().openFd(filename);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp!=null) mp.release();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_rintone:
                // Do something
                set_ringtone.setText("");
                try {
                    set_asset_ringtone(SoundRingScreen.this,tunename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                set_ringtone.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_done, 0, 0);

                break;
            case R.id.set_rintonecontact:
                break;
            case R.id.set_sms:
                break;
            case R.id.set_alarm:
                break;
        }
    }


    void set_asset_ringtone(Context context, String asset_name) throws IOException {

        ///////////////copying from assets to filepath////////////////
        InputStream externalDbStream = context.getAssets().open(asset_name);
        String outFileName ="file:///android_asset/"+asset_name;

        OutputStream localDbStream = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = externalDbStream.read(buffer)) > 0) {
            localDbStream.write(buffer, 0, bytesRead);
        }
        localDbStream.close();
        externalDbStream.close();
        //////Setting the ringtone /////////////////
        File rigntone_file=new File(outFileName);
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, rigntone_file.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, "ring");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(MediaStore.MediaColumns.SIZE, rigntone_file.length());
        values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        Uri uri = MediaStore.Audio.Media.getContentUriForPath(rigntone_file.getAbsolutePath());
        Uri newUri = getContentResolver().insert(uri, values);


        try {
            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE, newUri);
        } catch (Throwable t) {

        }

    }
}
