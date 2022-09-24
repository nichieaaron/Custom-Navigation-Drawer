package com.shrikanthravi.customnavigationdrawer;

import static com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply.StockItem.FEED;
import static com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply.StockItem.MSG;
import static com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply.StockItem.MUSIC;
import static com.shrikanthravi.customnavigationdrawer.swipeviewpager.DataSupply.StockItem.NEWS;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActive extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    Class fClass;
    public static Fragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_main);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("News",R.drawable.news_bg));
        menuItems.add(new MenuItem("Feed",R.drawable.feed_bg));
        menuItems.add(new MenuItem("Messages",R.drawable.message_bg));
        menuItems.add(new MenuItem("Music",R.drawable.music_bg));
        sNavigationDrawer.setMenuItemList(menuItems);

        fClass =  ItemFragment.class;
        ItemFragment.configureType(NEWS);

        try {

            frag = (Fragment) fClass.newInstance();

        } catch (Exception e) {

            e.printStackTrace();

        }
        if (frag != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, frag).commit();
        }

        sNavigationDrawer.setOnMenuItemClickListener(position -> {

            switch (position){
                case 0:
                    ItemFragment.configureType(NEWS);
                break;
                case 1:
                    ItemFragment.configureType(FEED);
                break;
                case 2:
                    ItemFragment.configureType(MSG);
                break;
                case 3:
                    ItemFragment.configureType(MUSIC);
                break;
            }

            fClass = ItemFragment.class;

            sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                @Override
                public void onDrawerOpened() {
                    //System.out.println("[nicaar] Drawer opened pos=" + position);
                }

                @Override
                public void onDrawerOpening(){
                    //System.out.println("[nicaar] Drawer opening pos=" + position);
                }

                @Override
                public void onDrawerClosing(){
                    //System.out.println("[nicaar] Drawer closed");

                    try {
                        frag = (Fragment) fClass.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (frag != null) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, frag).commit();
                    }
                }

                @Override
                public void onDrawerClosed() {

                }

                @Override
                public void onDrawerStateChanged(int newState) {
                    System.out.println("State "+newState);
                }
            });
        });

    }

}
