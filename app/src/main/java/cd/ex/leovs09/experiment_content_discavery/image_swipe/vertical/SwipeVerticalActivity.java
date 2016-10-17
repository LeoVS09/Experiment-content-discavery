package cd.ex.leovs09.experiment_content_discavery.image_swipe.vertical;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import cd.ex.leovs09.experiment_content_discavery.MainActivity;
import cd.ex.leovs09.experiment_content_discavery.R;



public class SwipeVerticalActivity extends AppCompatActivity {
    View dragView;
    private boolean swiping = false;
    private float startX;
    private float startY;
    private int displayHeight;
    private int currentImageIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_vertical_image);
        dragView = findViewById(R.id.imageView);

    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        displayHeight = ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getHeight();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        super.dispatchTouchEvent(event);

        float x = event.getX();
        float y = event.getY();
//        Log.i("onTouch",dragView.getPaddingTop() + " " + dragView.getHeight());
        if(!inRegion(event.getRawX(), event.getRawY(), dragView)) return false;
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                swiping = true;
                startX = x;
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Animation animation = AnimationUtils.loadAnimation(this,R.anim.leaving);
                if(swiping){
                    if(y < displayHeight/2){
                        Toast.makeText(this,"dislike",Toast.LENGTH_SHORT).show();
                        dragView.startAnimation(animation);
                        new ChangeImage().execute((ImageView)dragView);
                    }else if (y > (displayHeight)/2){
                        Toast.makeText(this,"like",Toast.LENGTH_SHORT).show();
                        dragView.startAnimation(animation);
                        new ChangeImage().execute((ImageView)dragView);
                    }else{
                        //Not changed
                    }
                }
                break;
        }
        return false;
    }
    private boolean inRegion(float x, float y, View v) {
        int [] mCoordBuffer = {0,0,0,0};
        v.getLocationOnScreen(mCoordBuffer);
        return mCoordBuffer[0] + v.getWidth() > x &&    // right edge
                mCoordBuffer[1] + v.getHeight() > y &&   // bottom edge
                mCoordBuffer[0] < x &&                   // left edge
                mCoordBuffer[1] < y;                     // top edge
    }


    private class ChangeImage extends AsyncTask<ImageView,Void,Void>{
        ImageView view;
        @Override
        protected Void doInBackground(ImageView... views){
            this.view = views[0];
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                Log.e("ChangeImage",e.toString(),e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            view.setImageResource(MainActivity.imagesId[currentImageIndex]);
            currentImageIndex = (currentImageIndex == MainActivity.imagesId.length-1)?
                    0 : currentImageIndex + 1;

        }

    }

}
