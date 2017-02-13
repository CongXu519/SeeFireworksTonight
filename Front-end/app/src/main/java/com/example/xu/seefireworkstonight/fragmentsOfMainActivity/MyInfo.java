package com.example.xu.seefireworkstonight.fragmentsOfMainActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xu.seefireworkstonight.LoginActivity;
import com.example.xu.seefireworkstonight.MainActivity;
import com.example.xu.seefireworkstonight.Model.Favorite;
import com.example.xu.seefireworkstonight.Model.GlobalVariable;
import com.example.xu.seefireworkstonight.Model.Site;
import com.example.xu.seefireworkstonight.Model.User;
import com.example.xu.seefireworkstonight.R;
import com.example.xu.seefireworkstonight.RegisterActivity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xu on 5/25/16.
 */
public class MyInfo extends Fragment{
    private Button login;
    private Button register;
    private Button logout;
    private LinearLayout mLinearListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        login  = (Button)view.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        register  = (Button)view.findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        logout = (Button)view.findViewById(R.id.logout);

        mLinearListView = (LinearLayout) view.findViewById(R.id.linear_listview);
        if(((LinearLayout) mLinearListView).getChildCount() > 0){
            ((LinearLayout) mLinearListView).removeAllViews();
        }

        if(GlobalVariable.username != null){
            login.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);

            new MyHttpRequestTask().execute();
        }
        else{
            login.setVisibility(View.VISIBLE);
            register.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        if(((LinearLayout) mLinearListView).getChildCount() > 0){
            ((LinearLayout) mLinearListView).removeAllViews();
        }

        if(GlobalVariable.username != null){
            login.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);

            new MyHttpRequestTask().execute();
        }
        else{
            login.setVisibility(View.VISIBLE);
            register.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
        }
    }

    private class MyHttpRequestTask extends AsyncTask<Void, Void, Favorite[]> {

        @Override
        protected Favorite[] doInBackground(Void... params) {
            Favorite[] results = null;
            try {

                final String url = "http://10.0.2.2:8080/RestWebservice_SFT/favorite/name/"+GlobalVariable.username;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                results = restTemplate.getForObject(url, Favorite[].class);

                System.out.println("###########################*******************************  "+ results);

            } catch (Exception e) {
                Log.e("MyInfo Fragment", e.getMessage(), e);
            }

            return results;
        }

        @Override
        protected void onPostExecute(Favorite[] favorites) {

            /***
             * adding item into listview
             */
            for (int i = 0; i < favorites.length; i++) {
                /**
                 * inflate items/ add items in linear layout instead of listview
                 */
                LayoutInflater inflater = null;
                View mLinearView = null;
                if(getActivity() != null){
                    inflater = (LayoutInflater) getActivity().getApplicationContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                }

                mLinearView = inflater.inflate(R.layout.row, null);

                /**
                 * getting id of row.xml
                 */
                TextView favoriteText = (TextView) mLinearView.findViewById(R.id.text);

                /**
                 * set item into row
                 */
                favoriteText.setText(favorites[i].getSiteName());
                favoriteText.setGravity(Gravity.CENTER);
                //favoriteText.setTextColor(Color.parseColor("#149cf0"));

                System.out.println("+++++++++=================================================================="+favoriteText.getText());

                /**
                 * add view in top linear
                 */

                mLinearListView.addView(mLinearView);

                /**
                 * get item row on click
                 *
                 */
                mLinearView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });
            }

        }

    }
}
