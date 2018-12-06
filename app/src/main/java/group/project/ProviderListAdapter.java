package group.project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProviderListAdapter extends RecyclerView.Adapter<ProviderListAdapter.ViewHolder> {

    private ArrayList<ProviderListHolder> providers;
    private int position;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private ProviderListHolder p;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    public ProviderListAdapter(ArrayList<ProviderListHolder> p) {
        this.providers = p;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final TextView rating;
        public final TextView cost;
        public final TextView availability;
        public final Button Book;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.ServiceProvider);
            rating= view.findViewById(R.id.Rating);
            cost=view.findViewById(R.id.Cost);
            availability=view.findViewById(R.id.Availability);
            Book = view.findViewById(R.id.Book);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_5, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        position = i;

        p = providers.get(i);
        viewHolder.name.setText(p.getName());
        viewHolder.rating.setText(p.getRating());
        viewHolder.availability.setText(p.getAvailability());
        viewHolder.cost.setText(p.getRate());

        viewHolder.Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                user= mAuth.getCurrentUser();
                database.child("Users").child(user.getUid()).child("Services").setValue(providers.get(viewHolder.getLayoutPosition()));
            }
        });

    }


    @Override
    public int getItemCount() {
        if (providers != null) {
            return providers.size();
        } else {
            return 0;
        }
    }
}
