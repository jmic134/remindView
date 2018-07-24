package com.do_demo.remindview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


/***
 * 带数字的按钮
 *
 * @date 2015年12月3日
 */
public class RemindButton extends RelativeLayout {
    private LayoutInflater inflater;
    private Context context;
    private ImageButton btn;
    private TextView txt_num;
    private int view_type;
    private View small;
    private int icon_src;
    private int bg_big;
    private int bg_small;
    private int num_max;
    private float textsize;


    public RemindButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.view_remind_button, this);

        btn = (ImageButton) rootView.findViewById(R.id.img_remind_src);


        txt_num = (TextView) rootView.findViewById(R.id.txt_remind_num);
        small = rootView.findViewById(R.id.img_remind_small);

        txt_num.setVisibility(View.INVISIBLE);
        small.setVisibility(View.INVISIBLE);
        readAttr(context, attrs);
        initData();
    }


    private void initData() {
        setNum(0);
        btn.setImageResource(icon_src);
        txt_num.setBackgroundResource(bg_big);
        txt_num.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        small.setBackgroundResource(bg_small);
    }


    private void readAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RemindButtonView);
        icon_src = typedArray.getResourceId(R.styleable.RemindButtonView_remind_icon_res, R.mipmap.ic_launcher);
        num_max=typedArray.getInt(R.styleable.RemindButtonView_remind_num_max,99);
        view_type = typedArray.getInt(R.styleable.RemindButtonView_remind_type, 0);

        bg_big=typedArray.getResourceId(R.styleable.RemindButtonView_remind_type_big_bg,R.drawable.bg_red_num);
        bg_small=typedArray.getResourceId(R.styleable.RemindButtonView_remind_type_small_bg,R.drawable.bg_red_num_small);
         textsize = typedArray.getDimension(R.styleable.RemindButtonView_remind_num_size, txt_num.getTextSize());






    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        //super.onMeasure(btn.getMeasuredWidth(), btn.getMeasuredHeight());
    }

    public TextView getTextView() {
        return txt_num;
    }

    /***
     * @param num 设置红点个数
     *            0:红点消失,小于0显示无数字小红点
     */
    public void setNum(int num) {
        String numStr = null;

        numStr = num > num_max ? num_max + "+" : num + "";

        switch (view_type) {
            case 0:
                if (num > 0) {
                    txt_num.setVisibility(View.VISIBLE);
                    txt_num.setText(numStr);
                } else if (num < 0) {
                    txt_num.setVisibility(View.VISIBLE);
                    txt_num.setText(" ");
                } else {
                    txt_num.setVisibility(View.INVISIBLE);
                }

                break;
            case 1:
                if (num > 0) {
                    small.setVisibility(View.VISIBLE);

                } else {
                    small.setVisibility(View.INVISIBLE);
                }

                break;
        }


    }

    /***
     * 设置小红点文字
     * @param txt
     */
    public void setNum(String txt) {

        if(view_type==0){

            txt_num.setText(txt);
            txt_num.setVisibility(View.VISIBLE);
        }




    }


    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        btn.setOnClickListener(l);
    }

    /**
     * 设置按钮背景
     *
     * @param res
     */
    public void setIcon(int res) {
        btn.setImageResource(res);
    }

    /***
     * 设置背景
     * @param res
     */
    public void setBgBig(int res){
        bg_big=res;
        small.setBackgroundResource(bg_small);
    }

    /***
     * 设置背景
     * @param res
     */
    public void setBgSmall(int res) {
        this.bg_small = res;
        small.setBackgroundResource(bg_small);
    }
}
