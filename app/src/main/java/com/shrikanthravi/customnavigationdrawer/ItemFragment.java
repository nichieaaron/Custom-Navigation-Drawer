package com.shrikanthravi.customnavigationdrawer;


import static com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply.StockItem.FEED;
import static com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply.StockItem.NEWS;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shrikanthravi.customnavigationdrawer.swipeviewpager.Adapter;
import com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply;
import com.shrikanthravi.customnavigationdrawer.swipeviewpager.Model;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {

    private static DataSupply.StockItem fragTy;
    View vw;
    Adapter adapter;
    List<Model> models;
    ViewPager viewPager;
    Integer[] colors = new Integer[3];
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public ItemFragment() {
        // Required empty public constructor
    }

    /*
    public ItemFragment(DataSupply.StockItem item) {
        // Required empty public constructor
    }
    */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("[nicaar] Got something="+ fragTy);

        // Inflate the layout for this fragment
        vw = inflater.inflate(R.layout.item_fragment, container, false);

        models = new ArrayList<>();
        DataSupply ds = new DataSupply();
        models = ds.AddItemsInStock(fragTy);

        adapter = new Adapter(models, getContext());

        viewPager = vw.findViewById(R.id.viewPager);
        Button ct = vw.findViewById(R.id.btnCart);
        Button a2c = vw.findViewById(R.id.a2c);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(60, 0, 60, 0);

        switch (fragTy)
        {
            case NEWS:
            case MSG:
                colors[0] = getResources().getColor(R.color.feed_frag_color1);
                colors[1] = getResources().getColor(R.color.feed_frag_color2);
                colors[2] = getResources().getColor(R.color.feed_frag_color3);
            break;
            case FEED:
            case MUSIC:
                colors[0] = getResources().getColor(R.color.music_frag_color1);
                colors[1] = getResources().getColor(R.color.music_frag_color2);
                colors[2] = getResources().getColor(R.color.music_frag_color3);
            break;
        }

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

        Transactions itemTxn = new Transactions();
        ct.setOnClickListener(view -> {
            itemTxn.view_cart(getContext());
        });

        a2c.setOnClickListener(view -> {
            itemTxn.add_to_cart(getContext());
        });

        return vw;
    }

    public static void configureType(DataSupply.StockItem typ){
        fragTy = typ;
    }
}
