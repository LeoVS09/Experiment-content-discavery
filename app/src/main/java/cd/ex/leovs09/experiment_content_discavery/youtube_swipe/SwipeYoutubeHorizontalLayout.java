package cd.ex.leovs09.experiment_content_discavery.youtube_swipe;

import android.content.Context;
import android.util.AttributeSet;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.image_swipe.horizontal.SwipeHorizontalLayout;
import cd.ex.leovs09.experiment_content_discavery.image_swipe.vertical.SwipeVerticalLayout;

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
        viewId = R.id.youtube_view;
        verticalDragRange = 0;
        horizontalDragRange = -displayWidth;
    }

}