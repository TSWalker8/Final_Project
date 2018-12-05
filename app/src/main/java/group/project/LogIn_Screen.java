package group.project;

import android.content.Intent;
import android.nfc.Tag;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LogIn_Screen extends AppCompatActivity {

    private Button enter;
    private String password, email;
    private EditText passwordInput;
    private EditText emailInput;
    private String choice="";
    private Button Register;
    private FirebaseAuth mAuth;
    private Toast t;
    private static final String TAG = "LOGIN";
    private DatabaseReference myRef;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        emailInput=findViewById(R.id.emailInput);
        passwordInput= findViewById(R.id.passwordInput);

        enter = findViewById(R.id.Enter);
        Register= findViewById((R.id.Register));

        mAuth = FirebaseAuth.getInstance();


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = passwordInput.getText().toString();
                email=emailInput.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogIn_Screen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    finish();
                                    WelcomeScreen();
                                }
                                else{
                                    t.makeText(LogIn_Screen.this, "Could not log in", Toast.LENGTH_SHORT).show();
                                    Log.w(TAG, "failure", task.getException());
                                }
                            }
                        });
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }




    public void WelcomeScreen(){
        Intent intent = new Intent(this, Welcome_Screen.class);
        startActivity(intent);
    }

    public void Register(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}
