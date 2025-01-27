package com.example.heartpulsesynch;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataEntry extends AppCompatActivity {
    Button saveButton;
    EditText edtx1, edtx2, edtx3, edtx4, edtx5, edtx6;

    SharedPreferences sharedPreferences;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        saveButton = findViewById(R.id.addButton);
        edtx1 = findViewById(R.id.dateValue);
        edtx2 = findViewById(R.id.systolicValue);
        edtx3 = findViewById(R.id.diastolicValue);
        edtx4 = findViewById(R.id.heartRateValue);
        edtx5 = findViewById(R.id.timeValue);
        edtx6 = findViewById(R.id.commentValue);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        edtx1.setText(dateFormat.format(calendar.getTime()));
        edtx5.setText(timeFormat.format(calendar.getTime()));


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputFormat();
            }
        });
    }

    private void inputFormat() {
        String date = edtx1.getText().toString();
        String time = edtx5.getText().toString();
        String systolic = edtx2.getText().toString();
        String diastolic = edtx3.getText().toString();
        String bloodPressure = edtx4.getText().toString();
        String comment = edtx6.getText().toString();

        if (TextUtils.isEmpty(date)) {
            edtx1.setError("The field must be required");
            return;
        }
        if (TextUtils.isEmpty(time)) {
            edtx5.setError("The field must be required");
            return;
        }
        if (TextUtils.isEmpty(systolic) || !(Integer.parseInt(systolic) >= 0 && Integer.parseInt(systolic) <= 200)) {
            edtx2.setError("Invalid data format added");
            return;
        }
        if (TextUtils.isEmpty(diastolic) || !(Integer.parseInt(diastolic) >= 0 && Integer.parseInt(diastolic) <= 150)) {
            edtx3.setError("Invalid data format added");
            return;
        }
        if (TextUtils.isEmpty(bloodPressure) || !(Integer.parseInt(bloodPressure) >= 0 && Integer.parseInt(bloodPressure) <= 150)) {
            edtx4.setError("Invalid data format added");
            return;
        }

        ModelClass modelClass = new ModelClass(date, time, systolic, diastolic, bloodPressure, comment);
        new RecordList().addRecord(modelClass);

        sharedPreferences = getSharedPreferences("96-117", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(RecordList.mcl);
        editor.putString("96-117", jsonString);
        editor.apply();

        MainActivity.adapter.notifyDataSetChanged();
        Toast.makeText(DataEntry.this, "Data Insertion Successful", Toast.LENGTH_LONG).show();
        finish();
    }
}
