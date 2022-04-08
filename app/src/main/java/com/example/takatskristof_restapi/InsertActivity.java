package com.example.takatskristof_restapi;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InsertActivity extends AppCompatActivity {

    private Button btnFelvetel,btnVissza;
    private EditText editTextNev,editTextOrszag,editTextLakossag;
    DatabaseReference varosokFirebase,postRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
    }
    private void init(){
        btnFelvetel=findViewById(R.id.btnFelvetel);
        btnVissza=findViewById(R.id.btnVissza);
        editTextNev=findViewById(R.id.editTextNev);
        editTextOrszag=findViewById(R.id.editTextOrszag);
        editTextLakossag=findViewById(R.id.editTextLakossag);
        btnFelvetel.setEnabled(false);

        editTextNev.addTextChangedListener(watcher);
        editTextOrszag.addTextChangedListener(watcher);
        editTextLakossag.addTextChangedListener(watcher);

        varosokFirebase= FirebaseDatabase.getInstance().getReference().child("Varosok");
        postRef=varosokFirebase.child("varos");
        btnFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertVaros();
            }
        });

        btnVissza.setOnClickListener(view -> {
            Intent i = new Intent(InsertActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void insertVaros(){
        String varos=editTextNev.getText().toString();
        String orszag=editTextOrszag.getText().toString();
        String convertLakossag= editTextLakossag.getText().toString();
        int lakossag=Integer.parseInt(convertLakossag);


        postRef.orderByChild("varos").equalTo(varos)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            if (data.child(varos).exists()) {
                                editTextNev.setTextColor(Color.RED);
                            } else {
                                editTextNev.setTextColor(Color.GREEN);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        Varosok varosok=new Varosok(varos,orszag,lakossag);

        varosokFirebase.push().setValue(varosok);
        Toast.makeText(InsertActivity.this,"Város hozzáadva",Toast.LENGTH_SHORT).show();
    }
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}
        @Override
        public void afterTextChanged(Editable s) {
            if (editTextNev.getText().toString().length() == 0 || editTextOrszag.getText().toString().length() == 0 ||
                    editTextLakossag.getText().toString().length() == 0) {
                btnFelvetel.setEnabled(false);
            } else {
                btnFelvetel.setEnabled(true);
            }
        }
    };
}