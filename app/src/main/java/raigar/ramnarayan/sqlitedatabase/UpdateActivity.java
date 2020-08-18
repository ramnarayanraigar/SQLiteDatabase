package raigar.ramnarayan.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private static final String TAG = UpdateActivity.class.getSimpleName();
    private Context mContext;
    private EditText editName, editClass, editRollNo, editMark;
    private Button btnAdd;

    private String id, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        mContext = this;

        editName = findViewById(R.id.edit_name);
        editClass = findViewById(R.id.edit_class);
        editRollNo = findViewById(R.id.edit_roll_no);
        editMark = findViewById(R.id.edit_marks);

        editClass.setVisibility(View.GONE);
        editRollNo.setVisibility(View.GONE);
        editMark.setVisibility(View.GONE);

        btnAdd = findViewById(R.id.btn_add);

        getIntent(getIntent());

        editName.setText(name);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager manager = new DBManager(mContext);

                manager.open();
               int i = manager.updateIntoStudent((Integer.parseInt(id)), editName.getText().toString().trim());

                if (i > 0) {
                    Toast.makeText(mContext, "Updated successfully", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getIntent(Intent intent) {
        if (intent != null) {
            id = intent.getStringExtra("id");
            name = intent.getStringExtra("name");
        }
    }
}