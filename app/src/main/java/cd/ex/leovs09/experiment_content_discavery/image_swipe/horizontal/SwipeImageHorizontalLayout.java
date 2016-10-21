package cd.ex.leovs09.experiment_content_discavery.image_swipe.horizontal;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.swipe.SwipeHorizontalLayout;
import cd.ex.leovs09.experiment_content_discavery.swipe.SwipeLayout;

/**
 * Created by LeoVS09 on 17.10.2016.
 */
public class SwipeImageHorizontalLayout extends SwipeHorizontalLayout {
    public SwipeImageHorizontalLayout(Context context){
        this(context,null);
    }

    public SwipeImageHorizontalLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeImageHorizontalLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        viewId = R.id.alboms;
    }
}
