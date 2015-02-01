package androidbasecamp.nice295.com.androidbasecamp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import androidbasecamp.nice295.com.androidbasecamp.R;

public class AnimManager {
	private Context mContext;
	private static AnimManager animManager;
	
	public static AnimManager getAnimManager(){
		if(animManager == null){
			animManager = new AnimManager();
			//appConfig.mContext = context;
		}
		return animManager;
	}

    public void slideDown(View v, int duration, int offset) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(offset);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        v.startAnimation(translateAnimation);
    }

    public void slideUp(View v, int duration, int offset) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 1.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(offset);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        v.startAnimation(translateAnimation);
    }
}
