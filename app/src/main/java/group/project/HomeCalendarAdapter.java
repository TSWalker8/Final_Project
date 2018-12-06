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

public class HomeCalendarAdapter extends RecyclerView.Adapter<HomeCalendarAdapter.ViewHolder> {

    private ArrayList<HomeCalendarHolder> services;
    private int position;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseUser user= mAuth.getCurrentUser();;
    private HomeCalendarHolder h;

    public HomeCalendarAdapter(ArrayList<HomeCalendarHolder> s){
        this.services=s;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        public final TextView DateandTime;
        public final TextView Service;
        public final TextView ServiceProvider;
        public final ImageButton image;

        public ViewHolder(View view){
            super(view);
            this.view=view;
            DateandTime=view.findViewById(R.id.Date_Time);
            Service=view.findViewById(R.id.Service);
            ServiceProvider=view.findViewById(R.id.ServiceProvider);
            image=view.findViewById(R.id.checkImage);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_4,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        position=i;

        h=services.get(i);
        viewHolder.DateandTime.setText(h.getDateandTime());
        viewHolder.Service.setText(h.getService());
        viewHolder.ServiceProvider.setText(h.getProvider());


        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                services.remove(viewHolder.getLayoutPosition());
                database.child("Users").child(user.getUid()).child(viewHolder.Service.getText().toString()).removeValue();
                notifyItemRemoved(position);

            }
        });
    }



    @Override
    public int getItemCount() {
        if(services!=null){
            return services.size();
        }
        else{
            return 0;
        }
    }
}
