package org.kanomchan.core.common.processhandler;

import java.util.List;
import java.util.Locale;

import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.bean.UserBean;
import org.springframework.transaction.TransactionStatus;

public class ProcessContext {
	
	protected String transId;

	protected TransactionStatus txnStatus;

	protected UserBean userBean;
	
	protected List<Message> messageList;
	
	protected boolean startProcess = false;

	protected String status;
	
	protected String lang;

	public Locale getLocale() {
		return null;
	}

	public void setLang(String langKey) {
		lang = langKey;
	}
}
