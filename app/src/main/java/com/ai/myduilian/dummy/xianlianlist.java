package com.ai.myduilian.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class xianlianlist {
	

	public static  List<DummyItem> ITEMS = new ArrayList<DummyItem>();
	

	public static  List<String> list=new ArrayList<String>();
	private static int positionx=0;
	
	public static void setList(List<String> list) {
		
		xianlianlist.list=list;
	}
	
	public static int getPositionx() {
		return positionx;
	}
	

	
	public static void setITEMS(List<DummyItem> ITEMS) {
		xianlianlist.ITEMS = ITEMS;
	}
	
	public static  	void setdata() {
		
		for (positionx = 0; positionx < list.size(); positionx++) {
			addItem(createDummyItem(positionx,list.get(positionx)));
		}
	}
	
	private static void addItem(DummyItem item) {
		ITEMS.add(item);
//		ITEM_MAP.put(item.id, item);
	}
	
	private static DummyItem createDummyItem(int position,String content) {
		return new DummyItem(String.valueOf(position), content);
	}
	
	private static String makeDetails(int position) {
		StringBuilder builder = new StringBuilder();
		builder.append("Details about Item: ").append(position);
		for (int i = 0; i < position; i++) {
			builder.append("\nMore details information here.");
		}
		return builder.toString();
	}
	
	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public  String id;
		public  String content;
		
		public DummyItem(String id, String content) {
			this.id = id;
			this.content = content;
		}
		
		@Override
		public String toString() {
			return content;
		}
	}
}
