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


    public class serviceAdapter_SPView extends RecyclerView.Adapter<serviceAdapter_SPView.ViewHolder> {

        private ArrayList<serviceHolder> services;
        private int position;
        private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        private serviceHolder s;
        private FirebaseUser user;
        private FirebaseAuth mAuth;

        public serviceAdapter_SPView(ArrayList<serviceHolder> s){
            this.services=s;
        }
        public static class ViewHolder extends RecyclerView.ViewHolder{
            public final View view;
            public final TextView service;
            public final TextView hourly;
            public final ImageButton image;

            public ViewHolder(View view){
                super(view);
                this.view=view;
                service=view.findViewById(R.id.serviceText);
                hourly=view.findViewById(R.id.hourlyText);
                image=view.findViewById(R.id.checkImage);

            }
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_2,viewGroup,false);

            return new serviceAdapter_SPView.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
            position=i;

            s=services.get(i);
            viewHolder.service.setText(s.getService());
            viewHolder.hourly.setText(s.getRate());


            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   System.out.println(viewHolder.getLayoutPosition());
                   s=services.get(viewHolder.getLayoutPosition());
                    services.remove(viewHolder.getLayoutPosition());
                    mAuth = FirebaseAuth.getInstance();
                    user= mAuth.getCurrentUser();
                    database.child("Users").child(user.getUid()).child("Services").child(s.getService()).removeValue();
                    database.child("Services").child(s.getService()).child("Service Providers").child(user.getUid()).removeValue();
                    notifyItemRemoved(viewHolder.getLayoutPosition());
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

