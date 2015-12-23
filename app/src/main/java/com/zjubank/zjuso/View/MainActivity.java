package com.zjubank.zjuso.View;

import java.lang.String;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.GestureDetector.OnGestureListener;

import com.dexafree.materialList.cards.SmallImageCard;
import com.dexafree.materialList.controller.RecyclerItemClickListener;
import com.dexafree.materialList.model.CardItemView;
import com.dexafree.materialList.view.MaterialListView;
import com.zjubank.zjuso.R;
import com.zjubank.zjuso.SQLTable.Websites.all;
import com.zjubank.zjuso.SQLTable.Websites.ckc;
import com.zjubank.zjuso.SQLTable.Websites.cspo;
import com.zjubank.zjuso.SQLTable.Websites.ugrs;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends Activity implements OnGestureListener {

    private GestureDetector detector;

    public String CutTitle( String title )
    {
        if(title.length()>20)
            title=title.substring(0, 20) + "...";
        return title;
    }

    public String CutDes( String des )
    {
        if( des.length()>50)
            des=des.substring(0, 50) + "...";
        return des;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getActionBar();
        ab.setTitle("我的头条");
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "924fa21d3c3d429b122310a6129b4b4c");
        setContentView(R.layout.activity_main);

//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle("我的头条");
//        setSupportActionBar(mToolbar);

        final MaterialListView mListView = (MaterialListView) findViewById(R.id.material_listview);
        BmobQuery<all> overview = new BmobQuery<>();
        overview.setLimit(20);
        overview.order("-date");
        overview.findObjects(MainActivity.this, new FindListener<all>() {
            @Override
            public void onSuccess(List<all> list) {
                for( all l : list )
                {
                    SmallImageCard card = new SmallImageCard(getApplicationContext());
                    card.setTitle( CutTitle(l.getTitle()) );
                    card.setDescription(l.getDate().getDate());
                    card.setTag(l.getLink());
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

    public View addButtonByText(String text){
        Button btn = new Button(this);
        btn.setText(text);
        return btn;
    }
    public View addTextByText(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setGravity(1);
        return tv;
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Fling", "Activity onTouchEvent!");
        return this.detector.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
//        Intent intent = new Intent();
//        intent.setClass(MainActivity.this, ckcActivity.class);
//        MainActivity.this.startActivity(intent);
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
            intent.setClass(MainActivity.this, ckcActivity.class);
            MainActivity.this.startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,
                    R.anim.push_left_out);
            return true;
        } else if (e1.getX() - e2.getX() < -120) {
            Toast.makeText(getApplicationContext(), "已经到最左边啦（≧∇≦）",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
