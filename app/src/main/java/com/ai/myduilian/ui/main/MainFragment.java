package com.ai.myduilian.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ai.myduilian.BaseApplication;
import com.ai.myduilian.JsonModel.xialianjson;
import com.ai.myduilian.Api.simpleApi;
import com.ai.myduilian.Api.staticConfig;
import com.ai.myduilian.MainActivity;
import com.ai.myduilian.R;
import com.ai.myduilian.dummy.xianlianlist;
import com.ai.myduilian.eventclass.event_settitle;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment

{
	Button button;
	TextInputEditText shanglian_input;
	TextWatcher textWatcher;
	private MainViewModel mViewModel;
	public static MainFragment newInstance() {
		return new MainFragment();
	}
	public MainFragment(){
	
}
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		EventBus.getDefault().post(new event_settitle("shanglian"));
		return inflater.inflate(R.layout.main_fragment, container, false);
	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		
	}
	
	@Override
	public void onAttachFragment(Fragment childFragment) {
		super.onAttachFragment(childFragment);
	}
	
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
		mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
		
	}
	private void  showerrror(TextInputEditText shanglian_input,String error){
		shanglian_input.setError(error);
		shanglian_input.setFocusable(true);
		shanglian_input.setFocusableInTouchMode(true);
		shanglian_input.requestFocus();
	}
	private boolean isfullchinaese(String s){
		String pattern = "[\u4e00-\u9fa5]+";
		boolean isMatch =  Pattern.matches(pattern, s);
		Log.e("validateshanlian", "validateshanlian: "+isMatch );
		return isMatch;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		shanglian_input=(TextInputEditText) getView().findViewById(R.id.shanglian_input);
		button=(Button) getView().findViewById(R.id.shanglian_enture);
		validatexianlian(shanglian_input.getEditableText().toString());
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(shanglian_input.getText().toString()))
				{
					
					
					Log.e("onClick", "onClick: "+shanglian_input.getHint() );
					Log.e("onClick", "onClick: "+shanglian_input.getEditableText() );
					Log.e("onClick", "onClick: "+shanglian_input.getText() );
					Log.e("onClick", "onClick: "+isfullchinaese(shanglian_input.getText().toString()) );
					Log.e("onClick", "onClick: "+getxialianLocker(shanglian_input.getText().toString()) );
					staticConfig.setShanglianString(shanglian_input.getEditableText().toString());
					getxianlian(staticConfig.getShanglianString());
				}
				
				
			}
		});
		textWatcher=new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String words=s.toString().trim();
				validatexianlian(words);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				String words=s.toString().trim();
				validatexianlian(words);
				if (!button.isEnabled()){
					Toast.makeText(BaseApplication.getContext(),"只能输入中文",Toast.LENGTH_SHORT).show();
				}else {
					staticConfig.setShanglianString(words);
				}
				
			}
		};
		shanglian_input.addTextChangedListener(textWatcher);
//		shanglian_input.setFilters(new InputFilter[]{getInputFilter()});
		


		
	
	}
	
	
	
	private String getxialianLocker(String s){
	String	a="0";
		for (int i=1;i<s.length();i++){
			a+='0';
		}
		return a;
	}
	private void validatexianlian(String words){
		if (isfullchinaese(words)){
			button.setEnabled(true);
		}else {
			button.setEnabled(false);
		}
	}
	public InputFilter getInputFilter(){
		InputFilter inputFilter=new InputFilter() {
			@Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
				if (TextUtils.isEmpty(source)){
					return "";
//					showerrror(shanglian_input,"dd");
				}
				if (!isfullchinaese(shanglian_input.getText().toString())){
					return "";
//					showerrror(shanglian_input,"");
				}
				
				
				
				return null;
			}
		};
		return  inputFilter;
	}
	private void getxianlian(String shanglian){
		
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("isUpdate",false);
		jsonObject.addProperty("shanglian",shanglian);
		jsonObject.addProperty("xialianLocker",getxialianLocker(staticConfig.getShanglianString()));
		
		simpleApi.getxianlian(jsonObject).enqueue(new Callback<xialianjson>() {
			@Override
			public void onResponse(Call<xialianjson> call, Response<xialianjson> response) {
				Log.e("onResponse", "onResponse: "+response.code() );
				
				

				if (response.code()==200){
					Log.e("onResponse", "onResponse: "+response.body().getD().getXialianSystemGeneratedSets().get(0).getWordCandidatesInColumnOrder() );
					Log.e("onResponse", "onResponse: "+response.body().getD().getXialianSystemGeneratedSets().get(0).getXialianCandidates() );
					staticConfig.setXianlianjsonlist(response.body());
					staticConfig.setXialianlist(response.body().getD().getXialianSystemGeneratedSets().get(0).getXialianCandidates() );
					Log.e("onResponse", "onResponse: "+staticConfig.getXialianlist() );
					xianlianlist.setList(staticConfig.getXialianlist());
					xianlianlist.setdata();
					Log.e("onResponse", "onResponse: "+response.body().getD().getXialianSystemGeneratedSets().get(0).getSegmentPattern() );
					Log.e("onResponse", "onResponse: "+response.body().getD().getXialianSystemGeneratedSets().get(0).getExtensionData() );
					EventBus.getDefault().post("xianlian");
				}else {
					Toast.makeText(BaseApplication.getContext(),"网络错误",Toast.LENGTH_SHORT);
				}
			}
			
			@Override
			public void onFailure(Call<xialianjson> call, Throwable t) {
				Log.e("onFailure", "onFailure: "+t.getMessage() );
				Log.e("onFailure", "onFailure: "+t.getStackTrace().toString() );
				Toast.makeText(BaseApplication.getContext(),"无网络",Toast.LENGTH_SHORT);
			}
		});
		
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();
//		BaseApplication.getRefWatcher().watch(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
	}
	

	
}
