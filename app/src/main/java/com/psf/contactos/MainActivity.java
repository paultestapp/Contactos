package com.psf.contactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView lst_contacto;

    private static final String[] INITIAL_PERMS={
            android.Manifest.permission.CALL_PHONE
    };
    private static final int INITIAL_REQUEST=1337;

    private Resources resources;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources = getResources();
        checkPermission();

        lst_contacto = findViewById(R.id.lst_contactos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        lst_contacto.setLayoutManager(layoutManager);
        setDataContact();
        setAdapter();

    }

    public void setAdapter() {
        ContactAdapter contactAdapter = new ContactAdapter(contacts, this);
        lst_contacto.setAdapter(contactAdapter);
    }

    public void setDataContact() {
        contacts = new ArrayList<Contact>();

        contacts.add(new Contact(R.drawable.photo_1, "Jhon Smith", "3337777", "jhon@host.com"));
        contacts.add(new Contact(R.drawable.photo_2,"Petra Pothan", "2227777", "ppothan@host.com"));
        contacts.add(new Contact(R.drawable.photo_3,"Henry Ashthor", "3334477", "henry@host.com"));
        contacts.add(new Contact(R.drawable.photo_4,"Daria Osborn", "3233477", "osborn@host.com"));
        contacts.add(new Contact(R.drawable.photo_5,"Heydi Klanston", "1330097", "heydi@host.com"));
    }


    private  void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //
    }

}
