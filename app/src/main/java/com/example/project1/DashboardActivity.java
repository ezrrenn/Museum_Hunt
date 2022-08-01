package com.example.project1;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project1.databinding.ActivityDashboardBinding;

import java.util.ArrayList;


public class DashboardActivity extends Menu {

    CardView home1,home2,home3,home4,home5,home6;

    ActivityDashboardBinding activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");

        home1 = findViewById(R.id.malay_home);
        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, bookActivity.class);
                startActivity(intent);
                //startActivity(new Intent(DashboardActivity.this, Museum_Info.class));
            }
        });

        home2 = findViewById(R.id.ho_home);
        home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, bookActivity.class);
                startActivity(intent);
                //startActivity(new Intent(DashboardActivity.this, Museum_Info.class));
            }
        });

        home3 = findViewById(R.id.baba_home);
        home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, bookActivity.class);
                startActivity(intent);
                //startActivity(new Intent(DashboardActivity.this, Museum_Info.class));
            }
        });

        home4 = findViewById(R.id.flora_home);
        home4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, bookActivity.class);
                startActivity(intent);
                //startActivity(new Intent(DashboardActivity.this, Museum_Info.class));
            }
        });


        home5 = findViewById(R.id.prison_home);
        home5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, bookActivity.class);
                startActivity(intent);
                //startActivity(new Intent(DashboardActivity.this, Museum_Info.class));
            }
        });

        home6 = findViewById(R.id.stad_home);
        home6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, bookActivity.class);
                startActivity(intent);
                //startActivity(new Intent(DashboardActivity.this, Museum_Info.class));
            }
        });

    }
}