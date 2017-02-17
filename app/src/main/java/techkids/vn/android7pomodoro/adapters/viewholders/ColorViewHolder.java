package techkids.vn.android7pomodoro.adapters.viewholders;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by Quoc Viet Dang on 2/11/2017.
 */

public class ColorViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.v_color)
    View view;
    @BindView(R.id.iv_color)
    ImageView imageView;
    public ColorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(String color){
        GradientDrawable drawable = (GradientDrawable)view.getBackground();
        drawable.setColor(Color.parseColor(color));
    }
    public void setCheck(boolean check){
        if (check){
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
