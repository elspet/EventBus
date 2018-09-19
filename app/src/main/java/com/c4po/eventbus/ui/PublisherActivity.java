package com.c4po.eventbus.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.c4po.eventbus.R;
import com.c4po.eventbus.event.SpecificEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 消息发布者
 *
 * @author Lisa
 */
public class PublisherActivity extends AppCompatActivity {

    Button btnPublishMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_publisher);
        btnPublishMsg = findViewById(R.id.btn_publish_msg);
        btnPublishMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpecificEvent specificEvent = new SpecificEvent("Lisa","iamgeUrl");
                EventBus.getDefault().post(specificEvent);
                PublisherActivity.this.finish();
            }
        });
    }
}
