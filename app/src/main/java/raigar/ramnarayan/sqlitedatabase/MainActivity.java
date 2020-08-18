package raigar.ramnarayan.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
https://www.journaldev.com/9438/android-sqlite-database-example-tutorial
 */

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Context mContext;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        dbManager = new DBManager(mContext);

        btnAdd = findViewById(R.id.btn_add);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddDataActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        fetchList();
    }

    private void fetchList() {
        RecyclerView recyclerView = findViewById(R.id.am_recycler_student_record);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);

        recyclerView.setLayoutManager(manager);

        dbManager.open();
        StudentRecordAdapter adapter = new StudentRecordAdapter(dbManager.fetchFromStudent());

        recyclerView.setAdapter(adapter);
    }
}