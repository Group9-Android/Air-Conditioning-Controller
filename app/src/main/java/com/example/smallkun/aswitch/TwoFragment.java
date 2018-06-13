package com.example.smallkun.aswitch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity activity = (MainActivity)getActivity();

        cardView1 = (CardView) view.findViewById(R.id.cardView);
        cardView2 = (CardView) view.findViewById(R.id.cardView2);
        cardView3 = (CardView) view.findViewById(R.id.cardView3);
        cardView4 = (CardView) view.findViewById(R.id.cardView4);


    }
}