package group.project;

import android.os.Bundle;
import android.provider.ContactsContract;
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

public class Calendar extends AppCompatActivity {

    private RecyclerView calendarList;
    private RecyclerView.Adapter calendarAdapter;
    private RecyclerView.LayoutManager calendarLayout;
    private Button enter;
    private EditText date;
    private EditText time;
    private String dateString, timeString;
    private ArrayList availability;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser u;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        FloatingActionButton fab = findViewById(R.id.fab);
        calendarList = findViewById(R.id.calendarAvailability);
        calendarAdapter= new calendarAdapter(availability);
        calendarList.setAdapter(calendarAdapter);
        calendarLayout = new LinearLayoutManager(this);
        calendarList.setLayoutManager(calendarLayout);
        calendarList.setHasFixedSize(true);
        time= findViewById(R.id.Time);
        date= findViewById(R.id.Date);
        enter=findViewById(R.id.Enter);
        time.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        enter.setVisibility(View.GONE);

        database.child("Users").child(user.getUid()).child("Availability").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                availability= new ArrayList();
                calendarAdapter= new calendarAdapter(availability);
                calendarList.setAdapter(calendarAdapter);
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding an Event", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                date.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                enter.setVisibility(View.VISIBLE);
                calendarList.setVisibility(View.GONE);
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dateString = date.getText().toString();
                        timeString = time.getText().toString();
                        if ((timeString.length()>=1) && dateString.length()>=1) {
                            date.setVisibility(View.GONE);
                            time.setVisibility(View.GONE);
                            enter.setVisibility(View.GONE);
                            calendarHolder c = new calendarHolder(dateString,timeString);
                            database.child("Users").child(user.getUid()).child("Availability").child(dateString+" "+timeString).setValue(c);
                            availability.add(c);
                            calendarList.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot){
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            calendarHolder c = ds.getValue(calendarHolder.class);
            System.out.println(c.getDate());
            availability.add(c);
            calendarList.setVisibility(View.VISIBLE);
        }
    }

}
