package cd.ex.leovs09.experiment_content_discavery.youtube_swipe;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.concurrent.TimeUnit;

import cd.ex.leovs09.experiment_content_discavery.MainActivity;
import cd.ex.leovs09.experiment_content_discavery.R;

public class YoutubeActivity extends YouTubeBaseActivity {
    private String YOUTUBE_KEY = "AIzaSyBP7NieGXZ5BeejXLxdYaKMFHFCDdQkqm4";
    YouTubePlayerView youTubeView;
    private String[] videoUrls = {
            "wKJ9KzGQq0w",
            "txBfhpm1jI0",
            "Q0oIoR9mLwc"
    };
    private int indexVideo = 0;
    private int displayWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(YOUTUBE_KEY, new YoutubeInitListener());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        displayWidth = ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getWidth();

    }
    private class YoutubeInitListener implements YouTubePlayer.OnInitializedListener{

        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
            if(youTubePlayer.isPlaying()) {
                youTubePlayer.pause();
                youTubePlayer.release();
            }
            youTubePlayer.loadVideo(videoUrls[indexVideo]);
            indexVideo = (indexVideo < videoUrls.length - 1) ? indexVideo + 1 : 0;
            youTubeView.getParent().requestLayout();
            youTubePlayer.play();
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            Log.d("Youtube","fail");
        }
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
    private boolean inRegion(float x, float y, View v) {
        int [] mCoordBuffer = {0,0,0,0};
        v.getLocationOnScreen(mCoordBuffer);
        return mCoordBuffer[0] + v.getWidth() > x &&    // right edge
                mCoordBuffer[1] + v.getHeight() > y &&   // bottom edge
                mCoordBuffer[0] < x &&                   // left edge
                mCoordBuffer[1] < y;                     // top edge
    }


    private class ChangeVideo extends AsyncTask<YouTubePlayerView,Void,Void> {
        YouTubePlayerView view;
        @Override
        protected Void doInBackground(YouTubePlayerView... views){
            this.view = views[0];
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                Log.e("ChangeVideo",e.toString(),e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            youTubeView.setLeft(0);
            youTubeView.initialize(YOUTUBE_KEY, new YoutubeInitListener());
            youTubeView.getParent().requestLayout();
        }

    }

}
