package com.example.sql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //create objects
   private ListView listView;
   private Button addbutton ;
   private TextView counttext ;
   private DbHandler dbHandler;
   Context context;
   private List<Todo> todolist ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //references
    addbutton = findViewById(R.id.add);
    counttext = findViewById(R.id.count);
    context = this;
    listView = findViewById(R.id.listview);
    dbHandler = new DbHandler(context); //  DbHandler dbHandler = new DbHandler(this);


        //count display
    int count = dbHandler.countdodo();
    counttext.setText("you have "+count+ " todos");//it will display on your main activity


     //all the single rows display
    todolist  = new ArrayList<>();
    todolist = dbHandler.getall();

    TodoAdapter todoAdapter = new TodoAdapter(context,R.layout.singlerow,todolist );
    listView.setAdapter(todoAdapter);




        //add button
    addbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(context,Addtodo.class));//final Intent intent = new Intent(this,Addtodo.class); up
        }
    });

        //one single row click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Todo todo= todolist.get(i); //todo object ekta ,todolist array eken position ekta hariyana data tika gannwa

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(todo.getTitle());  //alert box eka click krama ape tile ekai des ekai dosplay krgnnwa alert box eke
                builder.setMessage(todo.getDescription());

                //delete button
                builder.setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.delete(todo.getId());
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });

                //update button
                builder.setNeutralButton("update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(context,Edittodo.class); //navigate to Edit page
                        intent.putExtra("id",String.valueOf(todo.getId())); //id eka string ekak krala edit page ekta yawanne
                        startActivity(intent);
                    }
                });

                //finish button
                builder.setPositiveButton("finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,MainActivity.class);
                    }
                });

                builder.show();
            }
        });

    }
}