package publiccloud.example.com.popgridview;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import publiccloud.example.com.popgridview.R;

/**
 * Created by mac on 17/10/15.
 */

public class RatioFrameLayout extends FrameLayout{
    // 宽和高的比例
    private float ratio = 0.0f;
    public RatioFrameLayout(Context context) {
        this(context, null);
    }
    public RatioFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public RatioFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //从xml中获取比例值（需在values下的attrs下声明）
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        ratio = a.getFloat(R.styleable.RatioLayout_ratio, 0.0f);
        a.recycle();
    }
    /**
     * 可代码设置，或者布局中设置
     <span style="white-space:pre">    </span> * 设置宽高比例
     * @param f
     */
    public void setRatio(float f) {
        ratio = f;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        //MeasureSpec.EXACTLY精确模式
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0.0f) {
            height = (int) (width / ratio + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + getPaddingTop() + getPaddingBottom(),
                    MeasureSpec.EXACTLY);
        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY && ratio != 0.0f) {
            width = (int) (height * ratio + 0.5f);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width + getPaddingLeft() + getPaddingRight(),
                    MeasureSpec.EXACTLY);
        }else {
            new RuntimeException("设置比例值，宽高需要一个是精确模式");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
