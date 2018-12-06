package group.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeOwnerCalendar extends AppCompatActivity {

    private RecyclerView serviceList;
    private RecyclerView.Adapter HomeCalendarAdapter;
    private RecyclerView.LayoutManager serviceLayout;
    private String appointmentstring, servicestring, providerstring;
    private ArrayList services;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_calendar);

        serviceList = findViewById(R.id.workbeingdone);
        services=new ArrayList();
        HomeCalendarAdapter=new HomeCalendarAdapter(services);
        serviceList.setAdapter(HomeCalendarAdapter);
        serviceLayout = new LinearLayoutManager(this);
        serviceList.setLayoutManager(serviceLayout);
        serviceList.setHasFixedSize(true);
        mAuth = FirebaseAuth.getInstance();
        user= mAuth.getCurrentUser();
        Toast.makeText(this, "Press X when service Completed and Rate", Toast.LENGTH_LONG).show();
        database.child("Users").child(user.getUid()).child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services= new ArrayList();
                HomeCalendarAdapter= new HomeCalendarAdapter(services);
                serviceList.setAdapter(HomeCalendarAdapter);
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            HomeCalendarHolder h = ds.getValue(HomeCalendarHolder.class);
            services.add(h);
            serviceList.setVisibility(View.VISIBLE);
        }
    }

}
