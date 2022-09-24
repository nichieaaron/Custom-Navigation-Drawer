package com.shrikanthravi.customnavigationdrawer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Transactions extends AppCompatActivity {
    public Transactions() {
    }

    public void add_to_cart(Context context)
    {
        Toast.makeText(context,"added to cart",Toast.LENGTH_SHORT).show();
    }

    public void view_cart(Context context){
        Toast.makeText(context,"shopping cart",Toast.LENGTH_SHORT).show();
    }
}
