package com.ai.myduilian.Api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import com.ai.myduilian.JsonModel.hengpijson;
import com.ai.myduilian.JsonModel.xialianjson;

public class staticConfig {
	public   static long readTimeout=4500;
	public static long connectTimeout=4500;
	public static long writeTimeout=4500;
	public static long cachemaxsize=1024*1024*50;
	
	public static String cachedir="duiliancache";
	private static String shanglianString;
	private static  String xialianstring;
	private static String hengpistring;
	public static List<String> pleasegetshanlian=new LinkedList<>(Arrays.asList("请输入上联获取下联"));
	public static List<String> pleasegetxialian=new LinkedList<>(Arrays.asList("请输入下联获取横批"));
	private static List<String> xialianlist=new ArrayList<>();
	private static List<String> hengpilist=new ArrayList<>();
	private static hengpijson hengpijsonlist;
	private static xialianjson xianlianjsonlist;
	private static int share_id;
	
	public static int getShare_id() {
		return share_id;
	}
	
	public static void setShare_id(int share_id) {
		staticConfig.share_id = share_id;
	}
	
	public static String getShanglianString() {
		return shanglianString;
	}
	
	public static void setShanglianString(String shanglianString) {
		staticConfig.shanglianString = shanglianString;
	}
	
	public static List<String> getPleasegetshanlian() {
		return pleasegetshanlian;
	}
	
	public static void setPleasegetshanlian(List<String> pleasegetshanlian) {
		staticConfig.pleasegetshanlian = pleasegetshanlian;
	}
	
	public static xialianjson getXianlianjsonlist() {
		return xianlianjsonlist;
	}
	
	public static List<String> getXialianlist() {
		return xialianlist;
	}
	
	public static String getXialianstring() {
		return xialianstring;
	}
	
	public static void setXialianlist(List<String> xialianlist) {
		staticConfig.xialianlist = xialianlist;
	}
	
	public static void setXialianstring(String xialianstring) {
		staticConfig.xialianstring = xialianstring;
	}
	
	public static void setXianlianjsonlist(xialianjson xianlianjsonlist) {
		staticConfig.xianlianjsonlist = xianlianjsonlist;
	}
	
	public static List<String> getPleasegetxialian() {
		return pleasegetxialian;
	}
	
	public static void setPleasegetxialian(List<String> pleasegetxialian) {
		staticConfig.pleasegetxialian = pleasegetxialian;
	}
	
	public static hengpijson getHengpijsonlist() {
		return hengpijsonlist;
	}
	
	public static void setHengpistring(String hengpistring) {
		staticConfig.hengpistring = hengpistring;
	}
	
	public static String getHengpistring() {
		return hengpistring;
	}
	
	public static void setHengpilist(List<String> hengpilist) {
		staticConfig.hengpilist = hengpilist;
	}
	
	public static List<String> getHengpilist() {
		return hengpilist;
	}
	
	public static void setHengpijsonlist(hengpijson hengpijsonlist) {
		staticConfig.hengpijsonlist = hengpijsonlist;
	}
}
