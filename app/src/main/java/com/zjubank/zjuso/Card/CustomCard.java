package com.zjubank.zjuso.Card;

import android.content.Context;

import com.dexafree.materialList.cards.SimpleCard;
import com.zjubank.zjuso.R;

/**
 * Created by ZJUBank on 15/12/22.
 */

public class CustomCard extends SimpleCard
{
    public CustomCard(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.m_cardlayout;
    }
}
