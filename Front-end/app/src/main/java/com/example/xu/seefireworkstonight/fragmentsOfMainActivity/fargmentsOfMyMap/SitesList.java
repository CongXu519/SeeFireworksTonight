package com.example.xu.seefireworkstonight.fragmentsOfMainActivity.fargmentsOfMyMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.xu.seefireworkstonight.MainActivity;
import com.example.xu.seefireworkstonight.Model.Site;
import com.example.xu.seefireworkstonight.R;
import com.example.xu.seefireworkstonight.SiteActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by xu on 5/25/16.
 */
public class SitesList extends Fragment {
    private ListView lv;
    private ArrayList<HashMap<String, Object>> listItem;
    private Site[] sites;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sites = ((MainActivity)getActivity()).getSiteArray();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_siteslist, container, false);
        lv = (ListView) view.findViewById(R.id.lv);

        MyAdapter mAdapter = new MyAdapter(getActivity());

        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent =new Intent(getActivity(), SiteActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("site", sites[arg2]);

                intent.putExtras(bundle);

                startActivity(intent);

                Log.v("MyListViewBase", "u have clicked " + arg2);
            }
        });

        return view;
    }

    private ArrayList<HashMap<String, Object>> getSites(){
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();

        for(int i=0;i<sites.length;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("SiteID", sites[i].getId());
            map.put("SiteName", sites[i].getSiteName());
            map.put("Latitude", sites[i].getLatitude());
            map.put("Longitude", sites[i].getLongitude());
            map.put("SiteImage", sites[i].getSiteImage());
            map.put("AverageNote", sites[i].getAverageNote());
            listItem.add(map);
        }
        return listItem;

    }

    /**
     * Bind view & data
     */
    private class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {

            return getSites().size();
        }

        @Override
        public Object getItem ( int position){
            return null;
        }

        @Override
        public long getItemId ( int position){
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            ViewHolder holder;

            Log.v("MyListViewBase", "getView " + position + " " + convertView);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item, null);
                holder = new ViewHolder();

                holder.siteImg = (ImageView) convertView.findViewById(R.id.iv_site);
                holder.siteName = (TextView) convertView.findViewById(R.id.tv_sitename);
                holder.sitenote = (RatingBar) convertView.findViewById(R.id.rb_layout_item);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            byte[] imageBytes = (byte[])(getSites().get(position).get("SiteImage"));
            holder.siteImg.setImageBitmap(BitmapFactory.decodeByteArray(
                    imageBytes, 0, imageBytes.length));
            String Name_Site = getSites().get(position).get("SiteName").toString();
            //holder.siteName.setText(Name_Site.substring(0, Name_Site.length()-4));
            holder.siteName.setText(Name_Site);
            holder.sitenote.setRating((int)(getSites().get(position).get("AverageNote")));

            return convertView;
        }

    }

    public final class ViewHolder {
        public ImageView siteImg;
        public TextView siteName;
        public RatingBar sitenote;
    }

}
