package cd.ex.leovs09.experiment_content_discavery.youtube_swipe.horizontal;

import android.content.Context;
import android.util.AttributeSet;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.swipe.SwipeHorizontalLayout;

/**
 * Created by LeoVS09 on 17.10.2016.
 */
public class SwipeYouTubeHorizontalLayout extends SwipeHorizontalLayout {
    public SwipeYouTubeHorizontalLayout(Context context){
        this(context,null);
    }

    public SwipeYouTubeHorizontalLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeYouTubeHorizontalLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        viewId = R.id.youtube_fragment;
    }

}
