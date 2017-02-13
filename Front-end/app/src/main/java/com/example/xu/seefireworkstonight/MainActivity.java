package com.example.xu.seefireworkstonight;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.xu.seefireworkstonight.Model.Site;
import com.example.xu.seefireworkstonight.fragmentsOfMainActivity.MyInfo;
import com.example.xu.seefireworkstonight.fragmentsOfMainActivity.MyMap;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class MainActivity extends AppCompatActivity {
    private Site[] siteArray;

    public Site[] getSiteArray(){
        return this.siteArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&   Running ..............");
        new MyHttpRequestTask().execute();
    }

    private class MyHttpRequestTask extends AsyncTask<Void, Void, Site[]> {

        @Override
        protected Site[] doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/RestWebservice_SFT/site/list";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Site[] sites = restTemplate.getForObject(url, Site[].class);

                return sites;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Site[] sites) {
            siteArray = new Site[sites.length];
            siteArray = sites;
            initView();
        }

    }

    private void initView() {
        MyMap map = new MyMap();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, map).commit();

        RadioGroup myTabRg = (RadioGroup) findViewById(R.id.tab_menu);

        myTabRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.map:
                        MyMap map = new MyMap();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, map)
                                .commit();
                        break;

                    case R.id.me:
                        MyInfo me = new MyInfo();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, me)
                                .commit();
                        break;

                    default:
                        break;
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null && bundle.containsKey("from login")){
            String str = bundle.getString("from login");

            if(str != null && str.equals("yes")){
                MyInfo me = new MyInfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, me)
                        .commit();

                RadioGroup myTabRg = (RadioGroup) findViewById(R.id.tab_menu);
                myTabRg.findViewById(R.id.me).setSelected(true);
            }
        }else if(bundle != null && bundle.containsKey("from favor")){
            String str = bundle.getString("from favor");

            if(str != null && str.equals("yes")){
                MyInfo me = new MyInfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, me)
                        .commit();

                RadioGroup myTabRg = (RadioGroup) findViewById(R.id.tab_menu);
                myTabRg.findViewById(R.id.me).setSelected(true);
             }


        }
    }

    // Add a my location change listener to update Site[] siteArray

}