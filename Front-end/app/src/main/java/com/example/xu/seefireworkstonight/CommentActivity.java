package com.example.xu.seefireworkstonight;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.xu.seefireworkstonight.Model.Comment;
import com.example.xu.seefireworkstonight.Model.GlobalVariable;
import com.example.xu.seefireworkstonight.Model.Site;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class CommentActivity extends AppCompatActivity {
    private Site site;
    private String com;
    private int note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Bundle bundle = this.getIntent().getExtras();
        site = (Site)(bundle.getSerializable("site"));

        final RatingBar note_comment = (RatingBar)findViewById(R.id.rb_comment);
        final EditText et_comment = (EditText)findViewById(R.id.et_comment);
        final Button submit_comment = (Button)findViewById(R.id.sub_comment);

        submit_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_comment.getText() != null){
                    note = (int)note_comment.getRating();
                    com = et_comment.getText().toString();

                    new MyHttpRequestTask().execute();

                }else{
                }
            }
        });

    }

    private class MyHttpRequestTask extends AsyncTask<Void, Void, Comment> {

        @Override
        protected Comment doInBackground(Void... params) {
            try {
                Comment comment = new Comment();
                String nameOfSite = site.getSiteName();
                comment.setNote(note);comment.setComment(com);comment.setSiteName(nameOfSite.substring(0, nameOfSite.length()-4));comment.setUserName(GlobalVariable.username);

                final String url = "http://10.0.2.2:8080/RestWebservice_SFT/comment/create";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Comment result = restTemplate.postForObject( url, comment, Comment.class);

                return result;

            } catch (Exception e) {
                Log.e("CommentActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Comment com) {
            Toast.makeText(CommentActivity.this, "Comment succeed !", Toast.LENGTH_LONG).show();

            CommentActivity.this.finish();
            //Intent intent =new Intent(CommentActivity.this, SiteActivity.class);
            //startActivity(intent);
        }

    }

}
