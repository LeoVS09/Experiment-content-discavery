package cd.ex.leovs09.experiment_content_discavery.swipe;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;

import cd.ex.leovs09.experiment_content_discavery.R;

/**
 * Created by LeoVS09 on 17.10.2016.
 */
public class SwipeHorizontalLayout extends SwipeLayout {
    public SwipeHorizontalLayout(Context context){
        this(context,null);
    }

    public SwipeHorizontalLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeHorizontalLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        verticalDragRange = 0;
        horizontalDragRange = -displayWidth;
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.DIRECTION_HORIZONTAL);
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
    @Override
    protected int newVertical(View child, int top, int dy) {
        startY = dragView.getTop();
        return startY;

    }
    @Override
    protected int newHorizontal(View child, int left, int dx){
        startX = dragView.getLeft();
        final int leftBound =  -displayWidth;
        final int rightBound = -leftBound;
        final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
        return newLeft;
    }


}
