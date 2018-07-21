package com.example.naderdutils.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.free_ride.yiwei.R;
import com.free_ride.yiwei.personal.AppManager;


/**
 * 作者: Nade_S on 2018/5/5.
 * 权限管理
 */

public class AppPerMissionUtils {
    public static final int BAIDU_READ_PHONE_STATE = 100;


    public static boolean showContacts(int i) {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        boolean isOk = false;
            if (ActivityCompat.checkSelfPermission(AppManager.getAppManager().currentActivity(),permissions[i])!= PackageManager.PERMISSION_GRANTED){
                // 目前木有权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(AppManager.getAppManager().currentActivity(),permissions[i])){
                    ActivityCompat.requestPermissions(AppManager.getAppManager().currentActivity(),new String[]{permissions[i]},BAIDU_READ_PHONE_STATE);
                }else {
                    // 展示理由
                    String hint = "";
                    switch (i){
                        case 0:
                            hint = "位置信息";
                            break;
                        case 1:
                            hint = "位置信息";
                            break;
                        case 2:
                            hint = "读取机器信息";
                            break;
                        case 3:
                            hint = "拍照";
                            break;
                        case 4:
                            hint = "读取手机相册";
                            break;
                        case 5:
                            hint = "保存到相册";
                            break;
                    }
                    addPhoneToast(AppManager.getAppManager().currentActivity(),hint);
                }
            }else {
                isOk = true;

            }
        return isOk;
    }



    public static void addPhoneToast(final Context context, String hint){
        View view = LayoutInflater.from(context).inflate(R.layout.addphonetoast, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        TextView hint_tv = (TextView) view.findViewById(R.id.addphone_toastcontent);
        hint_tv.setText("获取手机"+hint+"失败 ，请手动开启权限");
        Button cancel = (Button) view.findViewById(R.id.addphone_cancel);
        Button sure = (Button) view.findViewById(R.id.addphone_sure);
        sure.setText("去开启");
        final AlertDialog dialog = builder.create();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("package:"+AppManager.getAppManager().currentActivity().getPackageName());
                Intent in = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,uri);
                AppManager.getAppManager().currentActivity().startActivity(in);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
