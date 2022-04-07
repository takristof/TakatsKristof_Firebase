package com.example.takatskristof_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {

    private Button btnFelvetel,btnVissza;
    private EditText editTextNev,editTextOrszag,editTextLakossag;
    DatabaseReference varosokFirebase;

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

        varosokFirebase= FirebaseDatabase.getInstance().getReference().child("Varosok");

        btnFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertVaros();
            }
        });
    }

    private void insertVaros(){
        String varos=editTextNev.getText().toString();
        String orszag=editTextOrszag.getText().toString();
        String convertLakossag= editTextLakossag.getText().toString();
        int lakossag=Integer.parseInt(convertLakossag);

        Varosok varosok=new Varosok(varos,orszag,lakossag);

        varosokFirebase.push().setValue(varosok);
        Toast.makeText(InsertActivity.this,"Város hozzáadva",Toast.LENGTH_SHORT).show();
    }
}