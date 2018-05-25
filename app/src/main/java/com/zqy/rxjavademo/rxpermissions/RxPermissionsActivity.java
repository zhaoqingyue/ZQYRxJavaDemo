package com.zqy.rxjavademo.rxpermissions;

import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.functions.Action1;

public class RxPermissionsActivity extends BaseActivity {

    @BindView(R.id.btn_request)
    Button btn_request;
    @BindView(R.id.btn_requestEach)
    Button btn_requestEach;
    @BindView(R.id.btn_ensure)
    Button btn_ensure;
    @BindView(R.id.btn_ensureEach)
    Button btn_ensureEach;

    RxPermissions rxPermissions;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_rx_permissions;
    }

    @Override
    protected String getTitleName() {
        return "RxPermissions";
    }

    @Override
    protected void initData() {
        rxPermissions = new RxPermissions(this);
        /**
         * 总结：
         * 在初始程序的时候请求一个权限： new RxPermissions(this).request(permissions) or new RxPermissions(this).requestEach(permissions)
         * 在初始程序的时候请求多个权限： new RxPermissions(this).requestEach(permissions)
         * 在指定的位置，时机请求一个权限：Observable.compose(rxPermissions.ensure()) or Observable.compose(rxPermissions.ensureEach())
         * 在指定的位置，时机请求多个权限：Observable.compose(rxPermissions.ensureEach())
         */
        btn_request.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // new RxPermissions(this).request(permissions)
                rxPermissions.request(Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {

                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    Toast.makeText(getApplicationContext(),
                                            "all permissions get success",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "must has one ore more permissions get failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btn_requestEach.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // new RxPermissions(this).requestEach(permissions)
                rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
                        .subscribe(new Action1<Permission>() {

                            @Override
                            public void call(Permission permission) {
                                if (permission.granted){
                                    Toast.makeText(getApplicationContext(),
                                            permission.name + "this permission get success",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            permission.name + "this permission get failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Observable.compose(rxPermissions.ensure())
        RxView.clicks(btn_ensure)
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA))
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            Toast.makeText(getApplicationContext(),
                                    "all permissions get success",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "must has one ore more permissions get failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Observable.compose(rxPermissions.ensureEach())
        RxView.clicks(btn_ensureEach)
                .compose(rxPermissions.ensureEach(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO))
                .subscribe(new Action1<Permission>() {

                    @Override
                    public void call(Permission permission) {
                        if (permission.granted){
                            Toast.makeText(getApplicationContext(),
                                    permission.name + "this permission get success",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    permission.name + "this permission get failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
