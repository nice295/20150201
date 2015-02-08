package androidbasecamp.nice295.com.androidbasecamp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import androidbasecamp.nice295.com.androidbasecamp.Utils.AnimManager;

public class StickyActivity extends BaseActivity implements AbsListView.OnScrollListener {

    private ImageView mHeader;
    private ListView mListView;
    private View mPlaceHolderView;

    private int mHeaderHeight;
    private int mMinHeaderTranslation;
    private int mActionBarHeight;

    private TypedValue mTypedValue = new TypedValue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickyheader);

        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mHeaderHeight + getActionBarHeight();

        // bind views
        mHeader = (ImageView) findViewById(R.id.iv_header);

        mListView = (ListView) findViewById(R.id.lv_sticky);
        ArrayList<String> mSampleData = new ArrayList<String>();
        for (int idx = 0; idx < 100; idx++) {
            String string = "Data " + idx;
            mSampleData.add(string);
        }
        SampleAdapter mAdapter = new SampleAdapter(this, R.layout.list_item, mSampleData);
        mPlaceHolderView = getLayoutInflater().inflate(R.layout.view_header_placeholder, mListView, false);
        mListView.addHeaderView(mPlaceHolderView);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(this);
    }

    public int getActionBarHeight() {
        if (mActionBarHeight != 0) {
            return mActionBarHeight;
        }
        getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
        mActionBarHeight = TypedValue.complexToDimensionPixelSize(mTypedValue.data, getResources().getDisplayMetrics());
        return mActionBarHeight;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Populate views
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AppManager.getAppManager().showAllActivity();
            return true;
        } else if (id == R.id.action_main) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.close_child, R.anim.open_parent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int scrollY = getScrollY();
        //sticky actionbar
        mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));
    }

    public int getScrollY() {
        View c = mListView.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            //headerHeight = mPlaceHolderView.getHeight();
            headerHeight = mPlaceHolderView.getHeight();
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
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
