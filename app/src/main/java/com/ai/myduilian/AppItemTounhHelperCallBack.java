/*******************************************************************************
 * Author: holoto
 * Date: 3/8/19 11:32 AM
 ******************************************************************************/

package com.ai.myduilian;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.ai.myduilian.objectBoxModel.DuiLIanData;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.List;

public class AppItemTounhHelperCallBack extends ItemTouchHelper.Callback
{
	private MyDuiLianCardRecyclerViewAdapter mAdapter;
 private 	List<DuiLIanData> mData;
	public  AppItemTounhHelperCallBack(MyDuiLianCardRecyclerViewAdapter adapter, List<DuiLIanData> item)
	{
		mAdapter=adapter;
		mData=item;
	}
	@Override
	public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
		Log.e("getMovementFlags", "getMovementFlags: " );
		int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//		int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
		int swipeFlag = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
		return makeMovementFlags(dragFlag,swipeFlag);
	}
	
	@Override
	public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
		int from = viewHolder.getAdapterPosition();
		int to = viewHolder1.getAdapterPosition();
		Log.e("onMove", "onMove: "+from+"1"+to );
		Collections.swap(mData, from, to);
		mAdapter.notifyItemMoved(from, to);
		
		return true;
	}
	
	@Override
	public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
		int pos = viewHolder.getAdapterPosition();
		Log.e("", "pos: "+pos );
		Log.e("", "i "+i );
		Log.e("onSwiped", "onSwiped: "+mData.get(pos).getHengpi());
//		Log.e("onSwiped", "onSwiped: "+BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().get(pos+1).getHengpi() );
		Log.e("onSwiped", "onSwiped: "+BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll().size() );
		BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().remove(mData.get(pos));
		Log.e("onSwiped", "onSwiped: "+BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll().size() );
		Log.e("", "mData.size: "+mData.size() );
		mData.remove(pos);
		Log.e("", "mData.size: "+mData.size() );
		Log.e("onSwiped", "onSwiped: "+BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll().size() );
		mAdapter.notifyItemRemoved(pos);
		if (BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll().size()==0){
			EventBus.getDefault().post("card");
		}
//		mAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
		super.onSelectedChanged(viewHolder, actionState);
		Log.e("onSelectedChanged", "onSelectedChanged: " );
//		Log.e("", "onSelectedChanged: "+viewHolder.getAdapterPosition() );
		if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
			MyDuiLianCardRecyclerViewAdapter.ViewHolder holder = (MyDuiLianCardRecyclerViewAdapter.ViewHolder)viewHolder;
			holder.itemView.setBackgroundColor(0xffbcbcbc);
		}
	}
	
	@Override
	public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
		super.clearView(recyclerView, viewHolder);
		Log.e("clearView", "clearView: " );
		MyDuiLianCardRecyclerViewAdapter.ViewHolder holder = (MyDuiLianCardRecyclerViewAdapter.ViewHolder)viewHolder;
		holder.itemView.setBackgroundColor(0xffeeeeee);
	}
	
}
