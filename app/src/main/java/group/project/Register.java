package group.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button enter;
    private String userName, password, email;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText emailInput;
    private String choice="";
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private User user;
    private static final String TAG = "EmailPassword";
    private Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameInput= findViewById(R.id.userInput);
        passwordInput= findViewById(R.id.passwordInput);
        emailInput = findViewById(R.id.emailInput);

        Spinner spinner = findViewById(R.id.Account_Selector);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        enter =  findViewById(R.id.Enter);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        t.makeText(this, "welcome to registration", Toast.LENGTH_SHORT).show();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                email = emailInput.getText().toString();
                user= new User(password, choice, email);
                createAccount();
            }
        });
    }

    public void createAccount(){
        t.makeText(this, "creating", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               t.makeText(Register.this, "onComplete", Toast.LENGTH_SHORT).show();
                boolean b=task.isSuccessful();
                String s=String.valueOf(b);
                t.makeText(Register.this, s, Toast.LENGTH_LONG).show();
                if (task.isSuccessful()) {
                    t.makeText(Register.this, "SUCCESSFUL", Toast.LENGTH_LONG).show();
                    mDatabase.getReference("Users").child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                t.makeText(Register.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();
                               // Log.d(TAG, "createUserWithEmail:success");
                                finish();
                                WelcomeScreen();
                            }
                            if(!task.isSuccessful()){
                              //  t.makeText(Register.this, "UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
                               // Log.w(TAG, "CreateUserWithEmail:failure", task.getException());
                            }
                        }
                    });
                }
                else{
                    //t.makeText(Register.this, "UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "failure", task.getException());
                }
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
