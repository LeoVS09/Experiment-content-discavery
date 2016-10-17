package cd.ex.leovs09.experiment_content_discavery.image_swipe.horizontal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import cd.ex.leovs09.experiment_content_discavery.MainActivity;
import cd.ex.leovs09.experiment_content_discavery.R;


public class SwipeHorizontalActivity extends AppCompatActivity {
    View dragView;
    private boolean swiping = false;
    private float startX;
    private float startY;
    private int displayWidth;
    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_swipe_horizontal_image);
        dragView = findViewById(R.id.alboms);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        displayWidth = ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getWidth();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        super.dispatchTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
//        Log.i("onTouch",dragView.getPaddingTop() + " " + dragView.getHeight());
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
                if(swiping){
                    if(x < displayWidth/2){
                        Toast.makeText(this,"dislike",Toast.LENGTH_SHORT).show();
                        new ChangeImage().execute((ImageView)dragView);
                    }else if (x > (displayWidth)/2){
                        Toast.makeText(this,"like",Toast.LENGTH_SHORT).show();
                        new ChangeImage().execute((ImageView)dragView);
                    }else{
                        //Not changed
                    }
                }
                break;
        }
        return false;
    }


    private class ChangeImage extends AsyncTask<ImageView,Void,Void> {
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
