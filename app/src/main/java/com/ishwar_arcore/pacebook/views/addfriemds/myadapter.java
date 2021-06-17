package com.ishwar_arcore.pacebook.views.addfriemds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ishwar_arcore.pacebook.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<SearchFriends,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<SearchFriends> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull SearchFriends model)
    {
        holder.username.setText(model.getUsername());
        holder.city.setText(model.getCity());
        holder.college.setText(model.getCollege());
        Glide.with(holder.img.getContext()).load(model.getProfileimage()).into(holder.img);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_users_display_layout,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {


        CircleImageView img;
        TextView username,city,college;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            username=(TextView)itemView.findViewById(R.id.nametext);
            city=(TextView)itemView.findViewById(R.id.emailtextlayout);
            college=(TextView)itemView.findViewById(R.id.coursetext);
           img=(CircleImageView)itemView.findViewById(R.id.img1);

        }
    }
}
