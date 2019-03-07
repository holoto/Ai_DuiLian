package com.ai.myduilian.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

public class MainViewModel extends ViewModel {
	// TODO: Implement the ViewModel
	public  static class shanlian{
		private List<String> data;
	public  shanlian(List<String> d){
		this.data=d;
	}
		
		public List<String> getData() {
			return data;
		}
		
		public void setData(List<String> data) {
			this.data = data;
		}
	}
	private MutableLiveData<List<shanlian>> shanliandata;
	
	public MutableLiveData<List<shanlian>> getShanliandata() {
		if (shanliandata==null){
			shanliandata=new MutableLiveData<List<shanlian>>();
		}
		return shanliandata;
	}
	
	public void setShanliandata(MutableLiveData<List<shanlian>> shanliandata) {
		this.shanliandata = shanliandata;
	}
	
	@Override
	protected void onCleared() {
		super.onCleared();
	}
}
