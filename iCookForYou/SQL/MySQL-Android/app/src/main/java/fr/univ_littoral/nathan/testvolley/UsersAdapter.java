package fr.univ_littoral.nathan.testvolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by nathan on 07/02/18.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {


    private Context mCtx;
    private List<User> userList;

    public UsersAdapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user_list, null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);


        holder.textViewLastName.setText(user.getLastName());
        holder.textViewFirstName.setText(user.getFirstName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLastName, textViewFirstName;

        public UserViewHolder(View itemView) {
            super(itemView);

            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewFirstName = itemView.findViewById(R.id.textViewFirstName);
        }
    }
}