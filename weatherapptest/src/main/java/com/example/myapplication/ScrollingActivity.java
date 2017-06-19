package com.example.myapplication;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.db.City;
import com.example.myapplication.db.County;
import com.example.myapplication.db.Provice;
import com.example.myapplication.db.SQLDao;
import com.example.myapplication.g.HeWeather5Bean;
import com.example.myapplication.g.NowBean;
import com.example.myapplication.util.HttpUtil;
import com.example.myapplication.util.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScrollingActivity extends AppCompatActivity {
    private NestedScrollView nested_scrollview;
    private TextView tmp_tv;
    private TextView tmp;
    private TextView sc;
    private TextView data_time0;
    private TextView info_w0;
    private TextView max_w0;
    private TextView min_w0;
    private TextView data_time1;
    private TextView info_w1;
    private TextView max_w1;
    private TextView min_w1;
    private TextView data_time2;
    private TextView info_w2;
    private TextView max_w2;
    private TextView min_w2;
    private TextView tv;
    private static final String TAG ="ScrollingActivity" ;
    private HeWeather5Bean mHeWeather5Bean1;
    private HeWeather5Bean mHeWeather5Bean2;
    private List<Provice> mProvices;
    private List<City> mCities;
    private List<County> mCounties;
    private String chooseName="上海";
    private Provice selectProvice;
    private City selectCity;
    private County selectCounty;
    private Spinner mSpinner;
    private int LEVEL=1;
    private SQLDao mSQLDao;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setback();
        setContentView(R.layout.activity_scrolling);
        tv = (TextView)findViewById(R.id.tv_tv);
        newTv();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mSpinner = (Spinner) findViewById(R.id.spinner);

        mSQLDao=new SQLDao(this);
        if (LEVEL==1){
            queryProvices();
        }else if (LEVEL==2){
            queryCities(selectProvice.getProviceCode());
        }else if(LEVEL==3){
            queryCounty(selectProvice.getProviceCode(),selectCity.getCityCode());
        }
        mSpinner.setSelection(0, false);
        mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemSelected: 222222222222222222222222---"+LEVEL );
                switch (LEVEL){
                    case 1:
                        selectProvice=mProvices.get(position);
                        LEVEL=2;
                        chooseName=selectProvice.getProviceName();
                        queryCities(selectProvice.getProviceCode());
                        break;
                    case 2:
                        selectCity = mCities.get(position);
                        LEVEL=3;
                        chooseName=selectCity.getCityName();
                        Log.e(TAG, "onItemSelected: 2222222222------"+selectProvice.getProviceCode()+selectCity.getCityCode() );
                        queryCounty(selectProvice.getProviceCode(),selectCity.getCityCode());
                        break;
                    case 3:
                        selectCounty = mCounties.get(position);
                        chooseName=selectCounty.getCountyName();
                        Toast.makeText(ScrollingActivity.this,"选择地方:"+selectCounty.getCountyName(),Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        imageView=(ImageView)findViewById(R.id.iv);

        getNowWeather();
        getForecastWeather();
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(chooseName);
        Glide.with(this).load(R.drawable.tianqi).into(imageView);
        getBing();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void setback(){
        if(Build.VERSION.SDK_INT>=21){
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
    private void getBing(){
        HttpUtil.getBing(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String url=response.body().string();
                        Log.e(TAG, "onResponse: bbbbbbbbbb"+url);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                    Glide.with(ScrollingActivity.this).load(url).into(imageView);
                    }
                });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getForecastWeather();
        getNowWeather();
    }

    private void newTv(){
        nested_scrollview = (NestedScrollView) findViewById(R.id.nested_scrollview);
        tmp_tv = (TextView) findViewById(R.id.tmp_tv);
        tmp = (TextView) findViewById(R.id.tmp);
        sc = (TextView) findViewById(R.id.sc);
        data_time1 = (TextView) findViewById(R.id.data_time1);
        info_w1 = (TextView) findViewById(R.id.w_info1);
        max_w1 = (TextView) findViewById(R.id.max_w1);
        min_w1 = (TextView) findViewById(R.id.min_w1);
        data_time0 = (TextView) findViewById(R.id.data_time0);
        info_w0 = (TextView) findViewById(R.id.w_info0);
        max_w0 = (TextView) findViewById(R.id.max_w0);
        min_w0 = (TextView) findViewById(R.id.min_w0);
        data_time2 = (TextView) findViewById(R.id.data_time2);
        info_w2 = (TextView) findViewById(R.id.w_info2);
        max_w2 = (TextView) findViewById(R.id.max_w2);
        min_w2 = (TextView) findViewById(R.id.min_w2);
    }
    private void getNowWeather(){
        HttpUtil.requestNowWeather(chooseName, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ScrollingActivity.this,"获取天气信息失败,请检查网络.",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String weatherText=response.body().string();
                Log.e(TAG, "onResponse: ------------------------------"+weatherText );
                try {
                    JSONObject jsonObject=new JSONObject(weatherText);
                    JSONArray jsonArray=jsonObject.getJSONArray("HeWeather5");
                    mHeWeather5Bean1=new Gson().fromJson(jsonArray.getJSONObject(0).toString(),HeWeather5Bean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "run: 11111111---"+mHeWeather5Bean1.getNow());
                            Log.e(TAG, "run: 222222----"+mHeWeather5Bean1.getNow().getCond());
                            Log.e(TAG, "run: 33333333--"+mHeWeather5Bean1.getNow().getCond().getTxt());
                            tmp_tv.setText(mHeWeather5Bean1.getNow().getTmp()+"℃");
                            tmp.setText(mHeWeather5Bean1.getNow().getCond().getTxt().toString());
                            sc.setText(""+mHeWeather5Bean1.getNow().getWind().getSc());

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void getForecastWeather(){
        HttpUtil.requestForecastWeather(chooseName, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ScrollingActivity.this,"获取天气信息失败,请检查网络.",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String weatherText=response.body().string();
                Log.e(TAG, "onResponse: ================="+weatherText );
                try {
                    JSONObject jsonObject=new JSONObject(weatherText);
                    JSONArray jsonArray=jsonObject.getJSONArray("HeWeather5");
                    mHeWeather5Bean2=new Gson().fromJson(jsonArray.getJSONObject(0).toString(),HeWeather5Bean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "run:forecast_:_:_:_:-;-; "+mHeWeather5Bean2.getDaily_forecast().toString());
                            data_time0.setText(mHeWeather5Bean2.getDaily_forecast().get(0).getDate());
                            info_w0.setText("日间:"+mHeWeather5Bean2.getDaily_forecast().get(0).getCond().getTxt_d()+"\n"
                                    +"夜间:"+mHeWeather5Bean2.getDaily_forecast().get(0).getCond().getTxt_n());
                            max_w0.setText(mHeWeather5Bean2.getDaily_forecast().get(0).getTmp().getMax()+"℃");
                            min_w0.setText(mHeWeather5Bean2.getDaily_forecast().get(0).getTmp().getMin()+"℃");
                            data_time1.setText(mHeWeather5Bean2.getDaily_forecast().get(1).getDate());
                            info_w1.setText("日间:"+mHeWeather5Bean2.getDaily_forecast().get(1).getCond().getTxt_d()+"\n"
                                    +"夜间:"+mHeWeather5Bean2.getDaily_forecast().get(1).getCond().getTxt_n());
                            max_w1.setText(mHeWeather5Bean2.getDaily_forecast().get(1).getTmp().getMax()+"℃");
                            min_w1.setText(mHeWeather5Bean2.getDaily_forecast().get(1).getTmp().getMin()+"℃");

                            data_time2.setText(mHeWeather5Bean2.getDaily_forecast().get(2).getDate());
                            info_w2.setText("日间:"+mHeWeather5Bean2.getDaily_forecast().get(2).getCond().getTxt_d()+"\n"
                                    +"夜间:"+mHeWeather5Bean2.getDaily_forecast().get(2).getCond().getTxt_n());
                            max_w2.setText(mHeWeather5Bean2.getDaily_forecast().get(2).getTmp().getMax()+"℃");
                            min_w2.setText(mHeWeather5Bean2.getDaily_forecast().get(2).getTmp().getMin()+"℃");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void setSpinner(String type){
        Log.e(TAG, "setSpinner: 333333333333"+type );
        List<String> list = new ArrayList<>();
        switch (type){
            case "provice":
                for(Provice p:mProvices){
                    list.add(p.getProviceName());
                }
                break;
            case "city":
                for (City city:mCities){
                    list.add(city.getCityName());
                }
                break;
            case "county":
                for(County c:mCounties){
                    list.add(c.getCountyName());
                }
                break;
        }
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpinner.setAdapter(adapter);
            }
        });

    }

    private void queryProvices(){
        Log.e(TAG, "queryProvices: 44444444444444444444" );
        mProvices =mSQLDao.queryData("provice", 0);
        if (mProvices.size()<=0){
            String address="http://guolin.tech/api/china";
            Log.e(TAG, "queryProvices: 444444444444444---url" );
            queryFromUrl(address,"provice",0);
        }
    }
    private void queryCities(int proviceId){
        Log.e(TAG, "queryCities: 555555555555555555555555" );
        mCities=mSQLDao.queryData("city",proviceId);
        if (mCities.size()<=0){
            String address="http://guolin.tech/api/china/"+proviceId;
            queryFromUrl(address,"city",proviceId);
        }
    }
    private void queryCounty(int proviceId,int cityId){
        Log.e(TAG, "queryCounty: 6666666666666666666666");
        mCounties = mSQLDao.queryData("county", cityId);
        if(mCounties.size()<=0){
            String address="http://guolin.tech/api/china/"+proviceId+"/"+cityId;
            queryFromUrl(address,"county",cityId);
        }
    }
    private void queryFromUrl(final String address, final String type, final int lid){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText=response.body().string();
                Log.e(TAG, "onResponse: 7777777777777777"+type );
                if ("provice".equals(type)){
                    addData(responseText, "provice", lid);
                    mProvices=mSQLDao.queryData("provice",lid);
                    setSpinner("provice");
                }else if("city".equals(type)){
                    addData(responseText,"city",lid);
                    mCities = mSQLDao.queryData("city", lid);
                    Log.e(TAG, "onResponse: mcities----------"+mCities);
                    setSpinner("city");
                }else if ("county".equals(type)){
                    addData(responseText,"county",lid);
                    mCounties = mSQLDao.queryData("county", lid);
                    setSpinner("county");
                }
            }
        });
    }
    private void addData(String response,String area,int lid){
        Log.e(TAG, "addData: 8888888888888888888888888"+area );
        ContentValues values;
        switch (area){
            case "provice":
                List<Provice> proviceList= Utility.handProviceData(response);
                Log.e(TAG, "addData: 1-1-1-1-1-1-1-"+proviceList );
                values = new ContentValues();
                for(Provice provice : proviceList){

                    values.put("proviceCode",provice.getProviceCode());
                    values.put("proviceName",provice.getProviceName());
                    mSQLDao.addData("provice",values);
                    values.clear();
                }
                break;
            case "city":
                List<City> cityList = Utility.handCityData(response, lid);
                Log.e(TAG, "addData: 2-2-2-2-2-2-2-2-"+cityList.get(0).getCityName()+cityList.get(0).getCityCode() );
                values = new ContentValues();
                for(City city:cityList){
                    values = new ContentValues();
                    values.put("cityCode", city.getCityCode());
                    values.put("cityName",city.getCityName());
                    values.put("proviceId",city.getProviceId());
                    mSQLDao.addData("city",values);
                    values.clear();
                }
                break;
            case "county":
                List<County> countyList=Utility.handCountyData(response,lid);
                Log.e(TAG, "addData: 3-3-3-3-3-3-3-3-"+countyList );
                values = new ContentValues();
                for(County county:countyList){
                    values = new ContentValues();
                    values.put("countyName",county.getCountyName());
                    values.put("cityId", county.getCityId());
                    values.put("weatherId",county.getWeatherId());
                    mSQLDao.addData("county", values);
                    values.clear();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSQLDao.closeDb();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
