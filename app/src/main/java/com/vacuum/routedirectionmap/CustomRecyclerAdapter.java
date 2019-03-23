package com.vacuum.routedirectionmap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vacuum.routedirectionmap.models.Routes;

import java.util.List;


public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Routes>  routes;

    public CustomRecyclerAdapter(Context context, List routes) {
        this.context = context;
        this.routes = routes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(routes.get(position));

        Routes rt = routes.get(position);

        holder.currency.setText(rt.getFare());
        holder.legs.setText(rt.getLegs());
        //holder.pJobProfile.setText(pu.getJobProfile());

    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView currency;
        public TextView legs;

        public ViewHolder(View itemView) {
            super(itemView);

            currency = (TextView) itemView.findViewById(R.id.currency);
            legs = (TextView) itemView.findViewById(R.id.legs);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Routes cpu = (Routes) view.getTag();

                    Toast.makeText(view.getContext(), cpu.getFare()+" "+cpu.getLegs(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}