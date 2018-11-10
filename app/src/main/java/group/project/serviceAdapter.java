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
import android.widget.Toast;

import java.util.ArrayList;

public class serviceAdapter extends RecyclerView.Adapter<serviceAdapter.ViewHolder> {

    private ArrayList<serviceHolder> services;
    private int position;
    private String kind;
    private String wage;

    public serviceAdapter(ArrayList<serviceHolder> s){
        this.services=s;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        public final EditText service;
        public final EditText hourly;
        public final ImageButton image;
        public final Button change;

        public ViewHolder(View view){
            super(view);
            this.view=view;
            service=view.findViewById(R.id.serviceText);
            hourly=view.findViewById(R.id.hourlyText);
            image=view.findViewById(R.id.checkImage);
            change=view.findViewById(R.id.Change);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_1,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        position=i;

        serviceHolder s=services.get(i);
        viewHolder.service.setText(s.getService());
        viewHolder.hourly.setText(s.getRate());

        viewHolder.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind=viewHolder.service.getText().toString();
                wage=viewHolder.hourly.getText().toString();
                services.get(position).setService(kind);
                services.get(position).setRate(wage);
                notifyItemChanged(position);
            }
        });

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                services.remove(position);
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
