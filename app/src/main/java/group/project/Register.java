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

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button enter;
    private String userName, password;
    private EditText usernameInput;
    private EditText passwordInput;
    private String choice="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameInput= findViewById(R.id.userInput);
        passwordInput= findViewById(R.id.passwordInput);

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
                WelcomeScreen();
            }
        });
    }

    public void WelcomeScreen(){
        Bundle b = new Bundle();
        b.putString("role", choice);
        b.putString("user", userName);
        Intent intent = new Intent(this, Welcome_Screen.class);
        intent.putExtras(b);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choice= parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
