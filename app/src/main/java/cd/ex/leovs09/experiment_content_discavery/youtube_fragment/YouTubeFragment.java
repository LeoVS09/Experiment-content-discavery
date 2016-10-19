package cd.ex.leovs09.experiment_content_discavery.youtube_fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayerFragment;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.YouTubeFailureRecoveryActivity;

/**
 * Created by LeoVS09 on 19.10.2016.
 */
public class YouTubeFragment extends Fragment {
    private String videoUrl;

    public YouTubeFragment setVideo(String videoUrl){
        this.videoUrl = videoUrl;
        return this;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(YouTubeFailureRecoveryActivity.YOUTUBE_KEY, this);
        return inflater.inflate(R.layout.youtube_fragment, null);
    }
}
