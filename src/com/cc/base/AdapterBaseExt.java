package com.cc.base;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 
 * <b>@Description:</b>基本带泛型的适配器<br/>
 * <b>@Author:</b>ccy<br/>
 * <b>@Since:</b>2014-7-26-下午2:36:49<br/>
 */
public abstract class AdapterBaseExt<T> extends BaseAdapter {
	/**
	 * 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
	 * 2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
	 * 3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
	 */
	protected List<T> mList;
	protected LayoutInflater mLayoutInflater;
	protected Context mContext;
	public AdapterBaseExt(Context mContext, List<T> mList) {
		this.mContext = mContext;
		this.mList = mList;
		this.mLayoutInflater = LayoutInflater.from(mContext);
	}

	public List<T> getList() {
		return mList;
	}
	

	/**
	 * 
	 * <b>@Description:<b>把一个集合加入到底部<br/>
	 * <b>@param list<b>void<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-7-26-下午2:40:01<br/>
	 */
	public void appendToList(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(list);
		notifyDataSetChanged();
	}

	/**
	 * 
	 * <b>@Description:<b>把一个集合加入到顶部<br/>
	 * <b>@param list<b>void<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-7-26-下午2:39:24<br/>
	 */
	public void appendToTopList(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(0, list);
		notifyDataSetChanged();
	}

	/**
	 * 
	 * <b>@Description:<b>清除集合<br/>
	 * <b><b>void<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-7-26-下午2:40:27<br/>
	 */
	public void clear() {
		mList.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		if (position > mList.size() - 1) {
			return null;
		}
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position == getCount() - 1) {// 当position达到集合索引时
			onReachBottom();// 延伸集合的方法
		}
		return getExView(position, convertView, parent);
	}
    /**
     * 
     * <b>@Description:<b>获取视图<br/>
     * <b>@param position
     * <b>@param convertView
     * <b>@param parent
     * <b>@return<b>View<br/>
     * <b>@Author:<b>ccy<br/>
     * <b>@Since:<b>2014-7-26-下午2:57:18<br/>
     */
	protected abstract View getExView(int position, View convertView,ViewGroup parent);

	protected abstract void onReachBottom();
}
