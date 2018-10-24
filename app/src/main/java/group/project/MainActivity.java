package group.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button enter;
    private String userName, password;
    private EditText usernameInput;
    private EditText passwordInput;
    private String choice;
    private boolean proceed= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput= (EditText) findViewById(R.id.userInput);
        passwordInput= (EditText) findViewById(R.id.passwordInput);

        Spinner spinner = findViewById(R.id.Account_Selector);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        enter = (Button) findViewById(R.id.Enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                proceed=true;
            }
        });
        if (choice == "Admin" && proceed==true) {
            WelcomeScreen();

        } else if (choice == "Service Provider" && proceed==true) {
            WelcomeScreen();

        } else if (choice == "Home Owner" && proceed==true) {
            WelcomeScreen();

        }
    }

    public void WelcomeScreen(){
        Intent intent= new Intent(this, Welcome_Screen.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choice= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), choice, Toast.LENGTH_LONG );



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
