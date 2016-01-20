package vn.tdx.notificationcompattutorial;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int NORMAL_ID = 100;
    private static final int ACTION_ID = 200;
    private static final int BIGVIEW_ID = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.normal).setOnClickListener(this);
        findViewById(R.id.action).setOnClickListener(this);
        findViewById(R.id.bigview).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal:
                showNormalNotification();
                break;
            case R.id.action:
                showNotificationWithAction();
                break;
            case R.id.bigview:
                showNotificationBigView();
                break;
        }
    }

    private void showNormalNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Title");
        builder.setContentText("Description");
        builder.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManagerCompat.from(this).notify(NORMAL_ID, builder.build());
    }

    private void showNotificationWithAction() {
        //create normal notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Title");
        builder.setContentText("Description");
        builder.setSmallIcon(R.mipmap.ic_launcher);

        //add action
        Intent notificationIntent = new Intent(this, ActionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        NotificationCompat.Action action = new NotificationCompat.Action(R.mipmap.ic_launcher, "Open Action", pendingIntent);
        builder.addAction(action);

        //show notification
        NotificationManagerCompat.from(this).notify(ACTION_ID, builder.build());
    }

    private void showNotificationBigView() {
        //create normal notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Title");
        builder.setContentText("Description");
        builder.setSmallIcon(R.mipmap.ic_launcher);

        //Create bigview
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        events[0] = new String("First line....");
        events[1] = new String("Second line...");
        events[2] = new String("Third line...");
        events[3] = new String("4th line...");
        events[4] = new String("5th line...");
        events[5] = new String("6th line...");

        // Sets a title for the big view
        inboxStyle.setBigContentTitle("Big Title");
        // Add events
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        builder.setStyle(inboxStyle);
        //show notification
        NotificationManagerCompat.from(this).notify(BIGVIEW_ID, builder.build());
    }
}
