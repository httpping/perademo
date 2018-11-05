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
import android.text.Editable;
import android.text.TextWatcher;
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
import com.pera.tanping.peratech.framework.base.NetResult;
import com.pera.tanping.peratech.framework.bean.goods.BrandBean;
import com.pera.tanping.peratech.framework.bean.goods.GoodsBean;
import com.pera.tanping.peratech.framework.event.UpdateGoodsPriceEvent;
import com.pera.tanping.peratech.framework.module.discover.BannerBean;
import com.pera.tanping.peratech.framework.remote.ApiManager;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.remote.config.XGsonSubscriber;
import com.pera.tanping.peratech.framework.remote.model.LoginManager;
import com.pera.tanping.peratech.framework.utils.DoubleFromat;
import com.pera.tanping.peratech.framework.utils.ScreenUtil;
import com.pera.tanping.peratech.framework.utils.StringUtil;
import com.pera.tanping.peratech.framework.widget.AttrSelectView;
import com.utils.ui.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.pera.tanping.peratech.framework.module.goods.GoodsDetaillActivity.PRODUCT_ID;
import static com.youth.banner.BannerConfig.NUM_INDICATOR;

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/9/8 8:36
 *
 * @author tanping
 */
public class GoodsDetailFragment extends BaseFragment {


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

    @BindView(R.id.attr_ll)
    LinearLayout attrLL;

    @BindView(R.id.goback)
    ImageView goBack;

    Unbinder unbinder;
    private int number =1;
    private GoodsBean goodsBean;


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
            goodsId = bundle.getString(PRODUCT_ID);
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

//        goodsDesc.loadUrl("http://www.baidu.com");
        getBrandList(true);

        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                try {
                    number  = Integer.parseInt(str);
                    updateTotal();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    number = 1;
                }
            }
        });
    }


    public void createBanner(List<String> pics){

        Banner banner = goodsPictrue;
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
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
        for (int i=0;i<pics.size();i++){
            BannerBean bannerBean = new BannerBean();
            bannerBean.url = StringUtil.getImagePath(pics.get(i));
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




    private void getBrandList(boolean showDialog){
        RequestParam param = new RequestParam();
        param.put("categoryid",goodsId);
        ApiManager.Api().getProductInfo(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<GoodsBean>>>(getActivity(),showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<GoodsBean>> listNetResult) {
                        if (listNetResult.isSuccess()){
                            updateView(listNetResult.Data);
                        }else{
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();

                    }
                });


    }


    List<AttrSelectView> attrSelectViews = new ArrayList<>();
    private void updateView(List<GoodsBean> data) {

        goodsBean = data.get(0);
        goodsTitle.setText(goodsBean.productName);
//        goodsPrice.setText("￥" + DoubleFromat.fromat(goodsBean.salePrice,2, RoundingMode.FLOOR));
        goodsPrice.setVisibility(View.GONE);
        goodsModel.setText(goodsBean.productName);
        goodsDesc.loadData(goodsBean.description,"text/html", "UTF -8");

        String[] pics = goodsBean.thumbnailsUrll.split(",");
        List arrayList = new ArrayList();
        if (pics!=null){
            for (int i =0;i<pics.length;i++){
                arrayList.add( pics[i]);
            }
        }
        createBanner(arrayList);


        List<GoodsBean.ProductattrlistBean> productattrlist =  goodsBean.productattrlist;
        if (productattrlist !=null){
            for (GoodsBean.ProductattrlistBean productattrlistBean : productattrlist){
                AttrSelectView attrSelectView = new AttrSelectView(getContext());
                attrSelectView.updateView(productattrlistBean);
                attrLL.addView(attrSelectView);
                attrSelectViews.add(attrSelectView);
            }
        }
        //attr_ll
        updateTotal();
    }

    public void updateTotal(){
        if (goodsBean !=null){
         /*   double price = number * goodsBean.salePrice;

            String total =  DoubleFromat.fromat(price,2, RoundingMode.FLOOR);
            tvTotal.setText("￥"+total);*/

            updatePrice(null);
        }
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
            number = new Integer(str);
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
        RequestParam param = new RequestParam();
        try {
            String userId = LoginManager.getInstance().getUser().id;
            param.put("userid",userId);
            double total = number * goodsBean.salePrice;
            param.put("order_amount",total+"");

            JSONArray jsonArray = new JSONArray();
            //{"ProductID":"21","Price":"160","Quantity":"1"}
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ProductID",goodsBean.id);
            jsonObject.put("Price",goodsBean.salePrice);
            jsonObject.put("Quantity",number);
            jsonArray.put(jsonObject);

            JSONObject object = new JSONObject();
            object.put("productlist",jsonArray);

//            param.setJsonParams(object.toString());

            param.put("productlist",jsonArray.toString());
        } catch (Exception e) {
            LoginManager.getInstance().toLogin(getActivity());
            e.printStackTrace();
        }
        ApiManager.Api().createdOrder(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<GoodsBean>>>(getActivity(),true) {
                    @Override
                    public void onSuccess(NetResult<List<GoodsBean>> listNetResult) {
                         listNetResult.showError(getContext());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();

                    }
                });


    }


    @Override
    public boolean isRegisterEventBus() {
        return true;
    }

    @Subscribe
    public void updatePrice(UpdateGoodsPriceEvent event){
        double total= 0;
        for (AttrSelectView attrSelectView :attrSelectViews){
            GoodsBean.ProductattrlistBean.ListBean listBean =  attrSelectView.getSelected();
            if (listBean !=null) {
                total += listBean.AttrPrice;
            }
        }
        String totalStr =  DoubleFromat.fromat(total,2, RoundingMode.FLOOR);
        tvTotal.setText("￥"+totalStr);
    }
}
