package group.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        hourly= findViewById(R.id.hourlyRate);
        enter=findViewById(R.id.Enter);
        service.setVisibility(View.GONE);
        hourly.setVisibility(View.GONE);
        enter.setVisibility(View.GONE);
        work= new ArrayList();
        serviceAdapter= new serviceAdapter(work);
        serviceList.setAdapter(serviceAdapter);
        serviceList.setVisibility(View.GONE);

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

}
