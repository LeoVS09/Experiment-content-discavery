package cd.ex.leovs09.experiment_content_discavery.youtube_fragment;

import android.content.Context;
import android.util.AttributeSet;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.image_swipe.horizontal.SwipeHorizontalLayout;

/**
 * Created by LeoVS09 on 17.10.2016.
 */
public class SwipeYoutubeHorizontalLayout extends SwipeHorizontalLayout {
    public SwipeYoutubeHorizontalLayout(Context context){
        this(context,null);
    }

    public SwipeYoutubeHorizontalLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeYoutubeHorizontalLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        viewId = R.id.youtube_fragment;
        verticalDragRange = 0;
        horizontalDragRange = -displayWidth;
    }

}
