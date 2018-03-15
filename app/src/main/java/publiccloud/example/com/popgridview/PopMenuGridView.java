package publiccloud.example.com.popgridview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import publiccloud.example.com.popgridview.flowlayout.GvAdapter;


/**
 * @author 周岩
 * @class PopMenuView 继承PopupWindow
 * * @description 单据右侧按钮。。。的弹出框
 */
public class PopMenuGridView extends PopupWindow implements GridView.OnItemClickListener {

    private View mMenuView;
    private GridView flowlayout;
//    private ViewFlipper viewfipper;
    private Handler mHandler;
    private int handlerKey;
    private ArrayList<GvDate> mlist;
    private GvAdapter mAdapter;

    public PopMenuGridView(Activity context, final ArrayList<GvDate> mlist, Handler mHandler, int handlerKey) {
        super(context);
        this.mHandler = mHandler;
        this.handlerKey = handlerKey;
        this.mlist = mlist;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popmenugridview, null);

        flowlayout = (GridView) mMenuView.findViewById(R.id.gv_show);

        flowlayout.setOnItemClickListener(this);
        mAdapter = new GvAdapter(context,mlist);

        mMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        flowlayout.setAdapter(mAdapter);
        this.setContentView(mMenuView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x55000000);
        this.setBackgroundDrawable(dw);
        this.setOutsideTouchable(true);
        this.update();

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Message msg = new Message();
        msg.what = handlerKey;
        msg.obj = mlist.get(position).getName();
        mHandler.sendMessage(msg);
        dismiss();
    }
}
