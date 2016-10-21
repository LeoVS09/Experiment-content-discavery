package cd.ex.leovs09.experiment_content_discavery.youtube_swipe.horizontal;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.concurrent.TimeUnit;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.YouTubeFailureRecoveryActivity;

public class SwipeYouTubeHorizontalActivity extends YouTubeFailureRecoveryActivity {
    private int displayWidth;
    private SwipeYouTubeHorizontalActivity self = this;
    private YouTubePlayer player;
    private String[] videoUrls = {
            "nCgQDjiotG0",
            "wKJ9KzGQq0w",
            "txBfhpm1jI0",
            "Q0oIoR9mLwc"
    };
    private int indexVideo = 0;
    YouTubePlayerView youTubeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_horizontal_youtube);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(YOUTUBE_KEY, this);
        displayWidth = ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getWidth();

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        this.player = player;
        if (!wasRestored) {
            player.loadVideo(videoUrls[indexVideo]);
            player.play();
            indexVideo = (indexVideo < videoUrls.length - 1) ? indexVideo + 1 : 0;
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);    }

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
            player.loadVideo(videoUrls[indexVideo]);
            player.play();
            indexVideo = (indexVideo < videoUrls.length - 1) ? indexVideo + 1 : 0;
        }

    }
}
