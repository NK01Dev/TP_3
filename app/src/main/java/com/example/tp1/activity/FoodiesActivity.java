package com.example.tp1.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp1.DRVInterface.LoadMore;
import com.example.tp1.R;
import com.example.tp1.adapter.DynamicRVAdapter;
import com.example.tp1.adapter.StaticRvAdapter;
import com.example.tp1.model.DynamicrvModel;
import com.example.tp1.model.StaticRvModel;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodiesActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private StaticRvAdapter  staticRvAdapter;
List<DynamicrvModel> items= new ArrayList();
DynamicRVAdapter dynamicRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_foodies);
      recyclerView=findViewById(R.id.rv_1);
        ArrayList<StaticRvModel> item=new ArrayList<>();
        item.add( new StaticRvModel(R.drawable.pizza,"Pizza"));
        item.add( new StaticRvModel(R.drawable.sandwich,"Sandwich"));
        item.add( new StaticRvModel(R.drawable.burger,"Burger"));
        item.add( new StaticRvModel(R.drawable.fries,"Fries"));
        item.add( new StaticRvModel(R.drawable.ice_cream,"Ice Cream"));
        staticRvAdapter=new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);
    items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
        items.add(new DynamicrvModel("detail"));
RecyclerView  DRView=findViewById(R.id.rv_2);
DRView.setLayoutManager( new LinearLayoutManager(this));
dynamicRVAdapter = new DynamicRVAdapter(DRView,this,items);
DRView.setAdapter(dynamicRVAdapter);
dynamicRVAdapter.setLoadMore(new LoadMore() {
    @Override
    public void onLoadMore() {
        if (items.size()<=10){
            item.add(null);
            dynamicRVAdapter.notifyItemChanged( items.size()-1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
               items.remove(items.size()-1);

               int index=items.size();
               int end =index+10;
               for(int i=index ; i<end;i++){
                   String name= UUID.randomUUID().toString();
                   DynamicrvModel itemM= new DynamicrvModel(name);
items.add(itemM);
               }
               dynamicRVAdapter.notifyDataSetChanged();
               dynamicRVAdapter.setLoaded();
                }
            }, 4000);
              }
        else
            Toast.makeText(FoodiesActivity.this,"Data Completed",Toast.LENGTH_LONG).show();

    }
});
    }
}