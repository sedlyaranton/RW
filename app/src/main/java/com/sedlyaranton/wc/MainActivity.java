package com.sedlyaranton.wc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sedlyaranton.wc.database.DBHelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;



public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private FloatingActionButton sendBtnAdd;

    private ListView allTasks;
    private ArrayAdapter<String> my_adapter;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        allTasks = (ListView) findViewById(R.id.tasks_list);

        sendBtnAdd = (FloatingActionButton) findViewById(R.id.sendBtnAdd);
        sendBtnAdd.setOnClickListener(this);

        dbHelper = new DBHelper(getApplicationContext());

    }

    @Override
    protected void onResume() {
        super.onResume();

        // откурываем подключение
        db = dbHelper.getReadableDatabase();

        // получаем данные из бд в виде курсора
        userCursor = db.rawQuery("select * from "+ DBHelper.TABLE_REPORTCARD, null);

        //определяем какие столбци из курсора будет выводиться в ListVieew
        String[] headers = new String[]{DBHelper.COLUMN_DATE, DBHelper.COLUMN_CLOCK};

        // создаем адаптер передаем в него курсор
        //не могу разобрать помоему у меня проблемы с adapter
        // мне хочется сделать вывод в ListView она имеет название allTasks

        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        allTasks.setAdapter(userAdapter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //закрываем подключение к курсору
        db.close();
        userCursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendBtnAdd:
                Intent chat1 = new Intent(this, Tabel.class);
                startActivity(chat1);
                break;
            default:
        }

    }
}
