package publiccloud.example.com.popgridview;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyHandler mMyHandler;
    TextView viewById;
    LinearLayout ll_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyHandler = new MyHandler();
        viewById = (TextView)findViewById(R.id.tv_show);
        ll_show = (LinearLayout) findViewById(R.id.ll_show);

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrder();
            }
        });

    }

    private ArrayList<GvDate> mlist = null;

    private void showOrder() {
        if (mlist == null) {
            mlist = new ArrayList<>();
            GvDate gvDate=null;
            gvDate = new GvDate("商品修改", "icon_editor");
            mlist.add(gvDate);
            gvDate = new GvDate("撤销", "icon_cancel");
            mlist.add(gvDate);
            gvDate = new GvDate("一键退货", "icon_return");
            mlist.add(gvDate);
            gvDate = new GvDate("复制为销售单", "icon_sellorder");
            mlist.add(gvDate);
            gvDate = new GvDate("复制为采购单", "icon_buyorder");
            mlist.add(gvDate);
            gvDate = new GvDate("挂单列表", "icon_stayorder");
            mlist.add(gvDate);
            gvDate = new GvDate("重新开单", "icon_replaceorder");
            mlist.add(gvDate);
            gvDate = new GvDate("备注", "icon_remark");
            mlist.add(gvDate);
            gvDate = new GvDate("临时打印", "icon_print");
            mlist.add(gvDate);
            gvDate = new GvDate("分享", "icon_share");
            mlist.add(gvDate);

        }
        if (ppMenuView == null) {
            ppMenuView = new PopMenuGridView(MainActivity.this, mlist, mMyHandler, 1);
        }
        ppMenuView.showAtLocation(ll_show, Gravity.CENTER, 0, 0);

    }

    PopMenuGridView ppMenuView = null;

    private class MyHandler extends Handler {

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            int what = msg.what;
            switch (what) {
                case 1:
                    String str = ((String) msg.obj).toString();
                    viewById.setText(str);
                    break;
            }
        }

    }

}
