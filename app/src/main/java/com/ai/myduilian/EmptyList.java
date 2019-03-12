/*******************************************************************************
 * Author: holoto
 * Date: 3/12/19 3:40 PM
 ******************************************************************************/

package com.ai.myduilian;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ai.myduilian.eventclass.event_settitle;

import org.greenrobot.eventbus.EventBus;


public class EmptyList extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	
	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private CardView cardView;
	private OnListFragmentInteractionListener mListener;
	
	public EmptyList() {
		// Required empty public constructor
	}

	public static EmptyList newInstance() {
		EmptyList fragment = new EmptyList();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		EventBus.getDefault().register(this);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view=inflater.inflate(R.layout.fragment_empty_list, container, false);
		initview(view);
		EventBus.getDefault().post(new event_settitle("create"));
		return view;
	}
	private void initview(View view){
		cardView=(CardView) view.findViewById(R.id.empty_create);
		cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post("shanglian");
				
			}
		});
	}
	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onListFragmentInteraction(uri.toString());
		}
	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnListFragmentInteractionListener) {
			mListener = (OnListFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}
	
	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	
}
