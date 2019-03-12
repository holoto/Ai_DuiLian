package com.ai.myduilian;

import android.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ai.myduilian.Api.simpleApi;
import com.ai.myduilian.JsonModel.hengpijson;
import com.ai.myduilian.eventclass.event_settitle;
import com.ai.myduilian.DuiLianCardFragment;
import com.ai.myduilian.objectBoxModel.DuiLIanData;
import com.ai.myduilian.ui.main.MainFragment;
import com.ai.myduilian.Api.staticConfig;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import io.objectbox.Box;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener
{
	 final FragmentManager fragmentManagerx=getSupportFragmentManager();
	FragmentTransaction fragmentTransactionx=fragmentManagerx.beginTransaction();
	 MainFragment mainFragmentx;
	 xialianFragment xialianFragmentx;
	 hengpilistFragment hengpilistFragmentx;
	 shareduilian shareduilianx;
	DuiLianCardFragment duiLianCardFragmentx;
	MainPage mainPage;
	RadioButton radioButton_create;
	RadioButton radioButton_list;
	EmptyList emptyList;
	private Box<DuiLIanData> duiLIanDataBox;
	
	@Nullable
	@android.support.annotation.Nullable
	@Override
	public ActionBar getActionBar() {
		return super.getActionBar();
	}
	
	@Override
	public void onListFragmentInteraction(String item) {
		duiLIanDataBox=BaseApplication.getBaseApplicationinstance().getBoxStore().boxFor(DuiLIanData.class);
		Log.e("onListFragmentInter", "onListFragmentInteraction: "+item );
		if (xialianFragmentx.isAdded()&&!xialianFragmentx.isHidden()){
			staticConfig.setXialianstring(item);
			gethengpi(item);
		}else if (!hengpilistFragmentx.isHidden()&&hengpilistFragmentx.isAdded()){
			staticConfig.setHengpistring(item);
			Log.e("11", "onListFragmentInteraction: "+staticConfig.getShanglianString() );
			Log.e("11", "onListFragmentInteraction: "+staticConfig.getXialianstring() );
			Log.e("11", "onListFragmentInteraction: "+staticConfig.getHengpistring() );
//			duiLIanDataBox.removeAll();
			DuiLIanData duiLIanData=new DuiLIanData();
			duiLIanData.setShanglian(staticConfig.getShanglianString());
			duiLIanData.setXialian(staticConfig.getXialianstring());
			duiLIanData.setHengpi(staticConfig.getHengpistring());
			duiLIanDataBox.put(duiLIanData);
			swithfragment("card");
		}else if (duiLianCardFragmentx.isAdded()&&!duiLianCardFragmentx.isHidden()){
			Log.e("dd", "onListFragmentInteraction: "+Integer.parseInt(item) );
			Log.e("id", "onListFragmentInteraction: "+item);
			staticConfig.setShare_id(Integer.parseInt(item));
			swithfragment("share");
		}else if (mainPage.isAdded()&&!mainPage.isHidden()){
		
		}
	}
	private void gethengpi(String s){
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("xialian",staticConfig.getXialianstring());
		jsonObject.addProperty("shanglian",staticConfig.getShanglianString());
		simpleApi.gethengpi(jsonObject).enqueue(new Callback<hengpijson>() {
			@Override
			public void onResponse(Call<hengpijson> call, Response<hengpijson> response) {
				Log.e("onResponse", "onResponse: "+response.code() );
			if (response.code()==200){
				staticConfig.setHengpijsonlist(response.body());
				Log.e("onResponse", "onResponse: "+response.body() );
				Log.e("onResponse", "onResponse: "+response.body().getD() );
				staticConfig.setHengpilist(response.body().getD());
				swithfragment("hengpi");
			}else {
				Toast.makeText(BaseApplication.getContext(),"网络错误",Toast.LENGTH_SHORT);
			}
			}
			
			@Override
			public void onFailure(Call<hengpijson> call, Throwable t) {
				Toast.makeText(BaseApplication.getContext(),"无网络",Toast.LENGTH_SHORT);
			}
		});
	}
	private void inittapbar(){
	radioButton_create=findViewById(R.id.tab_create);
	radioButton_list=findViewById(R.id.tab_list);
	radioButton_create.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			swithfragment("shanglian");
		}
	});
	radioButton_list.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			swithfragment("card");
		}
	});
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//无title
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏
//		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
//				WindowManager.LayoutParams. FLAG_FULLSCREEN);
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//			Window window = getWindow();
//			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//			window.setStatusBarColor(Color.TRANSPARENT);
//		}
		
		EventBus.getDefault().register(this);
