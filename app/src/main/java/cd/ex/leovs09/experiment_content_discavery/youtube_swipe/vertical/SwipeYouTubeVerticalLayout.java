package cd.ex.leovs09.experiment_content_discavery.youtube_swipe.vertical;

import android.content.Context;
import android.util.AttributeSet;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.swipe.SwipeVerticalLayout;

/**
 * Created by LeoVS09 on 21.10.2016.
 */
public class SwipeYouTubeVerticalLayout extends SwipeVerticalLayout {
    public SwipeYouTubeVerticalLayout(Context context){
        this(context,null);
    }

    public SwipeYouTubeVerticalLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeYouTubeVerticalLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        viewId = R.id.youtube_fragment;
    }
}
