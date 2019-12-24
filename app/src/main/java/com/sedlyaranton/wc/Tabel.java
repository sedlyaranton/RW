package com.sedlyaranton.wc;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sedlyaranton.wc.database.DBHelper;

import java.util.Calendar;


public class Tabel extends AppCompatActivity implements View.OnClickListener {
    private EditText ed_text,ed_clock,ed_data;
    private Button btn_save,btn_cancellation;
    private ImageButton imageButton;
    private TextView titleCard;

    private int mYear, mMonth, mDay;

   // DBHelper dbHelper;
    DBHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabel);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleCard = (TextView) findViewById(R.id.title_card);
        ed_data = (EditText) findViewById(R.id.ed_data);
        ed_clock = (EditText) findViewById(R.id.ed_clock);
        ed_text = (EditText) findViewById(R.id.ed_text);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancellation = (Button) findViewById(R.id.btn_cancellation);
        imageButton = (ImageButton) findViewById(R.id.image_calend);
        imageButton.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_cancellation.setOnClickListener(this);

        //dbHelper = new DBHelper(this);
        sqlHelper = new DBHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DBHelper.TABLE_REPORTCARD + " where " +
                    DBHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            ed_data.setText(userCursor.getString(1));
            ed_clock.setText(String.valueOf(userCursor.getInt(2)));
            ed_text.setText(userCursor.getString(3));
            userCursor.close();
        } else {

        }

    }

    public void save(){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_DATE, ed_data.getText().toString());
        cv.put(DBHelper.COLUMN_CLOCK, Integer.parseInt(ed_clock.getText().toString()));
        cv.put(DBHelper.COLUMN_TEXT, ed_text.getText().toString());

        if (userId > 0) {
            db.update(DBHelper.TABLE_REPORTCARD, cv, DBHelper.COLUMN_ID + "=" + userId, null);
        } else {
            db.insert(DBHelper.TABLE_REPORTCARD, null, cv);
        }
        goHome();
    }
    private void goHome(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

       // String editDate = ed_data.getText().toString();
       // String editClok = ed_clock.getText().toString();
       // String editText = ed_text.getText().toString();

        //SQLiteDatabase database = dbHelper.getWritableDatabase();

        //ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.image_calend:
                callDatePicker();
                break;
            case R.id.btn_save:
                save();
                break;
        }

    }

    private void callDatePicker() {
        // получаем текущую дату
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        // инициализируем диалог выбора даты текущими значениями
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                        ed_data.setText(editTextDateParam);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
