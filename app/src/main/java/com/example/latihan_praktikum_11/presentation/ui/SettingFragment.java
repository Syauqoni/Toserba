package com.example.latihan_praktikum_11.presentation.ui;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.latihan_praktikum_11.R;

public class SettingFragment extends Fragment {
    private final String CHANNEL_ID = "notif_channel";

    public SettingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        TextView textView = rootView.findViewById(R.id.text_home);
        textView.setText("Ini adalah Menu Setting");

        Button btnNotif = rootView.findViewById(R.id.btnIntentActivity);
        btnNotif.setOnClickListener(view -> showIntentActivityNotification());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                requireContext().checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) !=
                        android.content.pm.PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
        }

        createNotificationChannel();

        return rootView;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Sample Notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = (NotificationManager) requireContext().getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
    }

    private void showIntentActivityNotification() {
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.putExtra("openFragment", "konten");

        PendingIntent activityPendingIntent = PendingIntent.getActivity(
                requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE
        );

        Bitmap largeImage = BitmapFactory.decodeResource(getResources(), R.drawable.baju);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_view)
                .setContentTitle("Ada Baju baru nih!")
                .setContentText("Klik disini untuk mencek")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(largeImage))
                .setContentIntent(activityPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager manager = (NotificationManager) requireContext().getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int) (Math.random() * 10000), builder.build());
    }

}
