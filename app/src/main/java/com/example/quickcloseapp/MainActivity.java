package com.example.quickcloseapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        TextView tvInfo = new TextView(this);
        tvInfo.setText("【初期設定】\n" +
                "フローティングボタンを有効化するため、以下の許可を行ってください。\n\n" +
                "1. 他のアプリの上に重ねて表示（オーバーレイ権限）\n" +
                "2. アクセシビリティ権限（ホームに戻る操作を実行するため）\n" +
                "3. バッテリー最適化の除外（ColorOS用）");
        tvInfo.setTextSize(16);
        layout.addView(tvInfo);

        Button btnOverlay = new Button(this);
        btnOverlay.setText("1. 重ねて表示（オーバーレイ）権限を開く");
        btnOverlay.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        });
        layout.addView(btnOverlay);

        Button btnAccessibility = new Button(this);
        Button btnBattery = new Button(this);

        btnAccessibility.setText("2. アクセシビリティ権限を開く");
        btnAccessibility.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        });
        layout.addView(btnAccessibility);

        btnBattery.setText("3. バッテリー最適化設定を開く");
        btnBattery.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        });
        layout.addView(btnBattery);

        setContentView(layout);
    }
}
