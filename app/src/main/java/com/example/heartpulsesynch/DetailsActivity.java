package com.example.heartpulsesynch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<ModelClass> mcl = RecordList.mcl;
    ModelClass modelClass;
    TextView dateT,timeT,systolicT,diastolicT,heartRateT,commentT;

    /**
     * creating the form where details of the
     * given data will show
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);

        dateT= findViewById(R.id.DdateValue);
        timeT = findViewById(R.id.DtimeValue);
        systolicT = findViewById(R.id.DsystolicValue);
        diastolicT = findViewById(R.id.DdiastolicValue);
        heartRateT = findViewById(R.id.DheartRateValue);
        commentT = findViewById(R.id.DcommentValue);


        retrieveData();

        modelClass = mcl.get(index);
        dateT.setText(modelClass.getDate());
        timeT.setText(modelClass.getTime());
        systolicT.setText(modelClass.getSystolic());
        diastolicT.setText(modelClass.getDiastolic());
        heartRateT.setText(modelClass.getBloodPressure());
        commentT.setText(modelClass.getComment());

    }

    /**
     * Data retrieve from shared preference
     */

    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("96-117",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("96-117",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        mcl = gson.fromJson(jsonString,type);
        if(mcl ==null)
        {
            mcl = new ArrayList<>();
        }
    }
}