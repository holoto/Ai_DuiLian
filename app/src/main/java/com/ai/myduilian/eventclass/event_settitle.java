/*******************************************************************************
 * Author: holoto
 * Date: 2/22/19 4:27 PM
 ******************************************************************************/

package com.ai.myduilian.eventclass;

public class event_settitle {
	private String select_xialian="选择下联";
	private String select_hengpi="选择横批";
	private String select_default="AI对联";
	private String title;
	public event_settitle(String s){
		switch (s){
			case "xialian":
				title=select_xialian;
				break;
			case "hengpi":
				title=select_hengpi;
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
