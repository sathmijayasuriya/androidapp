package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Addtodo extends AppCompatActivity {

    private Button save;
    private EditText title,description ;
    private DbHandler dbHandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtodo);

        //references
        save = findViewById(R.id.save);
        title = findViewById(R.id.title);
        description = findViewById(R.id.des);
        context = this;


        //when the user click the add button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usertitle = title.getText().toString();
                String userdescription = description.getText().toString();
                long started = System.currentTimeMillis();




                  Todo todo = new Todo(usertitle,userdescription,started,0); //todo object captured all the values that user input
                  dbHandler.addtodo(todo);


                startActivity(new Intent(context,MainActivity.class));

            }
        });

    }
}