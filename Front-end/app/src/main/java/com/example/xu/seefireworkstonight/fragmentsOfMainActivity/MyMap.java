package com.example.xu.seefireworkstonight.fragmentsOfMainActivity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xu.seefireworkstonight.MyFragmentPagerAdapter;
import com.example.xu.seefireworkstonight.R;
import com.example.xu.seefireworkstonight.fragmentsOfMainActivity.fargmentsOfMyMap.SitesList;
import com.example.xu.seefireworkstonight.fragmentsOfMainActivity.fargmentsOfMyMap.SitesMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 5/25/16.
 */
public class MyMap extends Fragment {
    private ViewPager mPageVp;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentPagerAdapter mFragmentAdapter;
    private TextView mTabSitesMapTv, mTabSitesListTv;
    private ImageView mTabLineIv;
    private SitesMap fragment_sitesMap;
    private SitesList fragment_sitesList;
    private int currentIndex;
    private int screenWidth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_mymap, container, false);

        findById(view);
        initViewPager();
        initTabLineWidth();

        return view;
    }

    private void findById(View view) {
        mTabLineIv = (ImageView) view.findViewById(R.id.id_tab_line_iv);
        mPageVp = (ViewPager) view.findViewById(R.id.id_page_vp);

        View view_mymap_top_tab = view.findViewById(R.id.id_mymap_top_tab);
        mTabSitesMapTv = (TextView) view_mymap_top_tab.findViewById(R.id.id_sitesmap_tv);
        mTabSitesListTv = (TextView) view_mymap_top_tab.findViewById(R.id.id_siteslist_tv);

    }

    private void initViewPager() {
        fragment_sitesMap = new SitesMap();
        fragment_sitesList = new SitesList();

        mFragmentList.add(fragment_sitesMap);
        mFragmentList.add(fragment_sitesList);

        mFragmentAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabSitesMapTv.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                        break;
                    case 1:
                        mTabSitesListTv.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                        break;
                }
                currentIndex = position;
            }
        });

    }

    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 2;
        mTabLineIv.setLayoutParams(lp);
    }

    private void resetTextView() {
        mTabSitesMapTv.setTextColor(Color.BLACK);
        mTabSitesListTv.setTextColor(Color.BLACK);
    }


}
