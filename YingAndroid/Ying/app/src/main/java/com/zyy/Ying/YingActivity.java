package com.zyy.Ying;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.zyy.Ying.YingMusic.YingMusicActivity;
import com.zyy.Ying.YingScanner.YingCaptureActivity;

public class YingActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ying);

        this.findViewById(R.id.YingButton1).setOnClickListener(this);

        this.findViewById(R.id.YingButton2).setOnClickListener(this);

        YingCrashHandler.getInstance().init(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param YView The view that was clicked.
     */
    @Override
    public void onClick(View YView) {
        switch (YView.getId()) {
            case R.id.YingButton1:
                startActivity(new Intent(this, YingCaptureActivity.class));
                break;

            case R.id.YingButton2:
                startActivity(new Intent(this, YingMusicActivity.class));
                break;
        }
    }
}
