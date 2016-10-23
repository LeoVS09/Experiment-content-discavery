package cd.ex.leovs09.experiment_content_discavery.youtube_swipe.vertical;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.AlphaAnimation;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.concurrent.TimeUnit;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.YouTubeFailureRecoveryActivity;

public class SwipeYouTubeVerticalActivity extends AppCompatActivity {
    private int viewTop;
    private int displayHeight;
    private SwipeYouTubeVerticalActivity self = this;
    private YouTubePlayer player;
    private float startY = 0;
    private boolean moved = false;
    private String[] videoUrls = {
            "nCgQDjiotG0",
            "wKJ9KzGQq0w",
            "txBfhpm1jI0",
            "Q0oIoR9mLwc"
    };
    private int indexVideo = 0;
    YouTubePlayerView youTubeView;
    YouTubePlayerSupportFragment youTubePlayerFragment;
    public static String YOUTUBE_KEY = "AIzaSyBP7NieGXZ5BeejXLxdYaKMFHFCDdQkqm4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_vertical_youtube);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.title_youtube);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        YouTubePlayerSupportFragment youTubePlayerFragment =
                (YouTubePlayerSupportFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(YOUTUBE_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                self.player = player;
                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                    player.loadVideo(videoUrls[indexVideo]);
                    player.play();
                    indexVideo = (indexVideo < videoUrls.length - 1) ? indexVideo + 1 : 0;
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult errorReason) {
                if (errorReason.isUserRecoverableError()) {
                    errorReason.getErrorDialog(self, 1).show();
                } else {
                    String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
                    Toast.makeText(self, errorMessage, Toast.LENGTH_LONG).show();
                }
            }


        });
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_fragment);
        youTubeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()) player.pause();
                else player.play();
            }
        });
        viewTop =  youTubeView.getTop();
        displayHeight = ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getHeight();

    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        super.dispatchTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        if(!inRegion(event.getRawX(), event.getRawY(), youTubeView)) return false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if(startY != y)
                    moved = true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Animation animation = AnimationUtils.loadAnimation(this,R.anim.leaving);
//                Log.i("dispatchTouchEvent","y: " + y + " height: " + displayHeight);
                if(moved) {
                    if (startY - y > 0) {
                        Toast.makeText(this, "dislike", Toast.LENGTH_SHORT).show();
                        new ChangeVideo().execute(youTubeView);
                    } else if (startY - y < 0) {
                        Toast.makeText(this, "like", Toast.LENGTH_SHORT).show();
                        new ChangeVideo().execute(youTubeView);
                    } else {
                        //Not changed
                    }
                }else {
                    if(player.isPlaying()) player.pause();
                    else player.play();
                }
                moved = false;
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
                TimeUnit.MILLISECONDS.sleep(100);
                publishProgress();
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (Exception e){
                Log.e("ChangeVideo",e.toString(),e);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values){
            Animation animation = AnimationUtils.loadAnimation(self,R.anim.leaving_youtube_view);
            youTubeView.startAnimation(animation);
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            youTubeView.setTop(viewTop);
            player.loadVideo(videoUrls[indexVideo]);
            player.play();
            indexVideo = (indexVideo < videoUrls.length - 1) ? indexVideo + 1 : 0;
        }

    }

}
