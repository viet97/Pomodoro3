package techkids.vn.android7pomodoro.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final String TAG = "ColorAdapter";
    private int selectedPosition;
    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View colorView = layoutInflater.inflate(R.layout.item_color,parent,false);
        ColorViewHolder colorViewHolder = new ColorViewHolder(colorView);
        return colorViewHolder;
    }
    public String getSelectedColor(){
        return DbContext.instance.allcolor().get(selectedPosition);
    }
    public void setColor(String color){
        selectedPosition = 0;
        for(int i=0 ;i < DbContext.instance.allcolor().size();i++){
            if (DbContext.instance.allcolor().get(i) == color ){
                selectedPosition =i;
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(ColorViewHolder holder, final int position) {
        String color = DbContext.instance.allcolor().get(position);
        holder.bind(color);
        if (selectedPosition == position){
            holder.setCheck(true);
        }else{
            holder.setCheck(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return DbContext.instance.allcolor().size();
    }


}
