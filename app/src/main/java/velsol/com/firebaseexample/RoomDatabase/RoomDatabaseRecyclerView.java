package velsol.com.firebaseexample.RoomDatabase;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import velsol.com.firebaseexample.R;

public class RoomDatabaseRecyclerView extends RecyclerView.Adapter<RoomDatabaseRecyclerView.ViewHolder>
{
    Context context;
    List<User> users;

    public RoomDatabaseRecyclerView(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public RoomDatabaseRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_data_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoomDatabaseRecyclerView.ViewHolder holder, int position)
    {

        holder.t1.setText(""+users.get(position).getName());
        holder.t2.setText(""+users.get(position).getEmail());
        holder.t3.setText(""+users.get(position).getMobile());
    }

    @Override
    public int getItemCount()
    {
        return users.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3;
        public ViewHolder(View itemView)
        {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.userName);
            t2=(TextView)itemView.findViewById(R.id.email);
            t3=(TextView)itemView.findViewById(R.id.mobile);
        }
    }
}
