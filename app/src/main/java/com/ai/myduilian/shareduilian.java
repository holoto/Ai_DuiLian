/*******************************************************************************
 * Author: holoto
 * Date: 2/22/19 5:27 PM
 ******************************************************************************/

package com.ai.myduilian;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ai.myduilian.Api.staticConfig;
import com.ai.myduilian.eventclass.event_settitle;
import com.ai.myduilian.objectBoxModel.DuiLIanData;

import org.greenrobot.eventbus.EventBus;

import io.objectbox.Box;


public class shareduilian extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private Box<DuiLIanData> duiLIanDataBox;
	
	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	ImageView imageView;
	CardView cardView;
	Bitmap bitmap;
	private OnListFragmentInteractionListener mListener;
	
	public shareduilian() {
		// Required empty public constructor
	}
	

	public static shareduilian newInstance() {
		shareduilian fragment = new shareduilian();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		duiLIanDataBox=BaseApplication.getBaseApplicationinstance().getBoxStore().boxFor(DuiLIanData.class);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		EventBus.getDefault().post(new event_settitle("share"));
		View view=inflater.inflate(R.layout.shareduilian, container, false);
		imageView=(ImageView) view.findViewById(R.id.share_image_canvas);
//		cardView=(CardView) view.findViewById(R.id.share_cardview);
		FrameLayout frameLayout=(FrameLayout) view.findViewById(R.id.share_framelayout);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int mwidthp=dm.widthPixels;
		int mheight=dm.heightPixels;
		Bitmap bitmap=Bitmap.createBitmap(mwidthp,mheight,Bitmap.Config.ARGB_8888);
		Log.e("onCreateView", "onCreateView: "+mwidthp );
		Log.e("onCreateView", "onCreateView: "+mheight);
		bitmap.eraseColor(Color.parseColor("#468EC8"));
		Canvas canvas=new Canvas(bitmap);
		
		
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
	paint.setTextAlign(Paint.Align.CENTER);
		
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(8);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextSize(50);
		Paint.FontMetrics fontMetrics=paint.getFontMetrics();
		Log.e("onCreateView", "top: "+fontMetrics.top );
		Log.e("onCreateView", "bottom: "+fontMetrics.bottom );
		Log.e("onCreateView", "leading: "+fontMetrics.leading );
		Log.e("onCreateView", "descent: "+fontMetrics.descent );
		Log.e("onCreateView", "ascent: "+fontMetrics.ascent );
		
		String xl=duiLIanDataBox.get(staticConfig.getShare_id()).getXialian();
		String sl=duiLIanDataBox.get(staticConfig.getShare_id()).getShanglian();
		canvas.drawText(duiLIanDataBox.get(staticConfig.getShare_id()).getHengpi(),mwidthp/2,100,paint);
		int y=new Double((mheight-xl.length()*(paint.descent()-paint.ascent()))/2).intValue();
		Log.e("onCreateView", "onCreateView: y"+y );
		setmytext(paint,xl,canvas,mwidthp-100,y,fontMetrics);
		setmytext(paint,sl,canvas,100,y,fontMetrics);
		
		canvas.drawBitmap(bitmap,0,0,paint);
		imageView.setImageBitmap(bitmap);
		
		return view;
	}

	private Bitmap changeBitmapSize(Bitmap bitmap,float newwitdh,float newheight) {
int width = bitmap.getWidth();
int height = bitmap.getHeight();
Log.e("width","width:"+width);
Log.e("height","height:"+height);
//设置想要的大小
int newWidth=30;
int newHeight=30;

//计算压缩的比率
float scaleWidth=((float)newWidth)/width;
float scaleHeight=((float)newHeight)/height;

//获取想要缩放的matrix
Matrix matrix = new Matrix();
matrix.postScale(scaleWidth,scaleHeight);

//获取新的bitmap
bitmap=Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
bitmap.getWidth();
bitmap.getHeight();
Log.e("newWidth","newWidth"+bitmap.getWidth());
Log.e("newHeight","newHeight"+bitmap.getHeight());
return bitmap;
	}


	
	private void setmytext(Paint paint,String s,Canvas canvas,int x,int y,Paint.FontMetrics fontMetrics){
		
		for (int i=0;i<s.length()-1;i++){
			int yy=new Double(y+(paint.descent()-paint.ascent())*i).intValue();
			Log.e("setmytext", "setmytextyy: "+yy  );
			canvas.drawText(s.substring(i,i+1),x,yy,paint);
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
	

	
}
