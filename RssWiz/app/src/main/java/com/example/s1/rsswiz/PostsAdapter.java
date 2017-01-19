package com.example.s1.rsswiz;

/**
 * Created by S1 on 1/18/2017.
 */
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<PostModel> posts;

    public PostsAdapter(List<PostModel> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostModel post = posts.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.desc.setText(Html.fromHtml(post.getDesc(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.desc.setText(Html.fromHtml(post.getDesc()));
        }
        String time = post.getTime();
        Date date = new Date();
        SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            date = FORMATTER.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.time.setText(date.toString());
        Picasso.with(holder.itemView.getContext()).load(post.getUrl()).fit().centerCrop().into(holder.img);
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView desc;
        TextView time;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            desc = (TextView) itemView.findViewById(R.id.textView);
            time = (TextView) itemView.findViewById(R.id.textView1);
            img = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    //https://api.myjson.com/bins/sv06b
}