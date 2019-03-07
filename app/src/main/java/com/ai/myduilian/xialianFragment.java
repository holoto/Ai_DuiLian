package com.ai.myduilian;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ai.myduilian.Api.staticConfig;
import com.ai.myduilian.dummy.xianlianlist;
import com.ai.myduilian.dummy.xianlianlist.DummyItem;
import com.ai.myduilian.eventclass.event_settitle;

import org.greenrobot.eventbus.EventBus;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class xialianFragment extends Fragment {
	
	// TODO: Customize parameter argument names
	private static final String ARG_COLUMN_COUNT = "column-count";
	// TODO: Customize parameters
	private int mColumnCount = 1;
	private OnListFragmentInteractionListener mListener;
	
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public xialianFragment() {
	}
	
	// TODO: Customize parameter initialization
	@SuppressWarnings("unused")
	public static xialianFragment newInstance() {
		return new xialianFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getArguments() != null) {
			mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_xialian_list, container, false);
		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			if (mColumnCount <= 1) {
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
			} else {
				recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
			}
			recyclerView.setAdapter(new MyxialianRecyclerViewAdapter(staticConfig.getXialianlist(),mListener));
		}
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.e("onResume", "onResume: " );
		EventBus.getDefault().post(new event_settitle("xialian"));

	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		
		if (context instanceof OnListFragmentInteractionListener) {
			mListener = (OnListFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnListFragmentInteractionListener");
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
		Log.e("onDetach", "onDetach: " );
	}

	
	@Override
	public void onDestroy() {
		super.onDestroy();
//		BaseApplication.getRefWatcher().watch(this);
	}
	
	
	
}
