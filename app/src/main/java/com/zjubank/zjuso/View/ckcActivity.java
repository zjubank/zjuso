package com.zjubank.zjuso.View;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.dexafree.materialList.cards.SmallImageCard;
import com.dexafree.materialList.controller.RecyclerItemClickListener;
import com.dexafree.materialList.model.CardItemView;
import com.dexafree.materialList.view.MaterialListView;
import com.zjubank.zjuso.R;
import com.zjubank.zjuso.SQLTable.Websites.ckc;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class ckcActivity extends Activity implements GestureDetector.OnGestureListener {

    private GestureDetector detector;

    public String CutTitle( String title )
    {
        if(title.length()>20)
            title=title.substring(0, 20) + "...";
        return title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getActionBar();
        ab.setTitle("竺院办公网");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

//        android.support.v7.widget.Toolbar mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle("竺院办公网");
//        setSupportActionBar(mToolbar);
//        TextView tx = (TextView) findViewById(R.id.top_news);
//        tx.setText("竺可桢学院办公网");


        final MaterialListView mListView = (MaterialListView) findViewById(R.id.material_listview);
        BmobQuery<ckc> overview_ckc = new BmobQuery<>();
        overview_ckc.setLimit(20);
        overview_ckc.order("-date");
        overview_ckc.findObjects(this, new FindListener<ckc>() {
            @Override
            public void onSuccess(List<ckc> list) {
                for( ckc l : list )
                {
                    SmallImageCard card = new SmallImageCard(getApplicationContext());
                    card.setTitle(CutTitle(l.getTitle()));
                    card.setDescription(l.getDate().getDate());
                    mListView.add(card);
                    mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {

                        @Override
                        public void onItemClick(CardItemView cardItemView, int i) {
                            String link = (String) cardItemView.getTag();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(link));
                            startActivity(intent);

                            return;
                        }

                        @Override
                        public void onItemLongClick(CardItemView cardItemView, int i) {

                            return;
                        }
                    });
                }
            }
            @Override
            public void onError(int i, String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });


        detector = new GestureDetector(this);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Fling", "Activity onTouchEvent!");
        return this.detector.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // TODO Auto-generated method stub
        Log.i("Fling", "Fling Happened!");
        if (e1.getX() - e2.getX() > 120) {
            Intent intent = new Intent();
            intent.setClass(ckcActivity.this, cspoActivity.class);
            ckcActivity.this.startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,
                    R.anim.push_left_out);
            return true;
        } else if (e1.getX() - e2.getX() < -120) {
            Intent intent = new Intent();
            intent.setClass(ckcActivity.this, MainActivity.class);
            ckcActivity.this.startActivity(intent);
            overridePendingTransition(R.anim.push_right_in,
                    R.anim.push_right_out);
            return true;
        }
        return false;
    }

}
