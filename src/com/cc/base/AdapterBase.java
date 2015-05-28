package com.cc.base;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class AdapterBase extends BaseAdapter {
	protected List mList ;
	protected Context mContext;
	protected LayoutInflater mLayoutInflater;
	
	public AdapterBase(Context context,List list){
		mContext = context;
		mList = list;
		mLayoutInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}
	
	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	public void setList(List pList) {
		this.mList = pList;
	}
	
}
