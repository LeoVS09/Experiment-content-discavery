package cd.ex.leovs09.experiment_content_discavery;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by LeoVS09 on 15.10.2016.
 */
public class SwipeLayout extends RelativeLayout {
    private ViewDragHelper dragHelper;
    private View dragView;

    public SwipeLayout(Context context){
        this(context,null);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeLayout(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
        dragView = findViewById(R.id.imageView);
        dragHelper = ViewDragHelper.create(this,1.0f,new SwipeHelperCallback());
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
//            Toast.makeText(getContext(),releasedChild.toString(),Toast.LENGTH_LONG).show();
            dragHelper.settleCapturedViewAt((int)xvel, (int)yvel);
        }

        @Override
        public int getViewVerticalDragRange(View child) {return getPaddingTop();}

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
//
//            final int topBound = getPaddingTop();
//            final int bottomBound = getHeight() - dragView.getHeight();
            final int newTop = top;
//            Toast.makeText(getContext(),top,Toast.LENGTH_SHORT).show();
            return newTop;
        }
    }
}
