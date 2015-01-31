package androidbasecamp.nice295.com.androidbasecamp.Fragment;

import android.app.Fragment;
import android.os.Bundle;

import de.greenrobot.event.EventBus;

/**
 * Created by kyuholee on 15. 1. 31..
 */
public class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().register(this);
    }

    public void onEvent(Object event) {
    }
}

