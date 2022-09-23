package com.shrikanthravi.customnavigationdrawer;


import static com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply.StockItem.FEED;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shrikanthravi.customnavigationdrawer.swipeviewpager.Adapter;
import com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply;
import com.shrikanthravi.customnavigationdrawer.swipeviewpager.Model;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    View vw;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vw = inflater.inflate(R.layout.fragment_feed, container, false);

        models = new ArrayList<>();
        DataSupply ds = new DataSupply();
        models = ds.AddItemsInStock(FEED);

        adapter = new Adapter(models, getContext());

        viewPager = vw.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(60, 0, 60, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.feed_frag_color),
                getResources().getColor(R.color.feed_frag_color1),
                getResources().getColor(R.color.feed_frag_color2),
                getResources().getColor(R.color.feed_frag_color3)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float posOffset, int posOffsetPx) {
                if (pos < (adapter.getCount() -1) && pos < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    posOffset,
                                    colors[pos],
                                    colors[pos + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return vw;
    }
}
