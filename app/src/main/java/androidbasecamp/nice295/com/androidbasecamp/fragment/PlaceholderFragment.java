package androidbasecamp.nice295.com.androidbasecamp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidbasecamp.nice295.com.androidbasecamp.Events.UpdateActionBar;
import androidbasecamp.nice295.com.androidbasecamp.R;
import de.greenrobot.event.EventBus;

/**
 * Created by kyuholee on 15. 1. 31..
 */
/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        EventBus.getDefault().post(new UpdateActionBar("Fragment 1"));

        return rootView;
    }

}
