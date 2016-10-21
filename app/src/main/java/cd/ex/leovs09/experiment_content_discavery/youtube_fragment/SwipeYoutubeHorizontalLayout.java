package cd.ex.leovs09.experiment_content_discavery.youtube_fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

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
        viewId = R.id.youtube_view;
        verticalDragRange = 0;
        horizontalDragRange = -displayWidth;
    }
    @Override
    protected int[] settleReleasedView(View releasedChild, float xvel, float yvel) {
        int newX = startX;
        if(xvel < displayWidth/3)
            newX = -displayWidth;
        else if(xvel > (2*displayHeight)/3)
            newX = displayWidth + displayWidth/2;
        int[] result = {newX,startY};
        return result;
    }
}
