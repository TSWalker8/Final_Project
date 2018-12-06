package group.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class homeOwnerProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String name;
    private String address;
    private String number;
    private String description;
    private DatabaseReference myRef;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private Toast t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_profile);
        mAuth = FirebaseAuth.getInstance();
        user= mAuth.getCurrentUser();
        myRef= FirebaseDatabase.getInstance().getReference();
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                homeOwnerProfile.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(homeOwnerProfile.this);
        myRef.child("Users").child(user.getUid()).child("Info").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HomeOwnerInfo h =dataSnapshot.getValue(HomeOwnerInfo.class);
                if(h==null){
                    firstSetup();
                }
                else{
                    TextView nameInput=findViewById(R.id.nameInput);
                    nameInput.setText(h.getName());
                    TextView addressInput=findViewById(R.id.addressInput);
                    addressInput.setText(h.getAddress());
                    TextView numberInput=findViewById(R.id.phonenumberInput);
                    numberInput.setText(h.getNumber());
                    TextView descriptionInput=findViewById(R.id.Description);
                    descriptionInput.setText(h.getDescription());
                    TextView nameheader= findViewById(R.id.nameheader);
                    nameheader.setText(h.getName());
                    TextView numberheader= findViewById(R.id.numberheader);
                    numberheader.setText(h.getNumber());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_owner_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calendar) {
            Intent intent1= new Intent (this, HomeOwnerCalendar.class);
            startActivity(intent1);
        } else if (id == R.id.nav_addservice) {
            Toast.makeText(this, "NOT YET IMPLEMENTED", Toast.LENGTH_LONG).show();
            //Intent intent2= new Intent (this, AddServicesToProvider.class);
            //startActivity(intent2);
        } else if (id == R.id.nav_manage) {
            Intent intent3 = new Intent(this, Edit_Profile_HomeOwner.class);
            startActivity(intent3);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void firstSetup(){
        Intent intent = new Intent(this, Edit_Profile_HomeOwner.class);
        startActivity(intent);
    }
}
