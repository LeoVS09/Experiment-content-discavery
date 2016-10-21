package cd.ex.leovs09.experiment_content_discavery.image_swipe.vertical;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;

import cd.ex.leovs09.experiment_content_discavery.R;
import cd.ex.leovs09.experiment_content_discavery.swipe.SwipeLayout;
import cd.ex.leovs09.experiment_content_discavery.swipe.SwipeVerticalLayout;

/**
 * Created by LeoVS09 on 15.10.2016.
 */
public class SwipeImageVerticalLayout extends SwipeVerticalLayout {

    public SwipeImageVerticalLayout(Context context){
        this(context,null);
    }

    public SwipeImageVerticalLayout(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public SwipeImageVerticalLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        viewId = R.id.imageView;
    }
}
