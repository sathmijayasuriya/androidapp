
package com.example.sql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<Todo> {

    private final int resource;
    private final Context context;
    private List<Todo> todoarray ;

    TodoAdapter(Context context, int resource, List<Todo> todoarray ){
        super(context,resource,todoarray);
        this.context = context;
        this.resource = resource;
        this.todoarray = todoarray;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(resource,parent,false);
        //View v = LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);

        TextView title = v.findViewById(R.id.textView1);
        TextView des = v.findViewById(R.id.textView2);
        ImageView image = v.findViewById(R.id.imageView);

        // totoList[obj1,obj2,obj3]
        Todo todo = todoarray.get(position);
        title.setText(todo.getTitle());
        des.setText(todo.getDescription());
        image.setVisibility(v.VISIBLE);

        if(todo.getEnd() > 0){
            image.setVisibility(View.VISIBLE);
        }

        return v;
    }
}

