package group.project;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {

    private ArrayList<String> services;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private String s;
    private String userID;
    private String name;
    private String price;
    private serviceProviderInfo sinfo;
    private serviceHolder h;
    private Context c;

    public ServiceListAdapter(ArrayList<String> s, Context context) {
        this.services = s;
        c=context;
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

        s = services.get(i);
        viewHolder.service.setText(s);

        viewHolder.viewProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, ScheduleServiceList_Providers.class);
                intent.putExtra("service", s);
                c.startActivity(intent);

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