package com.ccy.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.TypedValue;

public class TUtils {

	
	/**
	 * @author ccy
	 * @Description 得到网络类型（0没有网络 1 WIFI 2 3G）
	 * @param mContext
	 * @return int 2013-3-21下午2:33:33
	 * @Version
	 */
	public static int getNetType(Context mContext) {
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo net = connectivityManager.getActiveNetworkInfo();
		if (net == null) {
			return 0;
		} else {
			if ("WIFI".equals(net.getTypeName())) {
				return 1;
			} else if ("mobile".equals(net.getTypeName())) {
				return 2;
			} else {
				return 3;
			}
		}
	}
	
	/**
	 * 
	 * <b>@Description:<b>比较版本编码<br/>
	 * <b>@return<b>Boolean<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-9-13-上午10:23:35<br/>
	 */
    public static Boolean differentVersionCode(int curVision,int oldVison){
    	return curVision!=oldVison;
    } 
    
    /**
	 * 
	 * <b>@Description:<b>获取版本号编码<br/>
	 * <b>@param context
	 * <b>@return<b>int<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-9-13-上午10:20:20<br/>
	 */
	public static int getAppVersionCode(Context context){
		try {
			if(null==context){
				return 0;
			}
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			return pi.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	 /**
     * 
     * <b>@Description:<b>dp2px<br/>
     * <b>@param context
     * <b>@param dp
     * <b>@return<b>int<br/>
     * <b>@Author:<b>ccy<br/>
     * <b>@Since:<b>2014-9-4-上午10:04:48<br/>
     */
    public static int dp2px(Context context,int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}
}
