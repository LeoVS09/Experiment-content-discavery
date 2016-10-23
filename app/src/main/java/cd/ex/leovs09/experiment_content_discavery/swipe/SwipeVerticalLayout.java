package cd.ex.leovs09.experiment_content_discavery.swipe;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cd.ex.leovs09.experiment_content_discavery.R;

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
        horizontalDragRange = 0;
        verticalDragRange = -displayHeight;
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.DIRECTION_VERTICAL);

    }
    @Override
    protected int[] settleReleasedView(View releasedChild, float xvel, float yvel) {
        int newY = startY;
        Log.i("settleReleasedView","y: " + yvel + " height: " + displayHeight);
        if(yvel < -displayHeight/3)
            newY = Math.max((int)yvel,50);
        else if(yvel > displayHeight/3)
            newY = Math.min((int)yvel,2*displayHeight/3 - 200);
        int[] result = {startX,newY};
        Log.i("settleReleasedView","result: "+ result[0] + " " + result[1]);
        return result;
    }
    @Override
    protected int newVertical(View child, int top, int dy) {
        Log.i("newVertical","top: " + top + " dy: " + dy);
        final int topBound =  0;
        final int bottomBound = displayHeight;
        final int newTop = Math.min(Math.max(top, topBound), bottomBound);
        Log.i("newVertical","newTop: " + newTop);
        return newTop;
    }
    @Override
    protected int newHorizontal(View child, int left, int dx){
        startX = dragView.getLeft();
        return startX;
    }


}
