package com.example.smallkun.aswitch;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.SceneViewHolder>{

    public static class SceneViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        ImageView photo;

        SceneViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            photo = (ImageView)itemView.findViewById(R.id.img);
        }
    }

    List<scene> scenes;
    RVAdapter(List<scene> scenes) {
        this.scenes = scenes;
    }

    @Override
    public SceneViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cvitem, viewGroup, false);
        SceneViewHolder cvh = new SceneViewHolder(v);
        return cvh;
    }

    @Override
    public int getItemCount(){
        return scenes.size();
    }

    @Override
    public void onBindViewHolder(SceneViewHolder sceneViewHolder, int i){
        sceneViewHolder.name.setText(scenes.get(i).name);
        sceneViewHolder.photo.setImageResource(scenes.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
