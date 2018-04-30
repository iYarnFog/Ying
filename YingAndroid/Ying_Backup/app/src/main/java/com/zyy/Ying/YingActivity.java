package com.zyy.Ying;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import com.zyy.Ying.YingScanner.YingCaptureActivity;

public class YingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ying);

        //startActivity(new Intent(this, YingCaptureActivity.class));
    }
}
