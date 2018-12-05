package group.project;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Services_SPView extends AppCompatActivity {

    private RecyclerView serviceList;
    private RecyclerView.Adapter serviceAdapter_SP;
    private RecyclerView.LayoutManager serviceLayout;
    private TextView service;
    private TextView hourly;
    private String hourlyString, serviceString;
    private ArrayList work;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services__spview);

        serviceList = findViewById(R.id.serviceList_UNEDITABLE);
        serviceList.setAdapter(serviceAdapter_SP);
        serviceLayout = new LinearLayoutManager(this);
        serviceList.setLayoutManager(serviceLayout);
        serviceList.setHasFixedSize(true);
        service= findViewById(R.id.Service);
        hourly= findViewById(R.id.Time);
        database.child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                work= new ArrayList();
                serviceAdapter_SP= new serviceAdapter_SP(work);
                serviceList.setAdapter(serviceAdapter_SP);
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
