package org.kanomchan.core.common.constant;

public enum CommonMessageCode implements MessageCode {
	COM0000("Transaction Complete."), COM4999("Exction"), ATC2001(""), ATC2002(""), ATZ2002("");

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
