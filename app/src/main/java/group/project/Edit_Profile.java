package group.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class Edit_Profile extends AppCompatActivity {

    private EditText name;
    private String namestring;
    private EditText address;
    private String addressstring;
    private EditText number;
    private String numberstring;
    private EditText company;
    private String companystring;
    private EditText description;
    private String descriptionstring;
    private Button enter;
    private Switch license;
    private Boolean switchstate;
    private String state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);
        name= findViewById(R.id.Name);
        address=findViewById(R.id.Address);
        number=findViewById(R.id.Phone_Number);
        company=findViewById(R.id.Company);
        enter=findViewById(R.id.Enter);
        license=findViewById(R.id.License);
        description=findViewById(R.id.Description);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namestring=name.getText().toString();
                addressstring=address.getText().toString();
                numberstring=number.getText().toString();
                companystring=company.getText().toString();
                descriptionstring=description.getText().toString();
                switchstate=license.isChecked();
                if(switchstate==true){
                    state="YES";
                }
                else{
                    state="NO";
                }
                serviceProfile();
            }
        });
    }

    private void serviceProfile(){
        Bundle b = new Bundle();
        b.putString("name", namestring);
        b.putString("address", addressstring);
        b.putString("number", numberstring);
        b.putString("company", companystring);
        b.putString("license", state);
        b.putString("description", descriptionstring);
        Intent intent = new Intent(this, serviceprofile.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
