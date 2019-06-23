package tale.repsapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.Random;

import tale.repsapp.base.RepsHelper;

public class MainActivity extends AppCompatActivity {
    int minimum;
    int maximum;
    int run;
    TextView last;


    TextView myText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setElevation(0);
//Citanje iz baze
        RepsHelper repsHelper = new RepsHelper(this);

        SQLiteDatabase db =repsHelper.getWritableDatabase();

     Cursor cursor= db.rawQuery("SELECT * FROM reps",null);

        while (cursor.moveToNext()){
            minimum=cursor.getInt(1);
            maximum=cursor.getInt(2);
            run=cursor.getInt(3);

        }
        if (run!=1){
            Intent intent =new Intent(this,Settings.class);
            startActivity(intent);

        }
        last=(TextView)findViewById(R.id.last);
        last.setText("Min: "+minimum+" Max: "+maximum);

        cursor.close();
}
//settings click
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Intent intent = new Intent(this,Settings.class);
                startActivity(intent);


                return true;
            default: return super.onOptionsItemSelected(item);

        }

    }


//metoda za generisanje
    public void generate(View view) {
        //Generisanje
        Random random = new Random();
        int ukupno = maximum - minimum +1;
        int reps =random.nextInt(ukupno);
        reps = reps + minimum;

//Postavljanje rezultata
        myText = (TextView)findViewById(R.id.random);
        String myString = String.valueOf(reps);
        myText.setText(myString);
        //Prazna baza
if (run!=1){Intent intent = new Intent(this,Settings.class);
    startActivity(intent);
    StyleableToast styleableToast = new StyleableToast(this,"Please set min and max!",Toast.LENGTH_LONG);
    styleableToast.setBackgroundColor(Color.argb(90,20,125,119));
    styleableToast.setTextColor(Color.rgb(255,255,255));



    styleableToast.show();

}

    }
}
