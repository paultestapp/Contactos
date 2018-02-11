package com.psf.contactos;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DetalleContacto extends AppCompatActivity {
    private Resources resources;

    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        resources = getResources();

        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phone);
        tv_email = findViewById(R.id.tv_email);

        Bundle params = getIntent().getExtras();

        // Set data
        tv_name.setText(params.getString(resources.getString(R.string.tag_name)));
        tv_phone.setText(params.getString(resources.getString(R.string.tag_phone)));
        tv_email.setText(params.getString(resources.getString(R.string.tag_email)));

    }

    public void goCall(View view) {
        String phone = tv_phone.getText().toString();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try{
            Intent iCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phone));
            startActivity(iCall);
        }catch (ActivityNotFoundException activityException) {
            Log.e("DetalleContacto", "Call failed: " + activityException.toString());
        }
    }

    public void sendMail(View view) {
        String email = tv_email.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse(email));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent iMain = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(iMain);
        }
        return super.onKeyDown(keyCode, event);
    }
}
