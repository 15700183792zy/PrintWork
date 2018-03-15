package publiccloud.example.com.popgridview.flowlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import publiccloud.example.com.popgridview.GvDate;
import publiccloud.example.com.popgridview.R;
/**
 * Created by mac on 18/3/12.
 */

public  class GvAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GvDate> StringArrayList;

    public GvAdapter(Context context, ArrayList<GvDate> StringArrayList) {
        //  Log.e("-------------","初始化"+StringArrayList.size());
        this.context = context;
        this.StringArrayList = StringArrayList;
    }

    /* 当ListView数据发生变化时,调用此方法来更新ListView
    *
    * @param list
    */
    public void updateListView(ArrayList<GvDate> list) {
        if (list == null) {
            this.StringArrayList = new ArrayList<GvDate>();
        } else {
            this.StringArrayList = list;
        }
        //  Log.e("-------------",StringArrayList.size()+"");
        notifyDataSetChanged();
    }

    public ArrayList<GvDate> getStringArrayList() {
        return StringArrayList;
    }

    public void setStringArrayList(ArrayList<GvDate> StringArrayList) {
        this.StringArrayList = StringArrayList;
    }

    @Override
    public int getCount() {
        return StringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return StringArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_fragment_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        GvDate gvDate = StringArrayList.get(position);
        // Log.e("----------------"+position,String.getName()+"  "+String.getIcon());
        holder.base_tx.setText(gvDate.getName());
        holder.base_img.setBackgroundResource(getImage(gvDate.getImg()));

        return convertView;
    }

    //反射的到id
    public static  int getImage(String pic) {
        int id = R.mipmap.ic_launcher;
        if (pic == null || pic.trim().equals("")) {
            id = R.mipmap.ic_launcher;
        }else {
            Class cls = R.drawable.class;
            try {
                Field field = cls.getDeclaredField(pic);
                field.setAccessible(true);
                id = field.getInt(pic);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
    private  class ViewHolder{
        private TextView base_tx;
        private ImageView base_img;
        View view;
        ViewHolder(View v){
            view=v;
            base_tx = (TextView) v.findViewById(R.id.tv_show);
            base_img = (ImageView) v.findViewById(R.id.iv_show);
        }
    }
}
