/*******************************************************************************
 * Author: holoto
 * Date: 2/22/19 4:27 PM
 ******************************************************************************/

package com.ai.myduilian.eventclass;

public class event_settitle {
	private String select_shanglian="输入上联";
	private String select_xialian="选择下联";
	private String select_hengpi="选择横批";
	private String select_default="AI对联";
	private String select_list="我的对联";
	private String select_create="新的对联";
	private String select_share="分享对联";
	private String title;
	public event_settitle(String s){
		switch (s){
			case "shanglian":
				title=select_shanglian;
				break;
			case "xialian":
				title=select_xialian;
				break;
			case "hengpi":
				title=select_hengpi;
				break;
			case "list":
				title=select_list;
				break;
			case "create":
				title=select_create;
				break;
			case "share":
				title=select_share;
				break;
			default:
					title=select_default;
					break;
		}
	}
	
	public String getTitle() {
		return title;
	}
}
