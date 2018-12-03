package com.bilgetech.nerdesiniz;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class firebase {

    private FirebaseDatabase db;

    public firebase() {
        db = FirebaseDatabase.getInstance();

    }

    public String insertData(Person person) {

        DatabaseReference mFirebaseDatabase = db.getReference("rooms");
        String id = "";
        if (TextUtils.isEmpty(id)) {
            id = mFirebaseDatabase.push().getKey();
        }


        mFirebaseDatabase.child(person.getRoom_id()).child(id).setValue(person);

        return id;
    }

    public void updateData(Person person) {
        DatabaseReference mFirebaseDatabase = db.getReference("rooms");


        mFirebaseDatabase.child(person.getRoom_id()).child(person.getId()).setValue(person);


    }

    public void deleteData(Person person) {

        DatabaseReference mFirebaseDatabase = db.getReference("rooms");
        mFirebaseDatabase.child(person.getRoom_id()).child(person.getId()).removeValue();

    }

    public ArrayList<Person> selectData(String room_id) {
        final ArrayList<Person> personList = new ArrayList<>();
        DatabaseReference mFirebaseDatabase = db.getReference("rooms");

        mFirebaseDatabase.child(room_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()
                        ) {

                    personList.add(new Person(ds.getKey()
                            , ds.child("room_id").getValue(String.class)
                            , ds.child("name").getValue(String.class)
                            , ds.child("color").getValue(String.class)
                            , new Location(ds.child("location").child("lat").getValue(String.class)
                            ,ds.child("location").child("lng").getValue(String.class))));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return personList;
    }
}
