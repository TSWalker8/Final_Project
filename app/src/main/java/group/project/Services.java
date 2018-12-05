package group.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Services extends AppCompatActivity {

    private RecyclerView serviceList;
    private RecyclerView.Adapter serviceAdapter;
    private RecyclerView.LayoutManager serviceLayout;
    private Button enter;
    private EditText service;
    private EditText hourly;
    private String hourlyString, serviceString;
    private ArrayList work;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        FloatingActionButton fab = findViewById(R.id.fab);
        serviceList = findViewById(R.id.serviceAvailable);
        serviceList.setAdapter(serviceAdapter);
        serviceLayout = new LinearLayoutManager(this);
        serviceList.setLayoutManager(serviceLayout);
        serviceList.setHasFixedSize(true);
        service= findViewById(R.id.Service);
        hourly= findViewById(R.id.Time);
        enter=findViewById(R.id.Enter);
        service.setVisibility(View.GONE);
        hourly.setVisibility(View.GONE);
        enter.setVisibility(View.GONE);
        serviceList.setVisibility(View.GONE);
        database.child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                work= new ArrayList();
                serviceAdapter= new serviceAdapter(work);
                serviceList.setAdapter(serviceAdapter);
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding new Service", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                service.setVisibility(View.VISIBLE);
                hourly.setVisibility(View.VISIBLE);
                enter.setVisibility(View.VISIBLE);
                serviceList.setVisibility(View.GONE);
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serviceString = service.getText().toString();
                        hourlyString = hourly.getText().toString();
                        if ((serviceString.length()>=1) && (hourlyString.length()>=1 && hourlyString.matches("[0-9]+"))) {
                            service.setVisibility(View.GONE);
                            hourly.setVisibility(View.GONE);
                            enter.setVisibility(View.GONE);
                            serviceHolder s = new serviceHolder(serviceString,hourlyString);
                            database.child("Services").child(serviceString).setValue(s);
                            work.add(s);
                            serviceList.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            serviceHolder h = ds.getValue(serviceHolder.class);
            work.add(h);
            serviceList.setVisibility(View.VISIBLE);
        }
    }

}
