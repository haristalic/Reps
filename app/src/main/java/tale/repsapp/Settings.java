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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import tale.repsapp.base.RepsContract;
import tale.repsapp.base.RepsHelper;

public class Settings extends AppCompatActivity {
    NumberPicker min;
    NumberPicker max;
    int minimum;
    int maximum;
    int mini;
    int maxi;
    int run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
       //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setElevation(0);
        //NumberPicker
        min=(NumberPicker)findViewById(R.id.min);
        max=(NumberPicker)findViewById(R.id.max);
        min.setMinValue(1);
        min.setMaxValue(5);
        min.setWrapSelectorWheel(false);
        max.setMinValue(8);
        max.setMaxValue(20);
        max.setWrapSelectorWheel(false);
        //Postavljanje NumberPickera na zadnje podesavanje
        RepsHelper repsHelper = new RepsHelper(this);

        SQLiteDatabase db =repsHelper.getWritableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM reps",null);
        while (cursor.moveToNext()){
            minimum=cursor.getInt(1);
            maximum=cursor.getInt(2);
            run=cursor.getInt(3);
        }
        cursor.close();

        if (run!=1){
            min.setValue(3);
            max.setValue(10);
        }else{
            min.setValue(minimum);
            max.setValue(maximum);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
        }

//Metod za upis u bazu
    public void save(View view) {
//Uzimanje unosa od korisnika
        minimum=min.getValue();
        maximum=max.getValue();
//Upis u bazu podataka
       RepsHelper repsHelper = new RepsHelper(this);
        SQLiteDatabase db =repsHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RepsContract.ItemTable.COLUMN_NAME_MINREPS,minimum);
        contentValues.put(RepsContract.ItemTable.COLUMN_NAME_MAXREPS, maximum);
        contentValues.put(RepsContract.ItemTable.COLUMN_NAME_FIRSTRUN, 1);
        db.insert(RepsContract.ItemTable.TABLE_NAME, null, contentValues);
        Cursor cursor= db.rawQuery("SELECT * FROM reps",null);
        while (cursor.moveToNext()){
            mini=cursor.getInt(1);
            maxi=cursor.getInt(2);
        }
        cursor.close();
        db.close();
      //  repsHelper.insertData(minimum,maximum,1);
        //Slanje podataka main aktivnosti
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
StyleableToast styleableToast = new StyleableToast(this,"Settings saved",Toast.LENGTH_LONG);
        styleableToast.setBackgroundColor(Color.argb(90,20,125,119));
        styleableToast.setTextColor(Color.rgb(255,255,255));



        styleableToast.show();





    }
}
