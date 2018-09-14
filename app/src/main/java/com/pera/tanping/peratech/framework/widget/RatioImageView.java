package com.pera.tanping.peratech.framework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.pera.tanping.peratech.GlideApp;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.utils.StringUtil;
import com.pera.tanping.peratech.framework.utils.klog.KLog;


/**
 *
 * @Created by tanping
 *
 * 不能加载动画，会有bug。除非高度限高
 *
 * 三种模式 ：
 * ratio = 0 普通image
 * ratio >0  按比例
 * ratio <0  根据图片适配高度
 *
 */

public class RatioImageView extends android.support.v7.widget.AppCompatImageView  {
    //宽高比，由我们自己设定
    private float ratio;
    String imageUrl ;
    boolean isLoadSuccess ;
    public RatioImageView(Context context ) {
        super(context);

    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        //根据属性名称获取对应的值，属性名称的格式为类名_属性名
        ratio = typedArray.getFloat(R.styleable.RatioImageView_ratio, 0.0f);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (ratio == 0.0){
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }else if (ratio > 0){
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = Math.round(width/ratio);
            try {
                setMeasuredDimension(width,height);
            } catch (Exception e) {
                super.onMeasure(widthMeasureSpec,heightMeasureSpec);
                e.printStackTrace();
            }
        }else { // radio <0 高度随图片变
            Drawable d = getDrawable();
            if (d != null) {
                // ceil not round - avoid thin vertical gaps along the left/right edges
                int width = MeasureSpec.getSize(widthMeasureSpec);
                //宽度定- 高度根据使得图片的宽度充满屏幕
                int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
                try {
                    setMeasuredDimension(width, height);
                } catch (Exception e) {
                    e.printStackTrace();
                    super.onMeasure(widthMeasureSpec,heightMeasureSpec);
                }
            }
        }
    }


    /**
     * 设置宽高比
     * @param ratio
     */
    public void setRatio(float ratio){
        this.ratio = ratio;
    }


    /**
     * 宽高比
     * @param width
     * @param height
     */
    public void setRatio(float width,float height){
        if (height == 0 ){
            ratio = -1;
            return;
        }
        ratio = width / height;
        requestLayout();
    }

    public float getRatio(){
        return  ratio;
    }






    Target imageTarget = new ImgeViewTargete(this);
    RequestOptions requestOption  = new RequestOptions();
    /**
     * 封装交给年轻人
     * 设置图片 - 最简单的方法
     * @param url
     */
    public void setUrl(final String url){
        setUrl(url,null);
    }

    /**
     * 设置图片
     * @param url
     */
    public void setUrl(final String url,ScaleType scaleType){
        this.imageUrl = url ;
        isLoadSuccess =false;
        KLog.d("ImageLoader", "ImageLoad>>>> ratio : "+ ratio +" - url:" + url );
        //清理
        GlideApp.with(getContext().getApplicationContext()).clear(imageTarget);
        //gif 图
        if (StringUtil.isGif(url)){
            setScaleType(ScaleType.CENTER_INSIDE);
            GlideApp.with(getContext()).asGif().load(url).listener(new RequestListener<GifDrawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                    isLoadSuccess =false;
                    setScaleType(ScaleType.CENTER_INSIDE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                    setScaleType(scaleType);
                    return false;
                }
            }).into(this);
        }else {
            setScaleType(ScaleType.CENTER_INSIDE);
            GlideApp.with(this).asDrawable().listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    isLoadSuccess =false;
                    setScaleType(ScaleType.CENTER_INSIDE);
                    return false;
                }
                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    KLog.d("ImageLoader2","onResourceReady>>>> w : "+ getWidth() +" - h:" + getHeight()  +  " =--" + imageUrl);
                    isLoadSuccess =true;
                    if (scaleType!=null) {
                        setScaleType(scaleType);
                    }
                    return false;
                }
            }).load(url).into(imageTarget);
        }

    }





    public boolean isLoadSuccess() {
        return isLoadSuccess;
    }

    public   class ImgeViewTargete  extends ImageViewTarget<Drawable> {

        public ImgeViewTargete(@NonNull ImageView view) {
            super(view);
        }

        @Override
        protected void setResource(@Nullable Drawable resource) {
            isLoadSuccess = true;
            RatioImageView.this.setImageDrawable(resource);
        }

        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {
            super.onLoadFailed(errorDrawable);
            isLoadSuccess =false;
        }

        @Override
        public void getSize(@NonNull SizeReadyCallback cb) {
//            KLog.d("ImageLoader2","ImageLoad>>>> w : "+ getWidth() +" - h:" + getHeight()  +  " =--" + imageUrl);
            super.getSize(cb);
        }
    }
}