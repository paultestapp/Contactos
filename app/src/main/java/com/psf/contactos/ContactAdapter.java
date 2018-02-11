package com.psf.contactos;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by paulsalcedo on 11/2/18.
 */

public class ContactAdapter extends  RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    ArrayList<Contact> contacts;
    Activity activity;
    Resources resources;

    public ContactAdapter(ArrayList<Contact> contacts, Activity activity) {
        this.contacts = contacts;
        this.activity = activity;
        this.resources = activity.getResources();
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carview_contact, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        final int idx = i;

        final Contact contact = contacts.get(idx);
        contactViewHolder.img_photo.setImageResource(contact.getPhoto());
        contactViewHolder.tv_name.setText(contact.getName());
        contactViewHolder.tv_phone.setText(contact.getPhone());

        contactViewHolder.img_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details = new Intent(activity, DetalleContacto.class);
                details.putExtra(resources.getString(R.string.tag_name), contacts.get(idx).getName());
                details.putExtra(resources.getString(R.string.tag_phone), contacts.get(idx).getPhone());
                details.putExtra(resources.getString(R.string.tag_email), contacts.get(idx).getEmail());
                activity.startActivity(details);
                activity.finish();
            }
        });

        contactViewHolder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Like en " + contact.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        private ImageButton btn_like;
        private ImageView img_photo;
        private TextView tv_name;
        private TextView tv_phone;

        public ContactViewHolder(View itemView) {
            super(itemView);

            btn_like = itemView.findViewById(R.id.btn_like);
            img_photo = itemView.findViewById(R.id.img_photo);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);

        }
    }

}
