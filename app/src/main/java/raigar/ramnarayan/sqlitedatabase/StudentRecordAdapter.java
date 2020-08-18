package raigar.ramnarayan.sqlitedatabase;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentRecordAdapter extends RecyclerView.Adapter<StudentRecordAdapter.StudentRecordHolder>{
   private static final String TAG = StudentRecordAdapter.class.getSimpleName();
    private Cursor cursor;
    private Context mContext;

    public StudentRecordAdapter(Cursor cursor) {
        this.cursor = cursor;

        getValues();
    }

    @NonNull
    @Override
    public StudentRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_record, parent, false);
        return new StudentRecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRecordHolder holder, final int position) {

                holder.textMarks.setText(mContext.getResources().getString(R.string.marks) + " " + getValues().get(position).get(3));
                holder.textClass.setText(mContext.getResources().getString(R.string.cl_name) + " " + getValues().get(position).get(2));
                holder.textRoleNo.setText(mContext.getResources().getString(R.string.roll_no_) + " " + getValues().get(position).get(1));
                holder.textName.setText(mContext.getResources().getString(R.string.name) + " " + getValues().get(position).get(0));

                holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, UpdateActivity.class);

                        intent.putExtra("name", getValues().get(position).get(0));
                        intent.putExtra("id", getValues().get(position).get(4));

                        mContext.startActivity(intent);
                    }
                });

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBManager manager = new DBManager(mContext);
                        manager.open();
                      int i =  manager.deleteFromStudent(getValues().get(position).get(4));

                      getValues().remove(position);

                      if (i > 0) {
                          Toast.makeText(mContext, "Deleted", Toast.LENGTH_LONG).show();
                      }
                       notifyDataSetChanged();
                    }
                });
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return getValues().size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class StudentRecordHolder extends RecyclerView.ViewHolder {
        private TextView textName, textClass, textRoleNo, textMarks;
        private Button btnUpdate, btnDelete;

        public StudentRecordHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.isr_text_name);
            textClass = itemView.findViewById(R.id.isr_text_class);
            textRoleNo = itemView.findViewById(R.id.isr_text_roll_no);
            textMarks = itemView.findViewById(R.id.isr_text_marks);

            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    private ArrayList<ArrayList<String>> getValues() {
        ArrayList<ArrayList<String>> arr = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                ArrayList<String> list = new ArrayList<>();

                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));
                list.add(cursor.getString(3));
                list.add(cursor.getString(4));

                arr.add(list);
            } while(cursor.moveToNext());
        }

        return arr;
    }
}
