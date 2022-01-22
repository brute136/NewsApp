package com.teen.NewsApp;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import com.teen.NewsApp.Adapters.RecyclerAdapter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
	private SwipeRefreshLayout swipeRefresh;
	private String urll;
	private ArrayList<Model_Json> list;
	private RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		//list = new ArrayList<>();
		swipeRefresh = findViewById(R.id.Swipe);
		list = new ArrayList<>();
		recycler = findViewById(R.id.recyclerView);
		LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
		layoutmanager.setOrientation(LinearLayout.VERTICAL);
		recycler.setLayoutManager(layoutmanager);
		fetch();
		swipeRefresh.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				//Toast.makeText(MainActivity.this,"Swipe",Toast.LENGTH_SHORT).show();
				fetch();
			//swipeRefresh.setRefreshing(false);
			}

		});
    }
	public void fetch(){
		urll = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=d71092637e914fa5b7395cfa16e150fa";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(urll)
				.build();
		client.newCall(request)
				.enqueue(new Callback(){
					
						@Override
						public void onFailure(Call call, IOException respone) {
							
						}

						@Override
						public void onResponse(Call call, Response response) {
							if (!response.isSuccessful()){
								return;
							}
							try{
							   JSONObject object = new JSONObject(response.body().string());
							   JSONArray arr = object.getJSONArray("articles");
							   for (int i=0;i<arr.length();i++){
								   String des = arr.getJSONObject(i).getString("description");
								   String title = arr.getJSONObject(i).getString("title");
								   String image = arr.getJSONObject(i).getString("urlToImage");
								   list.add(new Model_Json(image,title,des));
							   }
							 //  Log.d("NewsAppData",arr.getJSONObject(1).getString("description"));
							   MainActivity.this.runOnUiThread(new Runnable(){
								   
									@Override
									public void run() {
										RecyclerAdapter adapterr= new RecyclerAdapter(MainActivity.this,list);
										recycler.setAdapter(adapterr);
										swipeRefresh.setRefreshing(false);
									}

							   });
							}catch (Exception c){}
						}

				});
		
	}
}