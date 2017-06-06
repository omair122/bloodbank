package recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dev2.uolbloodbank.R;

/**
 * Created by dev2 on 6/6/2017.
 */

public class RecyclerVIewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name;
    public TextView email;
    public TextView BloodGroup;
    public TextView Phone;

    public RecyclerVIewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        name = (TextView) itemView.findViewById(R.id.rname);
        email = (TextView) itemView.findViewById(R.id.remail);
        BloodGroup = (TextView) itemView.findViewById(R.id.rblood);
        Phone = (TextView) itemView.findViewById(R.id.rphone);

    }

    public void onClick(View view){

    }
}
