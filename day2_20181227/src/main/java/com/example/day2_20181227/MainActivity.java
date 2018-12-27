package com.example.day2_20181227;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity {

    /**
     * 商品列表
     */
    private TextView mTv;
    private XListView mXlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mXlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
    }
    private String url="http://120.27.23.105/product/getProducts?pscid=39&page=1";
    private void initData(){

    }
    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mXlv = (XListView) findViewById(R.id.xlv);
    }
}
