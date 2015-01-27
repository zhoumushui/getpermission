package com.zms.getpermission;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by AlexZhou on 2015/1/27.
 * 8:53
 */
public class ShowPermission extends Activity {
    private TextView tvPermission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpermission);
        tvPermission = (TextView) findViewById(R.id.tvPermission);
        String strPermission = getIntent().getStringExtra("strPermission");
        tvPermission.setText(strPermission);
    }
}
