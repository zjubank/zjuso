package com.zjubank.zjuso.Card;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dexafree.materialList.model.CardItemView;
import com.zjubank.zjuso.R;

/**
 * Created by ZJUBank on 15/12/22.
 */
public class CustomCardItemView extends CardItemView<CustomCard> {

    TextView mTitle;
    TextView mDescription;

    public CustomCardItemView(Context context) {
        super(context);
    }

    public CustomCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void build(CustomCard card) {
        setTitle(card.getTitle());
        setDescription(card.getDescription());
    }

    public void setTitle(String title){
        mTitle = (TextView)findViewById(R.id.titleTextView);
        mTitle.setText(title);
    }

    public void setDescription(String description){
        mDescription = (TextView)findViewById(R.id.descriptionTextView);
        mDescription.setText(description);
    }
}

