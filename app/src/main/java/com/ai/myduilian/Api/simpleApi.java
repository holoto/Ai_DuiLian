/*******************************************************************************
 * Author: holoto
 * Date: 2/15/19 2:24 PM
 ******************************************************************************/

package com.ai.myduilian.Api;

import com.ai.myduilian.JsonModel.xialianjson;
import com.ai.myduilian.JsonModel.hengpijson;
import com.ai.myduilian.JsonModel.IsValidChineseStringjson;
import com.google.gson.JsonObject;

import retrofit2.Call;

public  class simpleApi {
	
	public static Call<xialianjson> getxianlian(JsonObject jsonObject){
		DuiLianAPI.getxianlianjson getxianlianjson=RetrofitHelper.getInstance().buildRetrofit(DuiLianAPI.Apiurl.getBaseurl()).create(DuiLianAPI.getxianlianjson.class);
		Call<xialianjson> xianlianjsonCall=getxianlianjson.add(jsonObject,DuiLianAPI.Apiurl.getXianlianurl());
		return xianlianjsonCall;
		
	}
	public static Call<hengpijson> gethengpi(JsonObject jsonObject){
		DuiLianAPI.gethengpijson gethengpijson=RetrofitHelper.getInstance().buildRetrofit(DuiLianAPI.Apiurl.getBaseurl()).create(DuiLianAPI.gethengpijson.class);
		Call<hengpijson> hengpijsonCall=gethengpijson.add(jsonObject,DuiLianAPI.Apiurl.getHengpiurl());
		return hengpijsonCall;
	}
	public static Call<IsValidChineseStringjson> getIsValidChineseString(JsonObject jsonObject){
		DuiLianAPI.getIsValidChineseStringjson getIsValidChineseStringjson =RetrofitHelper.getInstance().buildRetrofit(DuiLianAPI.Apiurl.getBaseurl()).create(DuiLianAPI.getIsValidChineseStringjson.class);
		Call<IsValidChineseStringjson> isValidChineseStringjsonCall=getIsValidChineseStringjson.add(jsonObject,DuiLianAPI.Apiurl.getIsValidChineseStringurl());
		return isValidChineseStringjsonCall;
	}

}
