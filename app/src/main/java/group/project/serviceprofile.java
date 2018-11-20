package group.project;

import android.content.Intent;
import android.os.Bundle;
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

public class serviceprofile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String name;
    private String address;
    private String number;
    private String company;
    private String license;
    private String description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras= getIntent().getExtras();
        if (extras != null) {
            name=extras.getString("name");
            address=extras.getString("address");
            number=extras.getString("number");
            company=extras.getString("company");
            license=extras.getString("license");
            description=extras.getString("description");

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprofile);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView nameInput=findViewById(R.id.nameInput);
        nameInput.setText(name);
        TextView addressInput=findViewById(R.id.addressInput);
        addressInput.setText(address);
        TextView numberInput=findViewById(R.id.phonenumberInput);
        numberInput.setText(number);
        TextView companyInput=findViewById(R.id.companyInput);
        companyInput.setText(company);
        TextView licenseInput=findViewById(R.id.licenseInput);
        licenseInput.setText(license);
        TextView descriptionInput=findViewById(R.id.Description);
        licenseInput.setText(license);
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
        getMenuInflater().inflate(R.menu.serviceprofile, menu);
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
            Intent intent= new Intent (this, Calendar_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_addservice) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
