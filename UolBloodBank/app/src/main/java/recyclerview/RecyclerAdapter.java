package recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dev2.uolbloodbank.R;
import com.example.dev2.uolbloodbank.User;

import java.util.List;

/**
 * Created by dev2 on 6/6/2017.
 */

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerVIewHolder>{

    private List<User> users;

    public RecyclerAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public RecyclerVIewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview,null);
        RecyclerVIewHolder recycler = new RecyclerVIewHolder(view);

        return recycler;
    }

    @Override
    public void onBindViewHolder(RecyclerVIewHolder holder, int position) {
        holder.name.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());
        holder.BloodGroup.setText(users.get(position).getBloodGroup());
        holder.Phone.setText(users.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }
}
