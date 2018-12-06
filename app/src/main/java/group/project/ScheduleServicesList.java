package group.project;

import android.icu.text.IDNA;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScheduleServicesList extends AppCompatActivity {

    private RecyclerView serviceList;
    private RecyclerView providerList;
    private RecyclerView.Adapter ServiceListAdapter;
    private RecyclerView.Adapter ProviderListAdapter;
    private RecyclerView.LayoutManager Layout;
    private ArrayList services;
    private ArrayList providers;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_services_list);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        serviceList = findViewById(R.id.Services);
        services=new ArrayList();
        ServiceListAdapter= new ServiceListAdapter(services, this);
        serviceList.setAdapter(ServiceListAdapter);
        Layout = new LinearLayoutManager(this);
        serviceList.setLayoutManager(Layout);
        serviceList.setHasFixedSize(true);


        database.child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services = new ArrayList();
                ServiceListAdapter = new ServiceListAdapter(services, ScheduleServicesList.this);
                serviceList.setAdapter( ServiceListAdapter);
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void showData (DataSnapshot dataSnapshot){
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            serviceHolder s = ds.getValue(serviceHolder.class);
            services.add(s.getService());
        }
    }

}
