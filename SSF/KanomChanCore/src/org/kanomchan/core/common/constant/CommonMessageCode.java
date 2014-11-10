package org.kanomchan.core.common.constant;

public enum CommonMessageCode implements MessageCode {
	COM0000("Transaction Complete.")
	,COM0001("Success! Insert {1} Complete.")
	,COM0002("Success! Update {1} Complete.")
	,COM0003("Success! Delete {1} Complete.")
	, COM4999("Exction"), COM2001(""), ATC2001(""), ATC2002(""), ATZ2002(""), USR3001(""), USR3002(""), COM4994(""), COM4993(""), COM4998(""), COM0004(""), ATC2005(""), REG2001(""), REG2002(""), COM4886(""), COM4991(""), COM4992("");

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
