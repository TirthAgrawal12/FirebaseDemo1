package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button btn;
    Boolean flag = true;
    String value = "1";
    int count = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reff = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.nameET);
        btn = (Button)findViewById(R.id.btnClick);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String temp_name = name.getText().toString().trim();

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (!snapshot.child(temp_name).exists()) {

                            reff.child(temp_name).child("count").setValue("1");
                        }
                        else {
//                            reff.child(temp_name).child("count").setValue("1");

                            count = Integer.parseInt(snapshot.child(temp_name).child("count")
                                    .getValue().toString());
                            Log.i("HELLO : ", ""+count);

//                            return;
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                count++;
                reff.child(temp_name).child("count").setValue(""+count);
                //count = 0;




            }
        });

    }
    private void myFun(){

    }
}