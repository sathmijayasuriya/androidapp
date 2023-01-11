package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

private static final int VERSION = 1;
private static final String DB_NAME = "todo";
private static final String TABLE_NAME = "todo";

//table column names
private static final String ID = "id";
private static final String TITLE = "title";
private static final String DESCRIPTION = "description";
private static final String STARTED = "started";
private static final String FINISHED = "finished";

//constructor
    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null , VERSION);
    }

//create table code
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

       String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+"" +
               " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
               " "+TITLE+" TEXT, " +
               ""+DESCRIPTION+" TEXT," +
               " "+STARTED+" TEXT," +
               " "+FINISHED+" TEXT ); ";
       sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);
 /*
 CREATE TABLE todo(id INTEGER PRIMARY KEY AUTOINCREMENT,
               title TEXT,
               description TEXT,
               started TEXT,
               finished TEXT);

               sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"" +
               " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
               " "+TITLE+" TEXT, " +
               ""+DESCRIPTION+" TEXT," +
               " "+STARTED+" TEXT," +
               " "+FINISHED+" TEXT ); ");
  */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//i --> old version
//i1 --> new Version

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " +TABLE_NAME; //drop table if existed
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        onCreate(sqLiteDatabase); //create table again

    }




    //add method
    public void addtodo(Todo todo){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); //save data
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,todo.getTitle());
        contentValues.put(DESCRIPTION,todo.getDescription());
        contentValues.put(STARTED,todo.getStart() );
        contentValues.put(FINISHED,todo.getEnd());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    //close database
        sqLiteDatabase.close();
}


    //return number of columns in table
    public int countdodo(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String  QUERY = "SELECT * FROM " +TABLE_NAME ;

        Cursor cursor = sqLiteDatabase.rawQuery( QUERY,null);
        return cursor.getCount();


    }

    //return display values
    public List<Todo> getall(){

        List<Todo> todoarray = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String QUERY = "SELECT * FROM "+TABLE_NAME ;

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);

        if(cursor.moveToFirst()){

            do{
                Todo todo = new Todo(); //create new Todo object

                todo.setId(cursor.getInt(0));
                todo.setTitle(cursor.getString(1));
                todo.setDescription(cursor.getString(2));
                todo.setStart(cursor.getLong(3));
                todo.setEnd(cursor.getLong(4));

                todoarray.add(todo);

            }while (cursor.moveToNext());
        }
        return todoarray; //return karanne array ekak
    }



    //DELETE ONE ITEM
    public void delete(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    //get single data and display them on edit page
    public Todo getsingledata(int id){

        Cursor cursor;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION,STARTED,FINISHED },
                         ID + "= ?", new String[]{String.valueOf(id)},
                         null, null, null, null);   //eyala dena id ekata adala columns wala values okkma gannwa cursor object ekata

        Todo todo;
             if(cursor != null){  //cursor object eka null nameee  nam todo object ekata
                 cursor.moveToFirst();

                 todo = new Todo(    //todo model ekata data yawanwa,adaala column eken

                  cursor.getInt(0),
                  cursor.getString(1),
                  cursor.getString(2),
                  cursor.getLong(3),
                  cursor.getLong(4)
             );
                 //System.out.println(todo.getTitle());
             return todo;
             }
                return null;

        }


    //update data
 public int update(Todo todo){

    SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    ContentValues contentValues = new ContentValues();

     contentValues.put(TITLE,todo.getTitle());
     contentValues.put(DESCRIPTION,todo.getDescription());
     contentValues.put(STARTED,todo.getStart() );
     contentValues.put(FINISHED,todo.getEnd());

 int  status = sqLiteDatabase.update(TABLE_NAME,contentValues,ID + "=?",new String[]{String.valueOf(todo.getId()) });

 sqLiteDatabase.close();
     return status;
 }
}