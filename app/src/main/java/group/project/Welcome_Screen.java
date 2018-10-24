package group.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Welcome_Screen extends AppCompatActivity {

    private String user;
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras= getIntent().getExtras();
        if (extras != null) {
            System.out.println("Youre inside IF");
            System.out.println(extras.getString("role"));

            user=extras.getString("user");
            choice=extras.getString("role");
            System.out.println(user);
            System.out.println(choice);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);
        TextView Role= (TextView) findViewById(R.id.Role);
        Role.setText(choice);
        TextView Username= (TextView) findViewById(R.id.Username);
        Username.setText(user);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
