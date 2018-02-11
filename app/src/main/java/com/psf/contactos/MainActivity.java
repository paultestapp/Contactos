package com.psf.contactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lst_contacto;

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

        contacts = new ArrayList<Contact>();

        contacts.add(new Contact("Jhon Smith", "3337777", "jhon@host.com"));
        contacts.add(new Contact("Petra Pothan", "2227777", "ppothan@host.com"));
        contacts.add(new Contact("Henry Ashthor", "3334477", "henry@host.com"));
        contacts.add(new Contact("Bill Osborn", "3233477", "osborn@host.com"));
        contacts.add(new Contact("Heydi Klanston", "1330097", "heydi@host.com"));

        ArrayList<String> contacts_name = new ArrayList<String>();

        for (Contact contact : contacts) {
            contacts_name.add(contact.getName());
        }

        lst_contacto = findViewById(R.id.lst_contactos);
        lst_contacto.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts_name));

        lst_contacto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent details = new Intent(MainActivity.this, DetalleContacto.class);
                details.putExtra(resources.getString(R.string.tag_name), contacts.get(i).getName());
                details.putExtra(resources.getString(R.string.tag_phone), contacts.get(i).getPhone());
                details.putExtra(resources.getString(R.string.tag_email), contacts.get(i).getEmail());
                startActivity(details);
                finish();
            }
        });

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
