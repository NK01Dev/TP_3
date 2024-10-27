package com.example.tp1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp1.R;
import com.example.tp1.model.StaticRvModel;

import java.util.ArrayList;

public class StaticRvAdapter extends  RecyclerView.Adapter<StaticRvAdapter.StaticRvViewHolder> {
    private final ArrayList<StaticRvModel> items;
int row_index=-1;
    public StaticRvAdapter(ArrayList<StaticRvModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public StaticRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        StaticRvViewHolder staticRvViewHolder=new StaticRvViewHolder(view);

        return staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvViewHolder holder,int position) {
StaticRvModel currentItem=items.get(position);
holder.imageView.setImageResource(currentItem.getImage());
holder.textView.setText(currentItem.getText());
holder.linearLayout.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {
        int currentPosition = holder.getAdapterPosition();  // الحصول على الموقع الحالي

        if (currentPosition != RecyclerView.NO_POSITION) {
            row_index = currentPosition;  // حفض الموقع الحالي المختار
            notifyDataSetChanged();  // نحدث الـ RecyclerView باش يظهر العنصر المختار
        }
    }
});
if (row_index==position){
    holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected);
}else{
    holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);

}
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static  class StaticRvViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
        public StaticRvViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.textView);
            linearLayout=itemView.findViewById(R.id.linearlayout);

        }
    }
}
