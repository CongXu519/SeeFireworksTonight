package com.example.xu.seefireworkstonight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xu.seefireworkstonight.Model.Comment;
import com.example.xu.seefireworkstonight.Model.Site;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

public class SiteActivity extends AppCompatActivity {
    private Button go;
    private int screenWidth;
    private Site site;
    private LinearLayout mLinearListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        Bundle bundle = this.getIntent().getExtras();
        site = (Site)(bundle.getSerializable("site"));

        ImageView image_site = (ImageView)findViewById(R.id.iv_site);
        RatingBar note_site = (RatingBar)findViewById(R.id.rb_activity_site);
        TextView address_site = (TextView)findViewById(R.id.tv_address);
        go = (Button)findViewById(R.id.button_go);

        byte[] imageBytes = site.getSiteImage();
        image_site.setImageBitmap(BitmapFactory.decodeByteArray(
                imageBytes, 0, imageBytes.length));
        note_site.setRating((float)site.getAverageNote());
        address_site.setText(site.getAddress());

        mLinearListView = (LinearLayout) findViewById(R.id.linear_listview);
        if(((LinearLayout) mLinearListView).getChildCount() > 0){
            ((LinearLayout) mLinearListView).removeAllViews();
        }

        //new MyHttpRequestTask().execute();

        Button fav = (Button)findViewById(R.id.id_favorite);
        Button com = (Button)findViewById(R.id.id_comment);
        Button sha = (Button)findViewById(R.id.id_share);

        this.initButtonWidth(fav);
        this.initButtonWidth(com);
        this.initButtonWidth(sha);

        go = (Button)findViewById(R.id.button_go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f&daddr=%f,%f",
                        50.60826532801599, 3.1441283226013184, site.getLatitude(), site.getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);


            }
        });

        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SiteActivity.this, CommentActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("site", site);

                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SiteActivity.this, "Favorite add succeed !", Toast.LENGTH_LONG).show();

                // Need to add
                /*Intent intent =new Intent(SiteActivity.this, MainActivity.class);

                Bundle bundle=new Bundle();
                bundle.putString("from favor", "yes");

                intent.putExtras(bundle);

                startActivity(intent);*/
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();

        if(((LinearLayout) mLinearListView).getChildCount() > 0){
            ((LinearLayout) mLinearListView).removeAllViews();
        }

        new MyHttpRequestTask().execute();

        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh   end   !!!!!");
    }

    private class MyHttpRequestTask extends AsyncTask<Void, Void, Comment[]> {

        @Override
        protected Comment[] doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/RestWebservice_SFT/comment/list";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Comment[] comments = restTemplate.getForObject(url, Comment[].class);
                System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH "+ comments);


                return comments;

            } catch (Exception e) {
                Log.e("SiteActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Comment[] comments) {
            /***
             * adding item into listview
             */
            for (int i = 0; i < comments.length; i++) {
                /**
                 * inflate items/ add items in linear layout instead of listview
                 */
                LayoutInflater inflater = null;
                inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View mLinearView = inflater.inflate(R.layout.row, null);
                /**
                 * getting id of row.xml
                 */
                TextView commentText = (TextView) mLinearView
                        .findViewById(R.id.text);

                /**
                 * set item into row
                 */
                commentText.setText(comments[i].getUserName()+" :  "+comments[i].getComment());
                commentText.setGravity(Gravity.CENTER);
                //commentText.setTextColor(Color.parseColor("#149cf0"));

                System.out.println("+++++++++=================================================================="+commentText.getText());

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

    private void initButtonWidth(View view){
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view
                .getLayoutParams();
        lp.width = screenWidth / 3;
        view.setLayoutParams(lp);
    }
}
