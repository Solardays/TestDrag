package com.jc.testdrag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.jc.testdrag.helper.OnStartDragListener;
import com.jc.testdrag.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rc;

    private RCAdatper adatper;
    private List<String> list;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc = (RecyclerView) findViewById(R.id.rc);
        rc.setHasFixedSize(true);//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        RecyclerView.LayoutManager lm = new GridLayoutManager(this,4);
        rc.setLayoutManager(lm);

        list = new ArrayList<>();
        for (int i = 0;i<8;i++){
            list.add("item" + i);
        }
        adatper = new RCAdatper(list, new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                itemTouchHelper.startDrag(viewHolder);
            }
        });
        rc.setAdapter(adatper);

        itemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adatper));
        itemTouchHelper.attachToRecyclerView(rc);

    }


    public void addlist(View v){
        list.add("aaaaaa");
        adatper.notifyDataSetChanged();
    }


}
