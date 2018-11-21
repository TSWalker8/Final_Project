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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mAuth = FirebaseAuth.getInstance();
        FloatingActionButton fab = findViewById(R.id.fab);
        calendarList = findViewById(R.id.calendarAvailability);
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
        availability= new ArrayList();
        calendarAdapter= new serviceAdapter(availability);
        calendarList.setAdapter(calendarAdapter);
        calendarList.setVisibility(View.GONE);

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
                            u=mAuth.getCurrentUser();
                            database.child("Users").child(u.getUid()).child("Availability").setValue(c);
                            availability.add(c);
                            calendarList.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }

}
