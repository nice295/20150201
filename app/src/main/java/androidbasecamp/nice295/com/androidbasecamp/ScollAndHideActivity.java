package androidbasecamp.nice295.com.androidbasecamp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScollAndHideActivity extends BaseActivity implements View.OnTouchListener, AbsListView.OnScrollListener {

    private int mLastFirstVisibleItem;
    private boolean mIsScrollingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_and_hide);

        // bind views
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> mSampleData = new ArrayList<String>();
        for (int idx = 0; idx < 100; idx++) {
            String string = "Data " + idx;
            mSampleData.add(string);
        }
        SampleAdapter mAdapter = new SampleAdapter(this, R.layout.list_item, mSampleData);
        listView.setAdapter(mAdapter);
        listView.setOnScrollListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Populate views
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
        final ListView lw = (ListView) findViewById(R.id.listView);

        if (scrollState == 0)
            Log.i(getClass().getSimpleName(), "scrolling stopped...");

        if (view.getId() == lw.getId()) {
            final int currentFirstVisibleItem = lw.getFirstVisiblePosition();

            if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                mIsScrollingUp = false;
                Log.i(getClass().getSimpleName(), "scrolling down...");
                if (findViewById(R.id.tv_footer).getVisibility() == View.VISIBLE) {
                    TranslateAnimation translateAnimation = new TranslateAnimation(
                            TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                            TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                            TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                            TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
                    translateAnimation.setDuration(500);
                    translateAnimation.setFillAfter(true);
                    findViewById(R.id.tv_footer).startAnimation(translateAnimation);
                    findViewById(R.id.tv_footer).setVisibility(View.INVISIBLE);
                }

            } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                mIsScrollingUp = true;
                Log.i(getClass().getSimpleName(), "scrolling up...");
                if (findViewById(R.id.tv_footer).getVisibility() == View.INVISIBLE) {
                    TranslateAnimation translateAnimation = new TranslateAnimation(
                            TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                            TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                            TranslateAnimation.RELATIVE_TO_PARENT, 1.0f,
                            TranslateAnimation.RELATIVE_TO_PARENT, 0.0f);
                    translateAnimation.setDuration(500);
                    translateAnimation.setFillAfter(true);
                    findViewById(R.id.tv_footer).setVisibility(View.VISIBLE);
                    findViewById(R.id.tv_footer).startAnimation(translateAnimation);
                }
            }

            mLastFirstVisibleItem = currentFirstVisibleItem;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    private class SampleAdapter extends ArrayAdapter<String> {

        private ArrayList<String> items;

        public SampleAdapter(Context context, int textViewResourceId, ArrayList<String> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item, null);
            }
            String p = items.get(position);
            if (p != null) {
                TextView title = (TextView) v.findViewById(R.id.tv_title);
                if (title != null) {
                    title.setText(p);
                }
            }
            return v;
        }
    }
}
