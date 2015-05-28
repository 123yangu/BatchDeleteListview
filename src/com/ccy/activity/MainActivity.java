package com.ccy.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ccy.adapter.MyAdapter;
import com.ccy.entity.Dog;
import com.ccy.swipemenulistview.SwipeMenu;
import com.ccy.swipemenulistview.SwipeMenuCreator;
import com.ccy.swipemenulistview.SwipeMenuItem;
import com.ccy.swipemenulistview.SwipeMenuListView;
import com.ccy.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.cy.R;

public class MainActivity extends Activity implements OnClickListener, OnMenuItemClickListener {


	private List<Dog> mDogs;
	private SwipeMenuListView mListView;
	private MyAdapter mAdapter;
	private RelativeLayout mDeleteRl;
	private LinearLayout mEmpty_ll;;
	private TextView mSelectAll;
	private TextView mAddDog;
	private boolean mIsDeleteModel = false; // 当前listView是否处于删除模式 ,默认是正常显示模式.
	private boolean mIsSelectAll = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViews();
		initData();
		setListener();
	}

	private void findViews() {
		mEmpty_ll = (LinearLayout) findViewById(R.id.empty_ll);
		mAddDog = (TextView) findViewById(R.id.add_tv);
		mSelectAll = (TextView) findViewById(R.id.tv_select_all);
		mListView = (SwipeMenuListView) findViewById(R.id.list);
		mDeleteRl = (RelativeLayout) findViewById(R.id.main_delete_rl);

	}

	private void initData() {
		if (mAdapter == null) {
			getDogs() ;
			mAdapter = new MyAdapter(this, mDogs);
//			mListView.setAdapter(mAdapter);
			
			
			//添加侧滑菜单
			// step 1. create a MenuCreator
			SwipeMenuCreator creator = new SwipeMenuCreator() {

				@Override
				public void create(SwipeMenu menu) {
					// Create different menus depending on the view type
					switch (menu.getViewType()) {
					case 0:
						createMenuDelete(menu);
						break;
					case 1:
						createMenuEnableDelete(menu);
						break;
					}
				}

				private void createMenuDelete(SwipeMenu menu) {
					SwipeMenuItem deleteItem = new SwipeMenuItem(
							getApplicationContext());

					// create "delete" item
					deleteItem = new SwipeMenuItem(MainActivity.this);
					// set item background
					//					deleteItem.setBackground(colorDrawable);
					deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
							0x3F, 0x25)));
					// set item width
					deleteItem.setWidth(TUtils.dp2px(MainActivity.this,90));
					// set item title
					deleteItem.setTitle("删除");
					// set item title fontsize
					deleteItem.setTitleSize(18);
					// set item title font color Color.parseColor("#9dcdfa")
					deleteItem.setTitleColor(Color.WHITE);
					// add to menu
					menu.addMenuItem(deleteItem);//删除
				}

				private void createMenuEnableDelete(SwipeMenu menu) {
					SwipeMenuItem enableDeleteItem = new SwipeMenuItem(
							getApplicationContext());
					// create "delete" item
					enableDeleteItem = new SwipeMenuItem(MainActivity.this);
					//set item background
					//deleteItem.setBackground(colorDrawable);
					enableDeleteItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
							0xCE)));
					// set item width
					enableDeleteItem.setWidth(TUtils.dp2px(MainActivity.this,90));
					// set item title
					enableDeleteItem.setTitle("删除");
					// set item title fontsize
					enableDeleteItem.setTitleSize(18);
					// set item title font color Color.parseColor("#9dcdfa")
					enableDeleteItem.setTitleColor(Color.WHITE);
					menu.addMenuItem(enableDeleteItem);
				}
			};
			mListView.setMenuCreator(creator);//和list绑定
			mListView.setOnMenuItemClickListener(this);//和item绑定
			mListView.setAdapter(mAdapter);
			mListView.setEmptyView(mEmpty_ll);
		} else {
//			mAdapter.setDogs(mDogs);
			mAdapter.notifyDataSetChanged();
		}
		
		
	}

	private void setListener() {
		mAddDog.setOnClickListener(this);
		mSelectAll.setOnClickListener(this);
		mDeleteRl.setOnClickListener(this);
		mListView.setOnItemClickListener(new MyOnItemClickListener());
		mListView.setOnItemLongClickListener(new MyOnItemLongClickListener());
	}

	// 初始化数据
	private List<Dog> getDogs() {
		mDogs = new ArrayList<Dog>();
		for (int i = 0; i < 30; i++) {
			mDogs.add(new Dog("狗" + i, false));
		}
		return mDogs;
	}

	// 增加一些狗
	private void getMoreDogs() {
		for (int i = 0; i < 15; i++) {
			mDogs.add(new Dog("狗" + i, false));
		}
		initData();
	}

	// 展示编辑模式
	private void showDeleteModel(int position) {
		mSelectAll.setVisibility(View.VISIBLE);
		mDeleteRl.setVisibility(View.VISIBLE);
		Dog dog = (Dog) mDogs.get(position);
		dog.setChecked(true);
//		mAdapter.setDogs(mDogs);
		mAdapter.showDeleteCheckBox();
		mIsDeleteModel = true;
	}

	// 隐藏编辑模式，回到正常显示listView
	private void hideDeleteModel() {
		mSelectAll.setVisibility(View.GONE);
		mDeleteRl.setVisibility(View.GONE);
		Iterator<Dog> iterator = mDogs.iterator();
		while (iterator.hasNext()) {
			Dog dog = iterator.next();
			dog.setChecked(false);
		}
		mAdapter.hideDeleteCheckBox();
		initData();
		mIsDeleteModel = false;
	}

	// 添加删除item
	private void addDeleteDog(int position) {
		Dog dog = (Dog) mDogs.get(position);
		if (dog.isChecked()) {
			dog.setChecked(false);
		} else {
			dog.setChecked(true);
		}
//		mAdapter.setDogs(mDogs);
		mAdapter.showDeleteCheckBox();
		mIsDeleteModel = true;
	}

	// 全部选中
	private void addDeleteAllDogs() {
		Iterator<Dog> iterator = mDogs.iterator();
		while (iterator.hasNext()) {
			Dog dog = iterator.next();
			dog.setChecked(true);
		}
//		mAdapter.setDogs(mDogs);
		mAdapter.showDeleteCheckBox();
		mIsDeleteModel = true;
		mIsSelectAll = true;
	}

	// 清空全部
	private void clearCheckItem() {
		Iterator<Dog> iterator = mDogs.iterator();
		while (iterator.hasNext()) {
			Dog dog = iterator.next();
			dog.setChecked(false);
		}
//		mAdapter.setDogs(mDogs);
		mAdapter.showDeleteCheckBox();
		mIsDeleteModel = true;
		mIsSelectAll = false;
	}

	// 删除
	private void deleteCheckItem() {
		Iterator<Dog> iterator = mDogs.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			Dog dog = iterator.next();
			if (dog.isChecked()) {
				count++;
				iterator.remove();
			}
		}
		Toast.makeText(MainActivity.this, "删除了" + count + "只狗", Toast.LENGTH_SHORT).show();
	}

	private void changeSelectText() {
		if (mIsSelectAll) {
			mSelectAll.setText("清空全选");
		} else {
			mSelectAll.setText("全部选中");
		}
	}

	private class MyOnItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (mIsDeleteModel) {
				addDeleteDog(position);
			} else {
				Dog dog = (Dog) mDogs.get(position);
				Toast.makeText(MainActivity.this, "点击了" + dog.getName(), Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class MyOnItemLongClickListener implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			if (!mIsDeleteModel) {
				showDeleteModel(position);
			}
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (mAdapter == null) {
			return super.onKeyDown(keyCode, event);
		}
		if (keyCode == KeyEvent.KEYCODE_BACK && mIsDeleteModel) {
			hideDeleteModel();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.add_tv:
			getMoreDogs();
			break;
		case R.id.tv_select_all:
			if (mIsSelectAll) {
				clearCheckItem();
			} else {
				addDeleteAllDogs();
			}
			changeSelectText();
			break;
		case R.id.main_delete_rl:
			deleteCheckItem();
			hideDeleteModel();
			break;
		}
	}

	@Override
	public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
		Toast.makeText(getApplicationContext(), "postion==>"+position, 0);
		return false;
	}

}
