package model;

import java.util.Date;

public class RemainUsage {

	private Number REMAIN_DATA;
	private Number REMAIN_VOICE;
	private Number REMAIN_SMS;
	private Number USABLE_DATA;
	private Number USABLE_VOICE;
	private Number USABLE_SMS;
	private Date   LAST_READ;
	
	
	public Date getLAST_READ() {
		return LAST_READ;
	}
	public void setLAST_READ(Date lAST_READ) {
		LAST_READ = lAST_READ;
	}
	public Number getUSABLE_DATA() {
		return USABLE_DATA;
	}
	public void setUSABLE_DATA(Number uSABLE_DATA) {
		USABLE_DATA = uSABLE_DATA;
	}
	public Number getUSABLE_VOICE() {
		return USABLE_VOICE;
	}
	public void setUSABLE_VOICE(Number uSABLE_VOICE) {
		USABLE_VOICE = uSABLE_VOICE;
	}
	public Number getUSABLE_SMS() {
		return USABLE_SMS;
	}
	public void setUSABLE_SMS(Number uSABLE_SMS) {
		USABLE_SMS = uSABLE_SMS;
	}
	public Number getREMAIN_DATA() {
		return REMAIN_DATA;
	}
	public void setREMAIN_DATA(Number rEMAIN_DATA) {
		REMAIN_DATA = rEMAIN_DATA;
	}
	public Number getREMAIN_VOICE() {
		return REMAIN_VOICE;
	}
	public void setREMAIN_VOICE(Number rEMAIN_VOICE) {
		REMAIN_VOICE = rEMAIN_VOICE;
	}
	public Number getREMAIN_SMS() {
		return REMAIN_SMS;
	}
	public void setREMAIN_SMS(Number rEMAIN_SMS) {
		REMAIN_SMS = rEMAIN_SMS;
	}
	
	
	
	
	
}
