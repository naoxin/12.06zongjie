package com.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ehhzmy.hhzmy.R;
import com.hhzmy.hhzmy.MyActivity;
import com.hhzmy.hhzmy.ZhuCeActivity;
import com.hhzmy.util.OkHttp;
import com.hhzmy.view.Code;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by liyan on 2016/11/8.
 */

public class Fragment4 extends Fragment {

    private EditText ed;
    ImageView vc_image; //图标
    Button vc_shuaixi, vc_ok; //确定和刷新验证码
    String getCode = null; //获取验证码的值
    EditText vc_code; //文本框的值
    private ImageView ce;

    private Button mBtnPassword;
    private EditText mEtPassword;
    private boolean mbDisplayFlg = false;
    private ImageView sqq;
    private ImageView w;
    private ImageView xin;
    String path="http://60.205.92.165:8080/userOperAction/logon?";
    private String url;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment4, null);
        mEtPassword = (EditText) view.findViewById(R.id.edit2);

        mBtnPassword = (Button) view.findViewById(R.id.btnPassword);
        mBtnPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("AndroidTest", "mbDisplayFlg = " + mbDisplayFlg);
                if (!mbDisplayFlg) {
                    // display password text, for example "123456"
                    mEtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password, display "."
                    mEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mbDisplayFlg = !mbDisplayFlg;
                mEtPassword.postInvalidate();
            }
        });


        ed = (EditText) view.findViewById(R.id.edit);
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count == 1) {
                    int length = s.toString().length();
                    if (length == 3 || length == 8) {
                        ed.setText(s + " ");
                        ed.setSelection(ed.getText().toString().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        vc_image = (ImageView) view.findViewById(R.id.vc_image);
        vc_image.setImageBitmap(Code.getInstance().getBitmap());
        vc_code = (EditText) view.findViewById(R.id.edit3);

        getCode = Code.getInstance().getCode(); //获取显示的验证码
        Log.e("info", getCode + "----");
        vc_shuaixi = (Button) view.findViewById(R.id.vc_shuaixi);
        vc_shuaixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vc_image.setImageBitmap(Code.getInstance().getBitmap());
                getCode = Code.getInstance().getCode();
            }
        });

        vc_ok = (Button) view.findViewById(R.id.lu);
        vc_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v_code = vc_code.getText().toString().trim();
                if (v_code == null || v_code.equals("")) {
                    Toast.makeText(getActivity(), "没有填写验证码", Toast.LENGTH_SHORT).show();
                } else if (!v_code.equals(getCode)) {
                    Toast.makeText(getActivity(), "验证码填写不正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "操作成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MyActivity.class);
                    startActivity(intent);
                }
//                qin();
            }
        });

        ce = (ImageView) view.findViewById(R.id.ce);
        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ZhuCeActivity.class);
                getActivity().startActivity(intent);
            }
        });
        sqq = (ImageView) view.findViewById(R.id.disanq);
        sqq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI mShareAPI = UMShareAPI.get(getActivity());
                mShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
            }
        });
        w = (ImageView) view.findViewById(R.id.wei);
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI mShareAPI = UMShareAPI.get(getActivity());
                mShareAPI.doOauthVerify(getActivity(), SHARE_MEDIA.WEIXIN, umAuthListener);
            }
        });
        xin =(ImageView)view.findViewById(R.id.xin);
        xin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI mShareAPI = UMShareAPI.get(getActivity());
                mShareAPI.doOauthVerify(getActivity(), SHARE_MEDIA.SINA, umAuthListener);
            }
        });
        return view;
    }

    private void qin() {
        String ed1 = ed.getText().toString().replaceAll(" ", "").trim();
        Map<String,String> map=new HashMap<>();
        OkHttp.postAsync(path+"user_phone="+ed1+"&verify_code"+mEtPassword.getText().toString()+"&type=1", map, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
                JSONObject object = new JSONObject(result);
            String code = object.getString("code");
            String message_code = object.getString("message_code");
            Toast.makeText(getActivity(),code,Toast.LENGTH_SHORT).show();
            if(code.equals("200")){
                Toast.makeText(getActivity(),"登录成功！",Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }else if (code.equals("400")){
                Toast.makeText(getActivity(),"登录失败！"+message_code,Toast.LENGTH_SHORT).show();
            }
        }
        });
    }


    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            url = data.get("profile_image_url");
            name = data.get("screen_name");
            Toast.makeText(getContext(), url+name, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(),MyActivity.class).putExtra("tu",url).putExtra("name",name));
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


}
