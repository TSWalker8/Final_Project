package group.project;

import android.content.Intent;
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
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddServicesToProvider extends AppCompatActivity {

    private RecyclerView serviceList;
    private RecyclerView.Adapter serviceAdapter_SPView;
    private RecyclerView.LayoutManager serviceLayout;
    private Button enter;
    private EditText service;
    private EditText hourly;
    private String hourlyString, serviceString;
    private ArrayList work;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services_to_provider);

        mAuth = FirebaseAuth.getInstance();
        user= mAuth.getCurrentUser();
        FloatingActionButton fab = findViewById(R.id.fab);
        serviceList = findViewById(R.id.serviceList_SPView);
        work=new ArrayList();
        serviceAdapter_SPView=new serviceAdapter_SPView(work);
        serviceList.setAdapter(serviceAdapter_SPView);
        serviceLayout = new LinearLayoutManager(this);
        serviceList.setLayoutManager(serviceLayout);
        serviceList.setHasFixedSize(true);
        service= findViewById(R.id.Service);
        hourly= findViewById(R.id.Time);
        enter=findViewById(R.id.Enter);
        database.child("Users").child(user.getUid()).child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                work= new ArrayList();
                serviceAdapter_SPView= new serviceAdapter_SPView(work);
                serviceList.setAdapter(serviceAdapter_SPView);
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
                Intent intent = new Intent(AddServicesToProvider.this, Services_SPView.class);
                startActivity(intent);
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
