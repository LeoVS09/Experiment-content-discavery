package cd.ex.leovs09.experiment_content_discavery;

import android.app.Service;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.EventObject;

/**
 * Created by LeoVS09 on 15.10.2016.
 */
public class SwipeLayout extends RelativeLayout {
    private ViewDragHelper dragHelper;
    private View dragView;
    private int displayHeight;
    private int startX;
    private int startY;
    public SwipeLayout(Context context){
        this(context,null);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeLayout(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
        dragHelper = ViewDragHelper.create(this,1.0f,new SwipeHelperCallback());
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        dragView = findViewById(R.id.imageView);
        displayHeight = ((WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE))
                                        .getDefaultDisplay().getHeight();
        startX = dragView.getPaddingLeft();
        startY = dragView.getPaddingTop();
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        if(action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP){
            dragHelper.cancel();
            return false;
        }
        dragHelper.shouldInterceptTouchEvent(event);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Toast.makeText(getContext(),event.toString(),Toast.LENGTH_LONG).show();

        dragHelper.processTouchEvent(event);
        return true;
    }

    private class SwipeHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child.getId() == R.id.imageView;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
//            requestLayout();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild,xvel,yvel);

            int newY = startY;
            if(yvel < displayHeight/3)
                newY = Math.max((int)yvel,-displayHeight/4);
            else if(yvel > (2*displayHeight)/3)
                newY = Math.min((int)yvel,(3*displayHeight)/4);

            dragHelper.settleCapturedViewAt(startX, newY);
            requestLayout();
        }



        @Override
        public int getViewVerticalDragRange(View child) {return -displayHeight/4;}


        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound =  -getHeight() + 2*(displayHeight - getHeight());
            final int bottomBound = -topBound;
            final int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx){
            startX = dragView.getLeft();
            return startX;
        }
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

}
