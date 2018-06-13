package com.example.smallkun.aswitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by smallkun on 2018/5/5.
 */

public class TwoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two,container,false);
    }

    private CardView cardView1,cardView2,cardView3,cardView4;

    private List<scene> scenes;

    private void initializeData(){
        scenes = new ArrayList<>();
        scenes.add(new scene("回家", R.drawable.ic_close) );
        scenes.add(new scene("睡眠", R.drawable.ic_close) );
        scenes.add(new scene("运动", R.drawable.ic_close) );
        scenes.add(new scene("离家", R.drawable.ic_close) );
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity activity = (MainActivity)getActivity();

        RecyclerView rew = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rew.setLayoutManager(llm);
        initializeData();
        RVAdapter adapter = new RVAdapter(scenes);
        rew.setAdapter(adapter);

        adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getActivity(), Profile_m.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), Profile_n.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(), Profile_o.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getActivity(), Profile_p.class);
                        startActivity(intent3);
                        break;
                }


            }
        });
//        cardView1 = (CardView) view.findViewById(R.id.cardView);
//        cardView2 = (CardView) view.findViewById(R.id.cardView2);
//        cardView3 = (CardView) view.findViewById(R.id.cardView3);
//        cardView4 = (CardView) view.findViewById(R.id.cardView4);
//
//        cardView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Profile_m.class);
//                startActivity(intent);
//            }
//        });
//
//        cardView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Profile_n.class);
//                startActivity(intent);
//            }
//        });
//
//        cardView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Profile_o.class);
//                startActivity(intent);
//            }
//        });
//
//        cardView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Profile_p.class);
//                startActivity(intent);
//            }
//        });
    }
}