package androidbasecamp.nice295.com.androidbasecamp.Utils;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * Ӧ�ó���Activity�����ࣺ����Activity�����Ӧ�ó����˳�
 */
public class AppManager {

	private static Stack<Activity> mActivityStack;
	private static AppManager mManagerInstance;

	private AppManager() {
	}

	/**
	 * ��ȡAppManager��һʵ��
	 */
	public static AppManager getAppManager() {
		if (mManagerInstance == null) {
			mManagerInstance = new AppManager();
		}
		return mManagerInstance;
	}

	/**
	 * ���Activity����ջ
	 */
	public void addActivity(Activity activity) {
		if (mActivityStack == null) {
			mActivityStack = new Stack<Activity>();
		}
		mActivityStack.add(activity);
	}

	/**
	 * ��ȡ��ǰActivity����ջ�����һ��ѹ��ģ�
	 */
	public Activity getCurrentActivity() {
		Activity activity = mActivityStack.lastElement();
		return activity;
	}

	/**
	 * ����ǰActivity����ջ�����һ��ѹ��ģ�
	 */
	public void finishCurrentActivity() {
		Activity activity = mActivityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * ����ָ����Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			mActivityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * ����ָ�������Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : mActivityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * ��������Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = mActivityStack.size(); i < size; i++) {
			if (null != mActivityStack.get(i)) {
				mActivityStack.get(i).finish();
			}
		}
		mActivityStack.clear();
	}

	/**
	 * �˳�Ӧ�ó���
	 */
	public void exitApplication(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}
}