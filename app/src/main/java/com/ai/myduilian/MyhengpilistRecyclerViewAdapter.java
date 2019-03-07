/*******************************************************************************
 * Author: holoto
 * Date: 2/21/19 3:57 PM
 ******************************************************************************/

package com.ai.myduilian;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class MyhengpilistRecyclerViewAdapter extends RecyclerView.Adapter<MyhengpilistRecyclerViewAdapter.ViewHolder> {
	
	private final List<String> mValues;
	private final OnListFragmentInteractionListener mListener;
	
	public MyhengpilistRecyclerViewAdapter(List<String> items, OnListFragmentInteractionListener listener) {
		mValues = items;
		mListener = listener;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_hengpilist, parent, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.mItem = mValues.get(position);
		holder.mContentView.setText(mValues.get(position));
		
		holder.mView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != mListener) {
					// Notify the active callbacks interface (the activity, if the
					// fragment is attached to one) that an item has been selected.
					mListener.onListFragmentInteraction(holder.mItem);
				}
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return mValues.size();
	}
	public void reflashdata(){
		notifyDataSetChanged();
	}
	public class ViewHolder extends RecyclerView.ViewHolder {
		public final View mView;
		public final TextView mContentView;
		public String mItem;
		
		public ViewHolder(View view) {
			super(view);
			mView = view;
			mContentView = (TextView) view.findViewById(R.id.content1);
		}
		
		@Override
		public String toString() {
			return super.toString() + " '" + mContentView.getText() + "'";
		}
	}
}
