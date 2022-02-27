package com.funnyringtone.bestringtones;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.funnyringtone.bestringtones.models.SoundModel;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SoundRingScreen extends AppCompatActivity implements View.OnClickListener {

    TextView name, duration, set_ringtone, set_contacttone, set_smstone, set_alarm;
    String tunename, durationvalue,showname;
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
        if (intent != null) {
            tunename = intent.getStringExtra("name");
            showname = intent.getStringExtra("showname");
            durationvalue = intent.getStringExtra("duration");
            isplaying = Boolean.parseBoolean(intent.getStringExtra("isplaying"));
            soundposition = Integer.parseInt((intent.getStringExtra("position")));
        } else {
            Toast.makeText(SoundRingScreen.this, "Intent is null", Toast.LENGTH_SHORT).show();
        }
        init();
        playimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SoundRingScreen.this, "" + soundposition, Toast.LENGTH_SHORT).show();
                playSound(tunename, soundposition, isplaying);
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
        toolbar.setTitle(showname);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        soundModelList = getAllSoundFilesFromAssets();
        name = findViewById(R.id.name);
        playimg = findViewById(R.id.img);
        set_ringtone = findViewById(R.id.set_rintone);
        set_contacttone = findViewById(R.id.set_rintonecontact);
        set_smstone = findViewById(R.id.set_sms);
        set_alarm = findViewById(R.id.set_alarm);
        duration = findViewById(R.id.duration);
        name.setText(showname);
        duration.setText("00:0" + durationvalue);
        set_ringtone.setOnClickListener(this);
        set_contacttone.setOnClickListener(this);
        set_smstone.setOnClickListener(this);
        set_alarm.setOnClickListener(this);

    }

    private List<SoundModel> getAllSoundFilesFromAssets() {
        final List<SoundModel> tempSoundList = new ArrayList<>();
        tempSoundList.add(new SoundModel(R.string.s1, "The Fart which Smells Like Rose", "le_pet_a_la_rose_1.mp3", "Le Pet à la Rose", getDuration("le_pet_a_la_rose_1.mp3")));
        tempSoundList.add(new SoundModel(R.string.s2, "The Air Fart", "le_pet_aerien_2.mp3", "Le Pet Aérien", getDuration("le_pet_aerien_2.mp3")));
        tempSoundList.add(new SoundModel(R.string.s3, "The Fart of the Future", "le_pet_allonge_3.mp3", "Le Pet Allongé", getDuration("le_pet_allonge_3.mp3")));
        tempSoundList.add(new SoundModel(R.string.s4, "The Atomic Fart", "le_pet_atomique_4.mp3", "Le Pet Atomique", getDuration("le_pet_atomique_4.mp3")));
        tempSoundList.add(new SoundModel(R.string.s5, "The Classic Fart", "le_pet_classique_5.mp3", "Le Pet Classique", getDuration("le_pet_classique_5.mp3")));
        tempSoundList.add(new SoundModel(R.string.s6, "The Fart in the Toilet Bowl", "le_pet_dans_la_cuvette_6.mp3", "Le Pet dans la cuvette", getDuration("le_pet_dans_la_cuvette_6.mp3")));
        tempSoundList.add(new SoundModel(R.string.s7, "The Fart of the Year", "le_pet_de_bourrin_7.mp3", "Le Pet de Bourrin", getDuration("le_pet_de_bourrin_7.mp3")));
        tempSoundList.add(new SoundModel(R.string.s8, "The Fart of the Duck", "le_pet_de_canard_8.mp3", "Le Pet de Canard", getDuration("le_pet_de_canard_8.mp3")));
        tempSoundList.add(new SoundModel(R.string.s9, "The Discreet Fart", "le_pet_discret_9.mp3", "Le Pet Discret", getDuration("le_pet_discret_9.mp3")));
        tempSoundList.add(new SoundModel(R.string.s10, "The Crappy Fart", "le_pet_foireux_10.mp3", "Le Pet Foireux", getDuration("le_pet_foireux_10.mp3")));
        tempSoundList.add(new SoundModel(R.string.s11, "The Forced Fart", "le_pet_force_11.mp3", "Le Pet Forcé", getDuration("le_pet_force_11.mp3")));
        tempSoundList.add(new SoundModel(R.string.s12, "Stealthy Fart", "le_pet_furtif_12.mp3", "Le Pet Furtif", getDuration("le_pet_furtif_12.mp3")));
        tempSoundList.add(new SoundModel(R.string.s13, "The Fart Mastodon", "le_pet_mastodonte_13.mp3", "Le Pet Mastodonte", getDuration("le_pet_mastodonte_13.mp3")));
        tempSoundList.add(new SoundModel(R.string.s14, "The Long Fart", "le_pet_prolonge_2_14.mp3", "Le Pet Prolongé", getDuration("le_pet_prolonge_2_14.mp3")));
        tempSoundList.add(new SoundModel(R.string.s15, "The Fart Tight but Prolonged", "le_pet_serre_mais_prolonge_15.mp3", "Le Pet Serré mais prolongé", getDuration("le_pet_serre_mais_prolonge_15.mp3")));
        tempSoundList.add(new SoundModel(R.string.s16, "The Sneaky Fart", "le_pet_sournois_16.mp3", "Le Pet Sournois", getDuration("le_pet_sournois_16.mp3")));
        tempSoundList.add(new SoundModel(R.string.s17, "The Shy Fart", "le_pet_timide2_17.mp3", "Le Pet Timide", getDuration("le_pet_timide2_17.mp3")));
        tempSoundList.add(new SoundModel(R.string.s18, "The Little Fart", "le_simili_pet_18.mp3", "Le Simili Pet", getDuration("le_simili_pet_18.mp3")));
        tempSoundList.add(new SoundModel(R.string.s19, "The Single Fart", "le_simple_pet_19.mp3", "Le Simple Pet", getDuration("le_simple_pet_19.mp3")));

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
        if (mp != null) mp.release();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_rintone:
                type = 0;
                checkAll();
                break;
            case R.id.set_rintonecontact:
                type = 1;
                checkAll();
                break;
            case R.id.set_sms:
                type = 2;
                checkAll();
                break;
            case R.id.set_alarm:
                type = 3;
                checkAll();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACT_CHOOSER_ACTIVITY_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                setIndividualRingtone(data);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            int deniedCode = 0;
            // gather permission grant result

            // Add only permission which are denied
            for (int in : grantResults) {
                if (in == PackageManager.PERMISSION_DENIED) {
                    deniedCode++;
                }
            }

            // Check if all permission granted
            if (deniedCode == 0) {
                // Proceed
                if (isPermissionGranted())
                    completeAction();
            } else {
                if (isPermissionGranted())
                    completeAction();
            }
        }
    }

    private ContentValues getRingtoneContentValues(File file) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, tunename);
        values.put(MediaStore.Audio.Media.TITLE, tunename);
        values.put(MediaStore.Audio.Media.SIZE, file.length());
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mpeg");
        values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Audio.Media.RELATIVE_PATH, Environment.DIRECTORY_RINGTONES);
            values.put(MediaStore.Audio.Media.IS_PENDING, 1);
        } else
            values.put(MediaStore.Audio.Media.DATA, file.getAbsolutePath());
        return values;
    }

    private Uri addNewRingtone(ContentValues values, Uri uri, File file) {
        if (uri == null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                //delete the existing ringtone if duplicate ringtone occurs
                int delete = getContentResolver().delete(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                        , MediaStore.Audio.Media.DATA + "=\"" + file.getAbsolutePath() + "\"", null);
            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                //delete the existing ringtone if duplicate ringtone occurs
                int delete = getContentResolver().delete(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                        , MediaStore.Audio.Media.TITLE + "=\"" + values.getAsString(MediaStore.Audio.Media.TITLE) + "\"", null);
            }
            uri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    values);
            updateRingtonePending(uri, file, values);
        } else {
            updateRingtoneContentValues(uri, file, values);
        }
        return uri;
    }

    private void updateRingtonePending(Uri newUri, File file, ContentValues values) {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && newUri != null) {
                //init array with file length
                byte[] bytesArray = new byte[(int) file.length()];

                FileInputStream fis = new FileInputStream(file);
                fis.read(bytesArray); //read file into bytes[]
                fis.close();
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(newUri, "w", null);
                assert parcelFileDescriptor != null;
                FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
                fileOutputStream.write(bytesArray);
                fileOutputStream.close();

                values.put(MediaStore.Files.FileColumns.IS_PENDING, 0);
                getContentResolver().update(newUri, values, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateRingtoneContentValues(Uri newUri, File file, ContentValues values) {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                //init array with file length
                byte[] bytesArray = new byte[(int) file.length()];

                FileInputStream fis = new FileInputStream(file);
                fis.read(bytesArray); //read file into bytes[]
                fis.close();
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(newUri, "w", null);
                assert parcelFileDescriptor != null;
                FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
                fileOutputStream.write(bytesArray);
                fileOutputStream.close();
                values.put(MediaStore.Audio.Media.IS_PENDING, 0);
            }
            getContentResolver().update(newUri, values, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getRingtoneFile() {

        try {
            File dir = getExternalFilesDir("Ringtone");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, tunename);
            if (!file.exists()) {

                file.createNewFile();
                InputStream inputStream = getResources().getAssets().open(tunename);

                OutputStream outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setIndividualRingtone(Intent data) {
        try {
            Uri contactData = data.getData();
            assert contactData != null;
            String contactId = contactData.getLastPathSegment();
            String[] PROJECTION = new String[]{
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER,
            };
            Cursor localCursor = getContentResolver().query(contactData, PROJECTION, null, null, null);
            assert localCursor != null;
            localCursor.moveToFirst();
            //--> use moveToFirst instead of this:  localCursor.move(Integer.valueOf(contactId)); /*CONTACT ID NUMBER*/
            String contactID = localCursor.getString(localCursor.getColumnIndexOrThrow("_id"));
            String contactDisplayName = localCursor.getString(localCursor.getColumnIndexOrThrow("display_name"));
            Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, contactID);
            localCursor.close();

            ContentResolver contentResolver = getContentResolver();

            File file = getRingtoneFile();
            MediaScannerConnection.scanFile(SoundRingScreen.this,
                    new String[]{file.getAbsolutePath()}, new String[]{"audio/mp3"},
                    (path, uri) -> {
                        runOnUiThread(() -> {
                            ContentValues values = getRingtoneContentValues(file);
                            //Insert it into the database
                            Uri newUri = addNewRingtone(values, uri, file);
                            if (newUri != null) {
                                ContentValues localContentValues = new ContentValues(1);
                                localContentValues.put(ContactsContract.Contacts.CUSTOM_RINGTONE,
                                        newUri.toString());
                                long updated = contentResolver.update(localUri, localContentValues, null, null);

                                set_contacttone.setText("");
                                set_contacttone.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_done, 0, 0);
                            }
                        });
                    });
        } catch (Exception ex) {
            Toast.makeText(SoundRingScreen.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void setDefaultRingtone() {
        try {
            File file = getRingtoneFile();

            MediaScannerConnection.scanFile(SoundRingScreen.this,
                    new String[]{file.getAbsolutePath()}, new String[]{"audio/mp3"},
                    (path, uri) -> {
                        runOnUiThread(() -> {
                            ContentValues values = getRingtoneContentValues(file);
                            //Insert it into the database
                            Uri newUri = addNewRingtone(values, uri, file);
                            RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_RINGTONE, newUri);
                            set_ringtone.setText("");
                            set_ringtone.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_done, 0, 0);
                        });
                    });
        } catch (Exception ex) {
            Toast.makeText(SoundRingScreen.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void setDefaultNotice() {
        try {
            File file = getRingtoneFile();
            MediaScannerConnection.scanFile(SoundRingScreen.this,
                    new String[]{file.getPath()}, new String[]{"audio/mp3"},
                    (path, uri) -> {
                        runOnUiThread(() -> {
                            ContentValues values = getRingtoneContentValues(file);
                            //Insert it into the database
                            Uri newUri = addNewRingtone(values, uri, file);
                            RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION, newUri);
                            set_smstone.setText("");
                            set_smstone.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_done, 0, 0);
                        });
                    });
        } catch (Exception ex) {
            Toast.makeText(SoundRingScreen.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void setDefaultAlarm() {

        try {
            File file = getRingtoneFile();

            MediaScannerConnection.scanFile(SoundRingScreen.this,
                    new String[]{file.getAbsolutePath()}, new String[]{"audio/mp3"},
                    (path, uri) -> {

                        runOnUiThread(() -> {

                            ContentValues values = getRingtoneContentValues(file);
                            //Insert it into the database
                            Uri newUri = addNewRingtone(values, uri, file);
                            RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_ALARM, newUri);
                            set_alarm.setText("");
                            set_alarm.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_done, 0, 0);

                        });
                    });
        } catch (Exception ex) {
            Toast.makeText(SoundRingScreen.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private final List<String> permissionsNeeded = new ArrayList<>();
    private List<String> permissionsAll = new ArrayList<>();
    private final List<String> permissionsDenied = new ArrayList<>();
    private static final int REQUEST_PERMISSION = 786;
    static public final int CONTACT_CHOOSER_ACTIVITY_CODE = 729;
    static public final int CODE_WRITE_SETTINGS_PERMISSION = 101;
    int type = 0;

    public void checkAll() {
        permissionsAll.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionsAll.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissionsAll.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (type == 1) {
                permissionsAll.add(Manifest.permission.READ_CONTACTS);
                permissionsAll.add(Manifest.permission.WRITE_CONTACTS);
            }
            if (isPermissionGranted()) {
                completeAction();
            }
        } else {
            completeAction();
        }
    }

    private void completeAction() {
        if (type == 0)
            setDefaultRingtone();
        else if (type == 1) {
            getContact();
        } else if (type == 2) {
            setDefaultNotice();
        } else if (type == 3) {
            setDefaultAlarm();
        }
    }

    private void getContact() {
        // start contact search activity within any method you like
        Intent intent1 = new Intent(Intent.ACTION_PICK);
        intent1.setType(ContactsContract.Contacts.CONTENT_TYPE);
        startActivityForResult(intent1, CONTACT_CHOOSER_ACTIVITY_CODE);
    }

    private Boolean isPermissionGranted() {
        permissionsNeeded.clear();
        permissionsDenied.clear();
        for (String per : permissionsAll) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!checkPermission(
                        per
                )
                ) permissionsNeeded.add(per);
            }
        }
        boolean permission = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = Settings.System.canWrite(getApplicationContext());
        }
        if (!permissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsNeeded.toArray(new String[permissionsNeeded.size()]),
                    REQUEST_PERMISSION
            );
            return false;
        } else if (!permissionsDenied.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.permission_required)
                    .setMessage(R.string.permission_message)
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton(getString(R.string.action_settings), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", getPackageName(), null)
                            );
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).setCancelable(true).create().show();
            return false;
        } else if (!permission) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.allow_access)
                    .setMessage(R.string.set_sound_need_permission_msg)
                    .setNegativeButton(getString(R.string.not_now), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton(getString(R.string.action_settings), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, CODE_WRITE_SETTINGS_PERMISSION);
                            dialog.dismiss();
                        }
                    }).setCancelable(true).create().show();
            return false;
        }
        return true;
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private Boolean checkPermission(
            String permission
    ) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
        ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true;
        } else if (shouldShowRequestPermissionRationale(permission)
        ) {
            permissionsDenied.add(permission);
            return true;
        }
        return false;
    }
    protected void onResume() {
        super.onResume();

    }
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
