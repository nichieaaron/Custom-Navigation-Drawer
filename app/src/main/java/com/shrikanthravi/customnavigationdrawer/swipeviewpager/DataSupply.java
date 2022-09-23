package com.shrikanthravi.customnavigationdrawer.swipeviewpager;

import static java.security.AccessController.getContext;

import com.shrikanthravi.customnavigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class DataSupply {
    private Adapter adapter;
    private List<Model> models;

    public enum StockItem {
        NEWS,
        FEED,
        MSG,
        MUSIC;
    }

    public DataSupply() {

    }

    public List<Model> AddItemsInStock(StockItem itm)
    {
        models = new ArrayList<>();

        String[] itemTitle = getNumOfItemType(itm);
        int[] itemImg = getItemImage(itm);
        String[] itemDesc = getItemDescInfo(itm);

        for(int t = 0; t < itemTitle.length; t++){
            models.add(new Model(itemImg[t], itemTitle[t], itemDesc[t]));
        }

        return models;
    }

    private String[] getNumOfItemType(StockItem itm)
    {
        switch (itm)
        {
            case NEWS:
                return new String[] {"VoA", "BBC", "CNN", "RT"};
            case FEED:
                return new String[] {"Odyssey", "Bitchute", "RokFin"};
            case MSG:
                return new String[] {"Telegram","Kakao", "Viber"};
            case MUSIC:
                return new String[] {"Reggae","R&B", "Country"};
        }
        return new String[] {};
    }

    private int[] getItemImage(StockItem itm){
        switch (itm)
        {
            case NEWS:
            case FEED:
            case MSG:
            case MUSIC:
                return new int[] {R.drawable.news_bg, R.drawable.feed_bg, R.drawable.message_bg, R.drawable.music_bg};
        }
        return new int[] {};
    }

    private String[] getItemDescInfo(StockItem itm){
        switch (itm)
        {
            case NEWS:
            case FEED:
                return new String[] {
                        /* Odyssey */
                        "The Odyssey is one of two major ancient Greek epic poems attributed to Homer. It is one of the oldest extant " +
                        "works of literature still widely read by modern audiences. As with the Iliad, the poem is" +
                        "divided into 24 books. It follows the Greek hero Odysseus, king of Ithaca, and his journey home after the Trojan War",

                        /* Bitchute */
                        "BitChute is an alt-tech video hosting service launched by Ray Vahey in January 2017." +
                        "It offers freedom of expression, it accommodates all individuals.",

                        /* Rokfin */
                        "Rokfin is a platform for creators and content owners to bundle their offerings in a subscription. " +
                        "The platform uses a practical application of blockchain technology to seamlessly compensate creators for the customers " +
                        "they bring, help retain, and the network effects they generate."
                };
            case MSG:
            case MUSIC:
                return new String[] {
                        /* Reggae */
                        "Reggae music is synonymous with equal rights and justice and has earned Jamaica international respect and reinforced the country's image." +
                        " It has also had a huge impact on international pop culture.",
                        /* R&B */
                        "Rhythm and blues, frequently abbreviated as R&B or R'n'B, is a genre of popular music that originated in African-American communities in the 1940s.",
                        /* Country */
                        "Country is a genre of popular music that originated with blues, church music such as Southern gospel and spirituals, " +
                        "old-time, and American folk music forms including Appalachian, Cajun, Creole, and the cowboy Western music styles of New Mexico, Red Dirt, Tejano, and Texas country."
                };
        }
        return new String[] {};
    }
}
