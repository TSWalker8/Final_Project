package group.project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProviderListAdapter extends RecyclerView.Adapter<ProviderListAdapter.ViewHolder> {

    private ArrayList<ProviderListHolder> providers;
    private int position;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private String s;

    public ProviderListAdapter(ArrayList<ProviderListHolder> p) {
        this.providers = p;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView service;
        public final Button viewProvider;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            service = view.findViewById(R.id.ServiceOffered);
            viewProvider = view.findViewById(R.id.View);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_6, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        position = i;

        s = services.get(i);
        viewHolder.service.setText(s);

        viewHolder.viewProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        if (services != null) {
            return services.size();
        } else {
            return 0;
        }
    }
}
