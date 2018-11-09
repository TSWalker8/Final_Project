package group.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Services extends AppCompatActivity {

    private RecyclerView serviceList;
    private Button enter;
    private EditText service;
    private EditText hourly;
    private String hourlyString, serviceString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        serviceList = findViewById(R.id.serviceAvailable);
        service= findViewById(R.id.Service);
        hourly= findViewById(R.id.hourlyRate);
        enter=findViewById(R.id.Enter);
        service.setVisibility(View.GONE);
        hourly.setVisibility(View.GONE);
        enter.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding new Service", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                service.setVisibility(View.VISIBLE);
                hourly.setVisibility(View.VISIBLE);
                enter.setVisibility(View.VISIBLE);
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serviceString = service.getText().toString();
                        hourlyString = hourly.getText().toString();
                        service.setVisibility(View.GONE);
                        hourly.setVisibility(View.GONE);
                        enter.setVisibility(View.GONE);

                    }
                });
            }
        });
    }

}
