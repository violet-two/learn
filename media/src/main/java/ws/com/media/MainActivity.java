package ws.com.media;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Button btn_up;
    private Button btn_down;
    private Button btn_player;
    private ToggleButton btn_normal;
    private AudioManager aManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn_player.setOnClickListener(view -> {
            //初始化MediaPlayer对象
            MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.earth);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        });
        btn_up.setOnClickListener(view ->
                aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI));
        btn_down.setOnClickListener(view ->
                aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI));
        btn_normal.setOnCheckedChangeListener((source,isChecked) -> {
            if (isChecked) {
                btn_normal.setTextOff("禁音");
            } else {
                btn_normal.setTextOn("正常");
            }
            aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    isChecked?AudioManager.ADJUST_MUTE:AudioManager.ADJUST_UNMUTE,
                    AudioManager.FLAG_SHOW_UI);
        });

    }

    private void initView() {
        //获取音频服务
        aManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);

        btn_normal = findViewById(R.id.btn_normal);
        btn_up = findViewById(R.id.btn_up);
        btn_down = findViewById(R.id.btn_down);
        btn_player = findViewById(R.id.btn_player);
    }
}