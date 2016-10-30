package cd.ex.leovs09.experiment_content_discavery.youtube_swipe.horizontal;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.concurrent.TimeUnit;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.YouTubeFailureRecoveryActivity;
import cd.ex.leovs09.experiment_content_discavery.youtube_swipe.SwipeYouTubeActivity;

public class SwipeYouTubeHorizontalActivity extends SwipeYouTubeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_horizontal_youtube);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.title_youtube);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        super.dispatchTouchEvent(event);

        float x = event.getX();
        float y = event.getY();
        if(!inRegion(event.getRawX(), event.getRawY(), youTubeView)) return false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(x < displayWidth/2){
                    Toast.makeText(this,"dislike",Toast.LENGTH_SHORT).show();
                    new ChangeVideo().execute(youTubeView);
                }else if (x > (displayWidth)/2){
                    Toast.makeText(this,"like",Toast.LENGTH_SHORT).show();
                    new ChangeVideo().execute(youTubeView);
                }else{
                    //Not changed
                }
                break;
        }
        return false;
    }
    protected void preLeave(){}

}
