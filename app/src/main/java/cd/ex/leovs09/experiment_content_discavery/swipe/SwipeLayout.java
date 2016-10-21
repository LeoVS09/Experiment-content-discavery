package cd.ex.leovs09.experiment_content_discavery.swipe;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import cd.ex.leovs09.experiment_content_discavery.R;

/**
 * Created by LeoVS09 on 17.10.2016.
 */
public class SwipeLayout extends RelativeLayout {
    protected ViewDragHelper dragHelper;
    protected View dragView;
    protected int displayHeight;
    protected int displayWidth;
    protected int startX;
    protected int startY;
    protected int verticalDragRange;
    protected int horizontalDragRange;
    protected int viewId;
    public SwipeLayout(Context context){
        this(context,null);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        dragHelper = ViewDragHelper.create(this,1.0f,new SwipeHelperCallback());
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        dragView = findViewById(viewId);
        displayHeight = ((WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getHeight();
        displayWidth = ((WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getWidth();
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
        dragHelper.processTouchEvent(event);
        return true;
    }
    /*
        extend realised method for set end of move
     */
    protected int[] settleReleasedView(View releasedChild, float xvel, float yvel) {

        int[] result = {0,0};
        return result;
    }
    /*
        extend vertical method for set vertical on move
     */
    protected int newVertical(View child, int top, int dy) {
        return 0;
    }
    /*
        extend vertical method for set horizontal on move
     */
    protected int newHorizontal(View child, int left, int dx){
        return 0;
    }
    protected class SwipeHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child.getId() == viewId;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
//            requestLayout();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild,xvel,yvel);
            int [] res = settleReleasedView(releasedChild,xvel,yvel);
            dragHelper.settleCapturedViewAt(res[0], res[1]);
            requestLayout();
        }

        @Override
        public int getViewVerticalDragRange(View child) {return verticalDragRange;}

        @Override
        public int getViewHorizontalDragRange(View child) {return horizontalDragRange;}


        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return newVertical(child,top,dy);
        }
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx){
            return newHorizontal(child,left,dx);
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
