package com.pera.tanping.peratech.framework.module.goods;
/*

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG

*/

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pera.tanping.peratech.GlideApp;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseFragment;
import com.pera.tanping.peratech.framework.module.discover.BannerBean;
import com.pera.tanping.peratech.framework.utils.ScreenUtil;
import com.utils.ui.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.youth.banner.BannerConfig.NUM_INDICATOR;

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/9/8 8:36
 *
 * @author tanping
 */
public class GoodsDetailFragment extends BaseFragment {

    public static final String GOODS_ID = "goods_id";

    public String goodsId;
    @BindView(R.id.goods_pictrue)
    Banner goodsPictrue;
    @BindView(R.id.goods_title)
    TextView goodsTitle;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.goods_model)
    TextView goodsModel;
    @BindView(R.id.ig_del)
    ImageView igDel;
    @BindView(R.id.et_value)
    EditText etValue;
    @BindView(R.id.ig_add)
    ImageView igAdd;
    @BindView(R.id.goods_desc)
    WebView goodsDesc;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.ll_buttom)
    LinearLayout llButtom;

    @BindView(R.id.goback)
    ImageView goBack;

    Unbinder unbinder;
    private double number;


    public static GoodsDetailFragment getInstance(Bundle bundle) {

        GoodsDetailFragment fragment = new GoodsDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            goodsId = bundle.getString(GOODS_ID);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        multiStatusView.showContent();
        unbinder = ButterKnife.bind(this, multiStatusView);


        goodsDesc.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        goodsDesc.loadUrl("http://www.baidu.com");
        createBanner();
    }


    public void createBanner(){

        Banner banner = goodsPictrue;
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BannerBean bannerBean = (BannerBean) path;
                GlideApp.with(getContext().getApplicationContext()).asBitmap().load(bannerBean.url).into(imageView);
            }
        });

        List<BannerBean> bannerBeans = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i=0;i<5;i++){
            BannerBean bannerBean = new BannerBean();
            bannerBean.url = "https://f11.baidu.com/it/u=2465775762,1509670197&fm=72";
            bannerBeans.add(bannerBean);

            titles.add("");
        }

        //设置图片集合
        banner.setImages(bannerBeans);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setBannerStyle(NUM_INDICATOR);

        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.getLayoutParams().height = ScreenUtil.getScreenWidth(getContext())*3/4;
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_goodsdetail;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ig_del, R.id.ig_add, R.id.tv_save,R.id.goback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ig_del:
                del();
                break;
            case R.id.ig_add:
                add();
                break;
            case R.id.tv_save:
                onSave();
                break;
            case R.id.goback:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    public void add(){

        int value = getEtValue();
        value = value +1;

        setEtValue(value);

    }
    public void del(){
        int value = getEtValue();
        if (value<=0){
            return;
        }

        setEtValue(value-1);
    }


    public int getEtValue(){
        String str = etValue.getText().toString();
        try {
            number = new Double(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            number = 0;
        }
        return (int) number;
    }

    public void setEtValue (int value){
        etValue.setText(value+"");
        number = value;
    }
    public void onSave(){
        ToastUtil.showToast(getActivity(),"save");
    }
}
