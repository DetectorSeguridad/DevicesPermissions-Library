package com.gdetector.devicespermissions;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gdetector.devicespermissionslibrary.DevicesPermissionActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    public void getData() {


        List<String> firstGroup = new ArrayList<String>();
        firstGroup.add("1. First row");
        firstGroup.add("2. Second row");
        firstGroup.add("3. Third row");

        List<String> secondGroup = new ArrayList<String>();
        secondGroup.add("1. First row");
        secondGroup.add("2. Second row");

        expandableListDetail.put("FIRST GROUP", firstGroup);
        expandableListDetail.put("SECOND GROUP", secondGroup);


        //TODO Refactor
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                new DevicesPermissionActivity(expandableListDetail);
            }
        }, 100);
    }
}
