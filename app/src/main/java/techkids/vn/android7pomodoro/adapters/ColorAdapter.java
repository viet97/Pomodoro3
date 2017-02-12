package techkids.vn.android7pomodoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.adapters.viewholders.ColorViewHolder;
import techkids.vn.android7pomodoro.databases.DbContext;

/**
 * Created by Quoc Viet Dang on 2/11/2017.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorViewHolder> {
    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View colorView = layoutInflater.inflate(R.layout.item_color,parent,false);
        ColorViewHolder colorViewHolder = new ColorViewHolder(colorView);
        return colorViewHolder;
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        String color = DbContext.instance.allcolor().get(position);
        holder.bind(color);
    }

    @Override
    public int getItemCount() {
        return DbContext.instance.allcolor().size();
    }
}
