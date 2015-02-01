package androidbasecamp.nice295.com.androidbasecamp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidbasecamp.nice295.com.androidbasecamp.Events.UpdateActionBar;
import androidbasecamp.nice295.com.androidbasecamp.Fragment.PlaceholderFragment;
import androidbasecamp.nice295.com.androidbasecamp.Utils.AppConfig;
import androidbasecamp.nice295.com.androidbasecamp.Utils.AppManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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
            AppManager.getAppManager().showAllActivity();
            Log.d(getClass().getSimpleName(), "User name: "+ AppConfig.getAppConfig(this).get(AppConfig.CONF_USER_NAME));
            AppConfig.getAppConfig(this).set(AppConfig.CONF_USER_NAME, "Horace");
            return true;
        }
        else if (id == R.id.action_scroll_and_hide) {
            startActivity(new Intent(this, ScollAndHideActivity.class));
            overridePendingTransition(R.anim.close_parent, R.anim.open_child);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEvent(UpdateActionBar event) {
        getActionBar().setTitle(event.getActionBarTitle());
    }
}
