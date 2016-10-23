package cd.ex.leovs09.experiment_content_discavery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import java.io.File;

import cd.ex.leovs09.experiment_content_discavery.image_swipe.horizontal.SwipeHorizontalActivity;
import cd.ex.leovs09.experiment_content_discavery.image_swipe.vertical.SwipeVerticalActivity;
import cd.ex.leovs09.experiment_content_discavery.youtube_swipe.horizontal.SwipeYouTubeHorizontalActivity;
import cd.ex.leovs09.experiment_content_discavery.youtube_swipe.vertical.SwipeYouTubeVerticalActivity;

public class MainActivity extends AppCompatActivity {
    ToggleButton enableMediaController;
    VideoView myVideoView;
    public static int[] imagesId = {
            R.drawable.ac_dc,
            R.drawable.bloodhound_gang,
            R.drawable.imagion_dragons,
            R.drawable.alai_oli
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Button goToSwipe = (Button) findViewById(R.id.btn_img_vertical);
        goToSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),SwipeVerticalActivity.class));
            }
        });

        goToSwipe = (Button) findViewById(R.id.btn_img_horizontal);
        goToSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),SwipeHorizontalActivity.class));
            }
        });

        goToSwipe = (Button) findViewById(R.id.btn_youtube_vertical);
        goToSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),SwipeYouTubeVerticalActivity.class));
            }
        });

        goToSwipe = (Button) findViewById(R.id.btn_youtube_horizontal);
        goToSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),SwipeYouTubeHorizontalActivity.class));
            }
        });
    }

    private void setMediaController(){
        if(enableMediaController.isChecked()){
            myVideoView.setMediaController(new MediaController(this));
        }else{
            myVideoView.setMediaController(null);
        }
    }
    private String getViewSrc(){
        File extStorageDirectory = Environment.getExternalStorageDirectory();
        String s = extStorageDirectory.getAbsolutePath();
        s += "/test.mp4";
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
        return s;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
