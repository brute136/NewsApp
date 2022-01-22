package com.teen.NewsApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.teen.NewsApp.Model_Json;
import com.teen.NewsApp.R;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myHolder>{
	ArrayList<Model_Json> list;
	Context context;
	public RecyclerAdapter(Context context,ArrayList<Model_Json> list){
		this.list = list;
		this.context = context;
	}
	@Override
	public myHolder onCreateViewHolder(ViewGroup parent, int position) {
		View v = LayoutInflater.from(context).inflate(R.layout.activity_recycler,parent,false);
		myHolder mHol = new myHolder(v);
	    return mHol;
	}

	@Override
	public void onBindViewHolder(myHolder holder, int position) {
		holder.bind(list.get(position).getImage(),list.get(position).getTitle(),list.get(position).getDescription());
	}

	@Override
	public int getItemCount() {
	    return list.size();
	}
	class myHolder extends RecyclerView.ViewHolder{
		ImageView image;
		TextView title,des;
		public myHolder(View v){
			super(v);
			image = v.findViewById(R.id.imageview2);
			title = v.findViewById(R.id.textview2);
			des = v.findViewById(R.id.textview3);
		}
		public void bind(String imageUrl,String title,String des){
			Glide.with(context)
					.load(imageUrl)
					.into(image);
			this.title.setText(title);
			this.des.setText(des);
		}
	}

}