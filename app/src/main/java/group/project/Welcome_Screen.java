package group.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Welcome_Screen extends AppCompatActivity {

    private String user;
    private String choice;
    private Button Continue;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseUser user1;
    private Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);
        mAuth = FirebaseAuth.getInstance();
        user1= mAuth.getCurrentUser();
        myRef= FirebaseDatabase.getInstance().getReference();
        myRef.child("Users").child(user1.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User u =dataSnapshot.getValue(User.class);
                choice=u.getRole();
                user=u.getUserName();
                TextView Role=  findViewById(R.id.Role);
                Role.setText(choice);
                TextView Username=  findViewById(R.id.Username);
                Username.setText(user);
                t.makeText(Welcome_Screen.this, choice, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Continue= findViewById(R.id.Continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                if (choice.equals("Admin")) {
                    addServices();
                }
                else if (choice.equals("Service Provider")){
                    serviceProfile();
                }

            }
        });

    }

    public void addServices(){
        Intent intent= new Intent(this, Services.class);
        startActivity(intent);
    }

    public void serviceProfile(){
        Intent intent= new Intent(this, serviceprofile.class);
        startActivity(intent);
    }


    /*@Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }*/
}