//		fragmentManagerx=getSupportFragmentManager();
//		fragmentTransactionx=fragmentManagerx.beginTransaction();
		setContentView(R.layout.main_activity);
		if (savedInstanceState == null) {
			mainFragmentx=MainFragment.newInstance();
			xialianFragmentx=xialianFragment.newInstance();
			hengpilistFragmentx=hengpilistFragment.newInstance();
			shareduilianx= shareduilian.newInstance();
			duiLianCardFragmentx=DuiLianCardFragment.newInstance();
			mainPage=MainPage.newInstance();
			emptyList=EmptyList.newInstance();
//			fragmentTransactionx.replace(R.id.maincontainer, mainFragmentx);
			fragmentTransactionx.replace(R.id.maincontainer, duiLianCardFragmentx);
//			fragmentTransactionx.replace(R.id.maincontainer, mainPage);
//			fragmentTransactionx.replace(R.id.maincontainer, duilianCard);
//			fragmentTransactionx.replace(R.id.maincontainer, duilianCard);


//			fragmentTransactionx.add(R.id.maincontainer, xialianFragmentx,null);
//			fragmentTransactionx.add(R.id.maincontainer,hengpilistFragmentx,null);
//			fragmentTransactionx.hide(xialianFragmentx);
//			fragmentTransactionx.hide(hengpilistFragmentx);
//			fragmentTransactionx.show(mainFragmentx);
			
		
		}else {
			mainFragmentx=(MainFragment) fragmentManagerx.getFragment(savedInstanceState,"MainFragment");
			xialianFragmentx=(xialianFragment) fragmentManagerx.getFragment(savedInstanceState,"xialianFragment");
			hengpilistFragmentx=(hengpilistFragment) fragmentManagerx.getFragment(savedInstanceState,"hengpilistFragment");
			shareduilianx=(shareduilian) fragmentManagerx.getFragment(savedInstanceState,"shareduilianx");
			duiLianCardFragmentx=(DuiLianCardFragment) fragmentManagerx.getFragment(savedInstanceState,"duiLianCardFragmentx");
			mainPage=(MainPage) fragmentManagerx.getFragment(savedInstanceState,"mainpage");
			emptyList=(EmptyList) fragmentManagerx.getFragment(savedInstanceState,"emptyList");
		}
		fragmentTransactionx.commit();
		inittapbar();
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void swithfragment(String str){
		fragmentTransactionx=fragmentManagerx.beginTransaction();
		switch (str){
			case "shanglian":
				if (!mainFragmentx.isAdded()){
					fragmentTransactionx.replace(R.id.maincontainer,mainFragmentx);
					
				}
				fragmentTransactionx.addToBackStack(null);
				break;
			case "xianlian":
			
				
				if (!xialianFragmentx.isAdded()){
					fragmentTransactionx.replace(R.id.maincontainer,xialianFragmentx);
				}
				fragmentTransactionx.addToBackStack(null);
				
				break;
			case "hengpi":
				if (!hengpilistFragmentx.isAdded()){
					fragmentTransactionx.replace(R.id.maincontainer,hengpilistFragmentx);
				}
				fragmentTransactionx.addToBackStack(null);
				break;
			case "card":
				if (!duiLianCardFragmentx.isAdded()&&BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll().size()>0){
					fragmentTransactionx.replace(R.id.maincontainer,duiLianCardFragmentx);
				
				}else if (BaseApplication.getBaseApplicationinstance().getDuiLIanDataBox().getAll().size()==0&&!emptyList.isAdded()){
				fragmentTransactionx.replace(R.id.maincontainer,emptyList);
				}
				fragmentTransactionx.addToBackStack(null);
				break;
			case "share":
				if(!shareduilianx.isAdded()){
					fragmentTransactionx.replace(R.id.maincontainer,shareduilianx);
				}
				fragmentTransactionx.addToBackStack(null);
				break;
			case "mainpage":
				if (!mainPage.isAdded()){
					fragmentTransactionx.replace(R.id.maincontainer,mainPage);
				}
				fragmentTransactionx.addToBackStack(null);
				break;
				default:
					break;
					
		}
		fragmentTransactionx.commit();
	}
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void fragment_settitle(event_settitle eventSettitle){
		getSupportActionBar().setTitle(eventSettitle.getTitle());
	}
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		
	}
}
