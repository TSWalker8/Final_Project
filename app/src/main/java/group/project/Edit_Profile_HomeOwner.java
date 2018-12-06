package group.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Profile_HomeOwner extends AppCompatActivity {

    private EditText name;
    private String namestring;
    private EditText address;
    private String addressstring;
    private EditText number;
    private String numberstring;
    private EditText description;
    private String descriptionstring;
    private Button enter;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile__home_owner);
        name= findViewById(R.id.Name);
        address=findViewById(R.id.Address);
        number=findViewById(R.id.Phone_Number);
        enter=findViewById(R.id.Enter);
        description=findViewById(R.id.Description);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namestring=name.getText().toString();
                addressstring=address.getText().toString();
                numberstring=number.getText().toString();
                descriptionstring=description.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                myRef= FirebaseDatabase.getInstance().getReference();
                user=mAuth.getCurrentUser();
                HomeOwnerInfo h = new HomeOwnerInfo(namestring, addressstring, numberstring, descriptionstring);
                myRef.child("Users").child(user.getUid()).child("Info").setValue(h);

                mainPage();
            }
        });
    }

    private void mainPage(){
        Intent intent = new Intent (this,homeOwnerProfile.class);
        startActivity(intent);

    }

}