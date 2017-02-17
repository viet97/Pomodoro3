package techkids.vn.android7pomodoro.decorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Quoc Viet Dang on 2/15/2017.
 */

public class TaskColorSpaceDecor extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
       outRect.top = 16;
        outRect.right =16;
    }
}
