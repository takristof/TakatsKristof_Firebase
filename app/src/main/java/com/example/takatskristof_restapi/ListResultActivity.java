package com.example.takatskristof_restapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button btnListVissza;

    DatabaseReference varosokFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);
        init();

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
    private void init(){
        btnListVissza=findViewById(R.id.btnListVissza);

        btnListVissza.setOnClickListener(view -> {
            Intent i = new Intent(ListResultActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}