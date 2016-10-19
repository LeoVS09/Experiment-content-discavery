package cd.ex.leovs09.experiment_content_discavery.image_swipe.vertical;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.SwipeLayout;

/**
 * Created by LeoVS09 on 15.10.2016.
 */
public class SwipeVerticalLayout extends SwipeLayout {

    public SwipeVerticalLayout(Context context){
        this(context,null);
    }

    public SwipeVerticalLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeVerticalLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        viewId = R.id.imageView;
        horizontalDragRange = 0;
        verticalDragRange = -displayHeight/4;
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.DIRECTION_VERTICAL);

    }
    @Override
    protected int[] settleReleasedView(View releasedChild, float xvel, float yvel) {
        int newY = startY;
        if(yvel < displayHeight/3)
            newY = Math.max((int)yvel,-displayHeight/4);
        else if(yvel > (2*displayHeight)/3)
            newY = Math.min((int)yvel,(3*displayHeight)/4);
        int[] result = {startX,newY};
        return result;
    }
    @Override
    protected int newVertical(View child, int top, int dy) {
        final int topBound =  -getHeight() + 2*(displayHeight - getHeight());
        final int bottomBound = -topBound;
        final int newTop = Math.min(Math.max(top, topBound), bottomBound);
        return newTop;
    }
    @Override
    protected int newHorizontal(View child, int left, int dx){
        startX = dragView.getLeft();
        return startX;
    }


}
