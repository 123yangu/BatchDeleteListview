package com.ccy.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cc.base.AdapterBaseExt;
import com.ccy.entity.Dog;
import com.cy.R;

public class MyAdapter extends AdapterBaseExt<Dog> {
	
	

	public MyAdapter(Context mContext, List<Dog> mList) {
		super(mContext, mList);
		this.mList = mList;
	}

	private List<Dog> mDogs;
	private boolean mIsShowDeleteCheckBox;
//
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	@Override
	public int getItemViewType(int position) {
//		if(appointmentBean !=null && appointmentBean.getPerson() != 0){
//			return 1;
//		}else{
			return 0;
//		}
	}

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		
//	}

	public class ViewHolder {
		public TextView tv;
		public CheckBox cb;
	}

	public void showDeleteCheckBox() {
		mIsShowDeleteCheckBox = true;
		notifyDataSetChanged();
	}

	public void hideDeleteCheckBox() {
		mIsShowDeleteCheckBox = false;
	}

	public boolean getDeleteCheckBoxState() {
		return mIsShowDeleteCheckBox;
	}

	@Override
	protected View getExView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
//			convertView = convertView.inflate(mLayoutInflater, R.layout.item, null);
			convertView = mLayoutInflater.inflate(R.layout.item, null);
			viewHolder = new ViewHolder();
			viewHolder.tv = (TextView) convertView.findViewById(R.id.item_tv);
			viewHolder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (mIsShowDeleteCheckBox) {
			viewHolder.cb.setVisibility(View.VISIBLE);
			viewHolder.cb.setChecked(mList.get(position).isChecked());
		} else {
			viewHolder.cb.setVisibility(View.GONE);
		}
		viewHolder.tv.setText(mList.get(position).getName());
		return convertView;
	}

	@Override
	protected void onReachBottom() {
			
	}

}
