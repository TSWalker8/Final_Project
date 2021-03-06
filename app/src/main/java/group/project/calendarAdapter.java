package group.project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class calendarAdapter extends RecyclerView.Adapter<calendarAdapter.ViewHolder> {

    private ArrayList<calendarHolder> dates;
    private int position;
    private String date;
    private String time;
    private calendarHolder c;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public calendarAdapter(ArrayList<calendarHolder> c){
        this.dates=c;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        public final TextView date;
        public final TextView time;
        public final ImageButton image;

        public ViewHolder(View view){
            super(view);
            this.view=view;
            date=view.findViewById(R.id.Date);
            time=view.findViewById(R.id.Time);
            image=view.findViewById(R.id.checkImage);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        position=i;

        System.out.println(i);
        c=dates.get(i);
        System.out.println(c.getDate());
        System.out.println(c.getDate());
        viewHolder.date.setText(c.getDate());
        viewHolder.time.setText(c.getHours());

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=dates.get(viewHolder.getLayoutPosition());
                position=viewHolder.getLayoutPosition();
                dates.remove(position);
                mAuth = FirebaseAuth.getInstance();
                user= mAuth.getCurrentUser();
                database.child("Users").child(user.getUid()).child("Availability").child(c.getDate()+ " "+c.getHours()).removeValue();
                notifyItemRemoved(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        if(dates!=null){
            return dates.size();
        }
        else{
            return 0;
        }
    }
}
