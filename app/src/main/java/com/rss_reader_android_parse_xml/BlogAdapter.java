package com.rss_reader_android_parse_xml;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by AMAN on 15-09-2015.
 */
public class BlogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Pojo> data;
    private Context context;
    private LayoutInflater inflater;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    public BlogAdapter(Context context, List<Pojo> data) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(this.context);
        imageLoader = ImageLoader.getInstance();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view =  inflater.inflate(R.layout.blog_list_item,viewGroup,false);
        ItemHolder itemholder = new ItemHolder(view);
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                 .cacheOnDisc(true).resetViewBeforeLoading(true)
                 .build();
        return itemholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemholder = (ItemHolder) holder;
        Pojo current = data.get(position);
        imageLoader.displayImage(current.postDescription, itemholder.blogImage, options);
        itemholder.title.setText(current.postTitle);
        itemholder.category.setText(current.postCategory);
        itemholder.creator.setText(" Posted by "+current.postCreator);
        itemholder.date.setText(current.postDate);

    }

    @Override
    public int getItemCount() {
        return  (null != data ? data.size() : 0);
    }

    class ItemHolder extends RecyclerView.ViewHolder {


        TextView category,title,creator,date;
        ImageView blogImage;
        public ItemHolder(View itemView) {
            super(itemView);

            category = (TextView)itemView.findViewById(R.id.category);
            title = (TextView)itemView.findViewById(R.id.title);
            creator = (TextView)itemView.findViewById(R.id.creator);
            date = (TextView)itemView.findViewById(R.id.date);
            blogImage = (ImageView)itemView.findViewById(R.id.blogImage);

        }



    }

}
