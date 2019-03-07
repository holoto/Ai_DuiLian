/*******************************************************************************
 * Author: holoto
 * Date: 2/15/19 10:29 AM
 ******************************************************************************/

package com.ai.myduilian.Api;

import com.ai.myduilian.JsonModel.hengpijson;
import com.ai.myduilian.JsonModel.xialianjson;
import com.ai.myduilian.JsonModel.IsValidChineseStringjson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class DuiLianAPI{
//	https://duilian.msra.cn/app/CoupletsWS_V2.asmx/GetXiaLian
	public static   class Apiurl{
		private static String baseurl="https://duilian.msra.cn/app/CoupletsWS_V2.asmx/";
		private static String xianlianurl="GetXiaLian";
		private static String IsValidChineseStringurl="IsValidChineseString";
		private  static String hengpiurl="GetHengPi";
		
		public static String getBaseurl() {
			return baseurl;
		}
		
		public static String getHengpiurl() {
			return hengpiurl;
		}
		
		public static String getIsValidChineseStringurl() {
			return IsValidChineseStringurl;
		}
		
		public static String getXianlianurl() {
			return  xianlianurl;
		}
	}
	public interface getxianlianjson {
		@Headers( "Content-Type: application/json" )
		@POST("{url}")
		Call<xialianjson> add(@Body JsonObject body  , @Path("url") String url);
	}
	public interface  gethengpijson{
		@Headers( "Content-Type: application/json" )
		@POST("{url}")
		Call<hengpijson> add(@Body JsonObject body  , @Path("url") String url);
	}
	public interface  getIsValidChineseStringjson{
		@Headers( "Content-Type: application/json" )
		@POST("{url}")
		Call<IsValidChineseStringjson> add(@Body JsonObject body  , @Path("url") String url);
	}
	
}