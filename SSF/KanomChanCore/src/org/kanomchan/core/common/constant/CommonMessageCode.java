package org.kanomchan.core.common.constant;

public enum CommonMessageCode implements MessageCode {
	COM0000("Transaction Complete.");

	private CommonMessageCode(String dec){
		this.dec = dec;
	}
	private String dec;
	
	public String getCode() {
		return name();
	}
	
	@Override
	public String toString() {
		return "code : "+name()+" dec : "+dec;
	}

}
