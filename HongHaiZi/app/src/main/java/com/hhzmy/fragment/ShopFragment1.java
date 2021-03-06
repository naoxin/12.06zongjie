package com.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ehhzmy.hhzmy.R;
import com.hhzmy.hhzmy.BaiDuMapActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

/**
 * Created by liyan on 2016/11/8.
 */

public class ShopFragment1 extends Fragment {

    String[] path = {"花王妙而舒婴幼儿纸尿裤", "免运费", "中国      河北     保定", "中号M64片"};
    private long time = 4000;
    private TextView tvtime1;
    private TextView tvtime2;
    private TextView tvtime3;
    private int requestCode;

//倒计时handler
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            String formatLongToTimeStr = formatLongToTimeStr(time);
            String[] split = formatLongToTimeStr.split("：");
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    tvtime1.setText(split[0] + "小时");
                }
                if (i == 1) {
                    tvtime2.setText(split[1] + "分钟");
                }
                if (i == 2) {
                    tvtime3.setText(split[2] + "秒");
                }

            }
            if (time > 0) {
                handler.postDelayed(this, 1000);
            }
        }


    };
    private TextView na;
    private TextView yunfei;
    private TextView xinghao;
    private TextView address;
    private Intent intent;
    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.shop_fragment1, null);

        tvtime1 = (TextView) view.findViewById(R.id.tvtime1);
        tvtime2 = (TextView) view.findViewById(R.id.tvtime2);
        tvtime3 = (TextView) view.findViewById(R.id.tvtime3);
        na = (TextView) view.findViewById(R.id.name);
        yunfei = (TextView) view.findViewById(R.id.yunfei);
        address = (TextView) view.findViewById(R.id.address);
//        xinghao =(TextView)view. findViewById(R.id.xinghao);
//百度地图跳转
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), BaiDuMapActivity.class);
                requestCode = 0;
                startActivityForResult(intent, requestCode);
            }
        });
        //        na.setText(path[0]);
        yunfei.setText(path[1]);
        address.setText(path[2]);
//        xinghao.setText(path[3]);
        handler.postDelayed(runnable, 1000);
        image = (ImageView) view.findViewById(R.id.imageView2);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMImage umImage=new UMImage(getActivity(),R.mipmap.zxc);
                new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QQ)
                        .withText(path[0])
                        .withTitle("纸尿裤")
                        .setCallback(umShareListener)
                        .withMedia(umImage)
                        .withTargetUrl("https://123.sogou.com/?81014")
                        .share();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String change01 = data.getStringExtra("change01");
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 0:
                address.setText(change01);
                break;

            default:
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }

        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour + "：" + minute + "：" + second;
        return strtime;

    }


}
