package group.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScheduleServiceList_Providers extends AppCompatActivity {


    private RecyclerView providerList;
    private RecyclerView.Adapter ProviderListAdapter;
    private RecyclerView.LayoutManager Layout;
    private ArrayList<ProviderListHolder> providers;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String s;
    private String userID;
    private String name;
    private String cost;
    private String availability;
    private String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_service_list__providers);
        if(getIntent().hasExtra("service")){
            s=getIntent().getStringExtra("service");
        }
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        providerList = findViewById(R.id.providerList);
        providers=new ArrayList<ProviderListHolder>();
        ProviderListAdapter= new ProviderListAdapter(providers);
        providerList.setAdapter(ProviderListAdapter);
        Layout = new LinearLayoutManager(this);
        providerList.setLayoutManager(Layout);
        providerList.setHasFixedSize(true);
        ProviderListAdapter= new ProviderListAdapter(providers);


        database.child("Services").child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                providers = new ArrayList<ProviderListHolder>();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    userID=ds.toString();
                    database.child("Users").child(userID).child("info").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            serviceProviderInfo s= dataSnapshot.getValue(serviceProviderInfo.class);
                            name=s.getName();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    database.child("Users").child(userID).child("Services").child(s).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            serviceHolder s= dataSnapshot.getValue(serviceHolder.class);
                            cost=s.getRate();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    database.child("Users").child(userID).child("Availability").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds: dataSnapshot.getChildren()){
                                calendarHolder c = ds.getValue(calendarHolder.class);
                                availability=c.toString();
                                ProviderListHolder p= new ProviderListHolder(name,cost,availability,"5");
                                providers.add(p);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ProviderListAdapter = new ProviderListAdapter(providers);
        providerList.setAdapter(ProviderListAdapter);

    }
}
