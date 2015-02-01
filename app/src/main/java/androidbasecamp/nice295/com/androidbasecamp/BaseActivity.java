package androidbasecamp.nice295.com.androidbasecamp;

import android.app.Activity;
import android.os.Bundle;

import androidbasecamp.nice295.com.androidbasecamp.Utils.AppManager;
import de.greenrobot.event.EventBus;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        // add Activity to Activity Stack
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        // finish this Activity
        AppManager.getAppManager().finishActivity(this);
    }

    public void onEvent(Object event) {
    }
}
