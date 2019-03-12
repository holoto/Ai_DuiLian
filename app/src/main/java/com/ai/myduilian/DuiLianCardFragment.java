/*******************************************************************************
 * Author: holoto
 * Date: 2/23/19 1:50 PM
 ******************************************************************************/

package com.ai.myduilian;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ai.myduilian.eventclass.event_settitle;
import com.ai.myduilian.objectBoxModel.DuiLIanData;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import io.objectbox.Box;
import io.objectbox.query.Query;

public class DuiLianCardFragment extends Fragment {
	
	// TODO: Customize parameter argument names
	private static final String ARG_COLUMN_COUNT = "column-count";
	// TODO: Customize parameters
	private int mColumnCount = 1;
	private OnListFragmentInteractionListener mListener;
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public DuiLianCardFragment() {
	}
	
	// TODO: Customize parameter initialization
	@SuppressWarnings("unused")
	public static DuiLianCardFragment newInstance() {
		return new DuiLianCardFragment();
	}
	public static String getRandomJianHan(int len)
	{
		String ret="";
		for(int i=0;i<len;i++){
			String str = null;
			int hightPos, lowPos; // 定义高低位
			Random random = new Random();
			hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
			lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
			byte[] b = new byte[2];
			b[0] = (new Integer(hightPos).byteValue());
			b[1] = (new Integer(lowPos).byteValue());
			try
			{
				str = new String(b, "GBk"); //转成中文
			}
			catch (UnsupportedEncodingException ex)
			{
				
				ex.printStackTrace();
			}
			ret+=str;
		}
		return ret;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		duiLIanDataBox.removeAll();
		int max=0;
		if (BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll().size()==0){
			 max=1;
		}
		for (int i = 0; i < max; i++) {
			DuiLIanData duiLIanData=new DuiLIanData();
			
			duiLIanData.setHengpi("横批"+getRandomJianHan(5));
			duiLIanData.setShanglian("上联"+getRandomJianHan(8));
			duiLIanData.setXialian("下联"+getRandomJianHan(8));
			BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().put(duiLIanData);
			
		}
		List<DuiLIanData> duiLIanDataList=BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll();
		Log.e("onCreate", "onCreate: "+duiLIanDataList.size() );
		Log.e("onCreate", "onCreate: "+duiLIanDataList.get(0) );
//		Log.e("onCreate", "onCreate: "+duiLIanDataList.get(1111).getId() );
		if (getArguments() != null) {
			mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_duiliancard_list, container, false);
		EventBus.getDefault().post(new event_settitle("list"));
		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			List<DuiLIanData> dataList=BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll();
			MyDuiLianCardRecyclerViewAdapter myDuiLianCardRecyclerViewAdapter=new MyDuiLianCardRecyclerViewAdapter(dataList, mListener);
//			MyDuiLianCardRecyclerViewAdapter myDuiLianCardRecyclerViewAdapter=new MyDuiLianCardRecyclerViewAdapter(BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll(), mListener);
			if (mColumnCount <= 1) {
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
			} else {
				recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
			}
			recyclerView.setItemAnimator(new DefaultItemAnimator());
//			recyclerView.setAdapter(new MyDuiLianCardRecyclerViewAdapter(duiLIanDataBox.getAll(), mListener));
			recyclerView.setAdapter(myDuiLianCardRecyclerViewAdapter);
			ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new AppItemTounhHelperCallBack(myDuiLianCardRecyclerViewAdapter,dataList));
//			ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new AppItemTounhHelperCallBack(myDuiLianCardRecyclerViewAdapter,BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll()));
			itemTouchHelper.attachToRecyclerView(recyclerView);
			
		}
		return view;
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
	}
	
}
