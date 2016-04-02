package com.jc.testdrag;

import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jc.testdrag.helper.ItemTouchHelperAdapter;
import com.jc.testdrag.helper.ItemTouchHelperViewHolder;
import com.jc.testdrag.helper.OnStartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by solar on 2016/3/30.
 */
public class RCAdatper extends RecyclerView.Adapter<RCAdatper.ViewHolder> implements ItemTouchHelperAdapter {

    List<String> list;
    private OnStartDragListener mDragStartListener;

    public RCAdatper(List<String> list, OnStartDragListener dragStartListener) {
        this.list = list;
        this.mDragStartListener = dragStartListener;
    }

    @Override
    public RCAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.item_drag, null));
    }

    @Override
    public void onBindViewHolder(final RCAdatper.ViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
//                mDragStartListener.onStartDrag(holder);
                return false;
            }
        });
        holder.iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    onItemDismiss(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(list, fromPosition, toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        //删除mItems数据
        list.remove(position);
        //删除RecyclerView列表对应item
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        TextView tv;
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
