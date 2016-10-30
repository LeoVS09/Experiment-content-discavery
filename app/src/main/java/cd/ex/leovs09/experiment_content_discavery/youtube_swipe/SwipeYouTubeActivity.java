package cd.ex.leovs09.experiment_content_discavery.youtube_swipe;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

/**
 * Created by LeoVS09 on 30.10.2016.
 */
public abstract class SwipeYouTubeActivity extends AppCompatActivity {
    protected int viewTop;
    protected int viewLeft;
    protected int displayHeight;
    protected int displayWidth;
    protected SwipeYouTubeActivity self = this;
    protected YouTubePlayer player;
    protected float startY = 0;
    protected boolean moved = false;
    protected String[] videoUrls = {
            "nCgQDjiotG0",
            "wKJ9KzGQq0w",
            "txBfhpm1jI0",
            "Q0oIoR9mLwc",
            "9c6W4CCU9M4",
            "avP5d16wEp0",
            "irH3OSOskcE",
            "cdgQpa1pUUE",
            "MSee-dADFlA",
            "wL-axMRQky8",
            "0h-IENieEFI"
    };
    protected int indexVideo = 0;
    protected YouTubePlayerView youTubeView;
    protected YouTubePlayerSupportFragment youTubePlayerFragment;
    public static String YOUTUBE_KEY = "AIzaSyBP7NieGXZ5BeejXLxdYaKMFHFCDdQkqm4";

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        YouTubePlayerSupportFragment youTubePlayerFragment =
                (YouTubePlayerSupportFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(YOUTUBE_KEY, new YouTubeOnInitializedListener());
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_fragment);
        youTubeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) player.pause();
                else player.play();
            }
        });
        viewTop = youTubeView.getTop();
        viewLeft = youTubeView.getLeft();
        displayHeight = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getHeight();
        displayWidth = ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getWidth();

    }

    protected class YouTubeOnInitializedListener implements YouTubePlayer.OnInitializedListener{
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
    }

    protected boolean inRegion(float x, float y, View v) {
        int [] coordBuffer = {0,0,0,0};
        v.getLocationOnScreen(coordBuffer);
        return coordBuffer[0] + v.getWidth() > x &&    // right edge
                coordBuffer[1] + v.getHeight() > y &&   // bottom edge
                coordBuffer[0] < x &&                   // left edge
                coordBuffer[1] < y;                     // top edge
    }

    protected abstract void preLeave();

    public class ChangeVideo extends AsyncTask<YouTubePlayerView,Void,Void> {
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
            preLeave();
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            youTubeView.setTop(viewTop);
            youTubeView.setLeft(viewLeft);
            player.loadVideo(videoUrls[indexVideo]);
            player.play();
            indexVideo = (indexVideo < videoUrls.length - 1) ? indexVideo + 1 : 0;
        }

    }
}