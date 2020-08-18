package raigar.ramnarayan.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {
    private Context mContext;
    private EditText editName, editClass, editRollNo, editMark;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        mContext = this;

        editName = findViewById(R.id.edit_name);
        editClass = findViewById(R.id.edit_class);
        editRollNo = findViewById(R.id.edit_roll_no);
        editMark = findViewById(R.id.edit_marks);

        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager manager = new DBManager(mContext);

                manager.open();
               long l = manager.insertInStudent(editName.getText().toString().trim(), editRollNo.getText().toString()
                , editClass.getText().toString().trim(), Integer.parseInt( editMark.getText().toString().trim()));

               if (l > 0) {
                   Toast.makeText(mContext, "Added", Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}