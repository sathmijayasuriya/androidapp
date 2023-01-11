package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edittodo extends AppCompatActivity {

    private Button editsave;
    private EditText edittitle,editdescription;
    private DbHandler dbHandler;
    Context context;
    private long updatedate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittodo);

        //references
        editsave = findViewById(R.id.save1);
        edittitle = findViewById(R.id.title1);
        editdescription = findViewById(R.id.des1);
        context = this;
        dbHandler = new DbHandler(context);


        final String id = getIntent().getStringExtra("id");   //variable ekakt gannwa main eken ena id eka
        // System.out.println(id);
        Todo todo = dbHandler.getsingledata(Integer.parseInt(id)); //dbHandler eke getsinledata() call karama todo object ekak return wenwa ,eke row ekaka okkma data tieynwa.e todo eka methana todo object eka dgnnwa.


        edittitle.setText(todo.getTitle());   //object(todo) eken data gannwa Dbhandler eka return krapu
        editdescription.setText(todo.getDescription());

        editsave.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             String newtitle = edittitle.getText().toString();
             String newdes = editdescription.getText().toString();
             updatedate = System.currentTimeMillis();

                Todo todo = new Todo(Integer.parseInt(id),newtitle,newdes,updatedate,0);
                dbHandler.update(todo);

                startActivity(new Intent(context,MainActivity.class));
    }
});


    }
}