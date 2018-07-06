package com.example.smallkun.aswitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by smallkun on 2018/5/5.
 */

public class TwoFragment extends Fragment {

    public String wd="25",fs="0",status="0",mode="1",result,user_name,hour,min,state ="0",scene="1";
    public int ok;
    public int lgstate=200;
    public boolean onLogin=false;
    public String device_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two,container,false);
    }

    private CardView cardView1,cardView2,cardView3,cardView4;

    private List<scene> scenes;

    private void initializeData(){
        scenes = new ArrayList<>();
        scenes.add(new scene("回家", R.drawable.home) );
        scenes.add(new scene("睡眠", R.drawable.sleep) );
        scenes.add(new scene("运动", R.drawable.sport) );
        scenes.add(new scene("离家", R.drawable.leave) );
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

        //获取登录状态
        if (getArguments()!=null) {
            onLogin = getArguments().getBoolean("ONLOGIN");
            user_name= getArguments().getString("USERNAME");
            lgstate = getArguments().getInt("LGState");
        }

        adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (onLogin) {
                    if (lgstate == 100) {
                        switch (position) {
                            case 0:
                                Intent intent = new Intent(getActivity(), Profile_m.class);
                                startActivityForResult(intent, 1);
                                break;
                            case 1:
                                Intent intent1 = new Intent(getActivity(), Profile_n.class);
                                startActivityForResult(intent1, 2);
                                break;
                            case 2:
                                Intent intent2 = new Intent(getActivity(), Profile_o.class);
                                startActivityForResult(intent2, 3);
                                break;
                            case 3:
                                Intent intent3 = new Intent(getActivity(), Profile_p.class);
                                startActivityForResult(intent3, 4);
                                break;
                        }
                    } else {
                        if (lgstate == 101) {
                            Toast.makeText(getActivity(), "您尚未绑定空调，请绑定！", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(getActivity(), "您尚未登录，请登录！", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        //Toast.makeText(getActivity(), wd+" "+fs+" "+mode+ " "+hour+" "+min, Toast.LENGTH_SHORT).show();


        switch (requestCode){
            case 1:
                scene="1";
                if (resultCode == RESULT_OK) {
                    state = "1";
                    wd = data.getStringExtra("WD");
                    fs = data.getStringExtra("FS");
                    status = data.getStringExtra("OP");
                    mode = data.getStringExtra("MS");
                    hour = data.getStringExtra("Time");
                    min =  data.getStringExtra("Min");

                    //Toast.makeText(getActivity(), user_name+" "+wd+" "+fs+" "+mode+ " "+hour+" "+min+" "+status, Toast.LENGTH_SHORT).show();
                    //接口
                    new Thread(runnable).start();
                } else {
                    if (resultCode == RESULT_CANCELED) {
                        state = "0";
                        wd = "20";
                        fs = "0";
                        status = "0";
                        mode = "0";
                        hour = "0";
                        min =  "0";

                        //接口
                        new Thread(runnable).start();
                    }
                }
                break;
            case 2:
                scene = "2";
                if (resultCode == RESULT_OK) {
                    state = "1";
                    wd = data.getStringExtra("WD");
                    fs = data.getStringExtra("FS");
                    status = data.getStringExtra("OP");
                    mode = data.getStringExtra("MS");
                    hour = data.getStringExtra("Time");
                    min =  data.getStringExtra("Min");

                    //Toast.makeText(getActivity(), user_name+" "+wd+" "+fs+" "+mode+ " "+hour+" "+min+" "+status, Toast.LENGTH_SHORT).show();
                    //接口
                    new Thread(runnable).start();
                } else {
                    if (resultCode == RESULT_CANCELED) {
                        state = "0";
                        wd = "20";
                        fs = "0";
                        status = "0";
                        mode = "0";
                        hour = "0";
                        min =  "0";

                        //接口
                        new Thread(runnable).start();
                    }
                }
                break;
            case 3:
                scene="3";
                if (resultCode == RESULT_OK) {
                    state = "1";
                    wd = data.getStringExtra("WD");
                    fs = data.getStringExtra("FS");
                    status = data.getStringExtra("OP");
                    mode = data.getStringExtra("MS");
                    hour = data.getStringExtra("Time");
                    min =  data.getStringExtra("Min");

                    //Toast.makeText(getActivity(), user_name+" "+wd+" "+fs+" "+mode+ " "+hour+" "+min+" "+status, Toast.LENGTH_SHORT).show();
                    //接口
                    new Thread(runnable).start();
                } else {
                    if (resultCode == RESULT_CANCELED) {
                        state = "0";
                        wd = "20";
                        fs = "0";
                        status = "0";
                        mode = "0";
                        hour = "0";
                        min =  "0";

                        //接口
                        new Thread(runnable).start();
                    }
                }
                break;
            case 4:
                scene = "4";
                if (resultCode == RESULT_OK) {
                    state = "1";
                    wd = data.getStringExtra("WD");
                    fs = data.getStringExtra("FS");
                    status = data.getStringExtra("OP");
                    mode = data.getStringExtra("MS");
                    hour = data.getStringExtra("Time");
                    min =  data.getStringExtra("Min");

                    //Toast.makeText(getActivity(), user_name+" "+wd+" "+fs+" "+mode+ " "+hour+" "+min+" "+status, Toast.LENGTH_SHORT).show();
                    //接口
                    new Thread(runnable).start();
                } else {
                    if (resultCode == RESULT_CANCELED) {
                        state = "0";
                        wd = "20";
                        fs = "0";
                        status = "0";
                        mode = "0";
                        hour = "0";
                        min =  "0";

                        //接口
                        new Thread(runnable).start();
                    }
                }
                break;
            default:
        }
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            result = data.getString("response");

            //Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

            //提取返回数据中的内容
            String[] temp = result.split(",");
            result = temp[0];
            ok = Integer.parseInt(result.trim());//trim方法用于去除当前 String 对象移除所有前导空白字符和尾部空白字符
            if (ok == 100){
                Toast.makeText(getActivity(), "操作成功！", Toast.LENGTH_SHORT).show();
            } else if (ok == 200){
                Toast.makeText(getActivity(), "操作失败", Toast.LENGTH_SHORT).show();
            }
        }
    };



    //新线程进行网络请求
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //
            // TODO: http request.
            //
            URL url=null;
            try{
                url = new URL("http://47.106.181.0:8080/air/Scene?account="+URLEncoder.encode(user_name, "utf-8")+"&scene="+URLEncoder.encode(scene, "utf-8")+"&state="+URLEncoder.encode(state, "utf-8")+"&hour="+URLEncoder.encode(hour, "utf-8")+"&min="+URLEncoder.encode(min, "utf-8")+"&status="+URLEncoder.encode(status, "utf-8")+"&temperature="+URLEncoder.encode(wd, "utf-8")+"&pattern="+URLEncoder.encode(mode, "utf-8")+"&speed="+URLEncoder.encode(fs, "utf-8"));

            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            httpNet httpConnection=new httpNet(url);

            String result=httpConnection.loginOfPost();

            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("response",result);

            //data.putString("value", "请求结果");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
}