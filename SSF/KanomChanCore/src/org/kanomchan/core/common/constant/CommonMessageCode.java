package org.kanomchan.core.common.constant;

public enum CommonMessageCode implements MessageCode {
	COM0000("Transaction Complete.")
	,COM0001("Success! Insert {1} Complete.")
	,COM0002("Success! Update {1} Complete.")
	,COM0003("Success! Delete {1} Complete.")
	, COM4999("Exction"), COM2001(""), COM4994(""), COM4993(""), COM4998(""), COM0004(""), COM4886("")
	, COM4991("")
	, COM4992("")
	, COM4987("PK LANG NUll")
	, COM4986("fack id")
	, COM4893("File Not Found")
	, ATZ2001("Unauthorized Operation Business Exception")
	, ATC2001(""), ATC2002(""), ATZ2002("")
	, USR3001(""), USR3002("")
	, REG3001(""), REG3002(""), REG0003("")
	, ATC2005(""), REG2001(""), REG2002("")
	, EXA1001("Exction"), EXA1002("Exction")
	, KEY3999("")
	, KEY3998("")
	, KEY3997("")
	, KEY3996("")
	, KEY3995("")
	, KEY3994("")
	, KEY3993("")
	, KEY3992("")
	, KEY3991("")
	, KEY3990("")
	, KEY3989("")
	, KEY3988("");

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
