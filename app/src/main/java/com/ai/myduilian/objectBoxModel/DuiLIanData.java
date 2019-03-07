/*******************************************************************************
 * Author: holoto
 * Date: 2/23/19 5:31 PM
 ******************************************************************************/

package com.ai.myduilian.objectBoxModel;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

@Entity
public class DuiLIanData {
	
 private 	String shanglian;
private 	String xialian;
	@Index
private 	String hengpi;
	@Id(assignable = true)
	private 	long id;
	public DuiLIanData(long itemid,String sl,String xl,String hp){
		shanglian=sl;
		xialian=xl;
		hengpi=hp;
	}
	
	public  DuiLIanData(){
	
	}
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getHengpi() {
		return hengpi;
	}
	
	public void setHengpi(String hengpi) {
		this.hengpi = hengpi;
	}
	
	public String getShanglian() {
		return shanglian;
	}
	
	public void setShanglian(String shanglian) {
		this.shanglian = shanglian;
	}
	
	public String getXialian() {
		return xialian;
	}
	
	public void setXialian(String xialian) {
		this.xialian = xialian;
	}
}
