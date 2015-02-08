package androidbasecamp.nice295.com.androidbasecamp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidbasecamp.nice295.com.androidbasecamp.Events.UpdateActionBar;
import androidbasecamp.nice295.com.androidbasecamp.R;
import androidbasecamp.nice295.com.androidbasecamp.ScollAndHideActivity;
import androidbasecamp.nice295.com.androidbasecamp.StickyActivity;
import de.greenrobot.event.EventBus;

/**
 * Created by kyuholee on 15. 1. 31..
 */
/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        TextView tv_to_scroll_and_hide = (TextView) rootView.findViewById(R.id.tv_to_scroll_and_hide);
        tv_to_scroll_and_hide.setOnClickListener(this);
        TextView tvToStick = (TextView)rootView.findViewById(R.id.tv_to_sticky);
        tvToStick.setOnClickListener(this);

        EventBus.getDefault().post(new UpdateActionBar("Home"));

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_scroll_and_hide:
                startActivity(new Intent(getActivity(), ScollAndHideActivity.class));
                break;
            case R.id.tv_to_sticky:
                startActivity(new Intent(getActivity(), StickyActivity.class));
                break;
        }
    }
}
