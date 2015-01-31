package androidbasecamp.nice295.com.androidbasecamp.Events;

/**
 * Created by kyuholee on 15. 1. 31..
 */
public class UpdateActionBar {
    private String mActionBarTitle;

    public UpdateActionBar (String title) {
        mActionBarTitle = title;
    }

    public String getActionBarTitle() {
        return mActionBarTitle;
    }
}
