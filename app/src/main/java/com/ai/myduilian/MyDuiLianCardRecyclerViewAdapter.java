/*******************************************************************************
 * Author: holoto
 * Date: 2/23/19 1:50 PM
 ******************************************************************************/

package com.ai.myduilian;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ai.myduilian.dummy.duiliancard.DummyItem;
import com.ai.myduilian.objectBoxModel.DuiLIanData;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyDuiLianCardRecyclerViewAdapter extends RecyclerView.Adapter<MyDuiLianCardRecyclerViewAdapter.ViewHolder>
{
	
	private final List<DuiLIanData> mValues;
	private final OnListFragmentInteractionListener mListener;
	
	
	public MyDuiLianCardRecyclerViewAdapter(List<DuiLIanData> items, OnListFragmentInteractionListener listener) {
		mValues = items;
		mListener = listener;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_duiliancard, parent, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.mItem = mValues.get(position);
		holder.mshanglianview.setText(mValues.get(position).getShanglian());
		holder.mxialianvie.setText(mValues.get(position).getXialian());
		holder.mhengpiview.setText(mValues.get(position).getHengpi());
		
		holder.mView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != mListener) {
					// Notify the active callbacks interface (the activity, if the
					// fragment is attached to one) that an item has been selected.
					mListener.onListFragmentInteraction(String.valueOf(holder.mItem.getId()));
				}
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return mValues.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		public final View mView;
		public final TextView mshanglianview;
		public final TextView mxialianvie;
		public final TextView mhengpiview;
		public DuiLIanData mItem;
		
		public ViewHolder(View view) {
			super(view);
			mView = view;
			mshanglianview = (TextView) view.findViewById(R.id.item_shanglian);
			mxialianvie = (TextView) view.findViewById(R.id.item_xialiang);
			mhengpiview = (TextView) view.findViewById(R.id.item_hengpi);
		}
		
		@Override
		public String toString() {
			return super.toString() + " '" + mshanglianview.getText() + "'";
		}
	}
}
