package com.example.takatskristof_restapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListResultActivity extends AppCompatActivity {
    private ListView varosListView;
    List<Varosok> varosokList;

    DatabaseReference varosokFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);

        varosListView=findViewById(R.id.varosListView);
        varosokList=new ArrayList<>();

        varosokFirebase= FirebaseDatabase.getInstance().getReference("Varosok");

        varosokFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                varosokList.clear();

                for(DataSnapshot varosDatasnap : snapshot.getChildren()){
                    Varosok varosok=varosDatasnap.getValue(Varosok.class);
                    varosokList.add(varosok);
                }

                ListAdapter adapter=new ListAdapter(ListResultActivity.this,varosokList);
                varosListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}