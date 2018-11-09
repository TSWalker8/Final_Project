package group.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class Welcome_Screen extends AppCompatActivity {

    private String user;
    private String choice;
    private Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras= getIntent().getExtras();
        if (extras != null) {
            user=extras.getString("user");
            choice=extras.getString("role");

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);
        TextView Role=  findViewById(R.id.Role);
        Role.setText(choice);
        TextView Username=  findViewById(R.id.Username);
        Username.setText(user);
        Continue= findViewById(R.id.Continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                if (choice == "admin") {
                    System.out.println("HELLO");
                    addServices();
                }

            }
        });

    }

    public void addServices(){
        Intent intent= new Intent(this, Services.class);
        startActivity(intent);
    }
    /*@Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }*/
}
