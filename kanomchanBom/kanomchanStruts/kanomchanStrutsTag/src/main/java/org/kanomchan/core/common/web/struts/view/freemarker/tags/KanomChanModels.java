package org.kanomchan.core.common.web.struts.view.freemarker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kanomchan.core.common.web.struts.components.MessageValidate;

import com.opensymphony.xwork2.util.ValueStack;

public class KanomChanModels {
	private ValueStack stack;
    private HttpServletRequest req;
    private HttpServletResponse res;
    
    protected SetValueByStringModel setValueByString;
    protected MessageValidateModel messageValidateModel;
    
    public KanomChanModels(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        this.stack = stack;
        this.req = req;
        this.res = res;
    }
	
    
    public SetValueByStringModel getSetValueByString() {
    	if(setValueByString ==null){
    		setValueByString = new SetValueByStringModel(stack, req, res);
    	}
		return setValueByString;
	}
    
    public MessageValidateModel getMessageValidate(){
    	if(messageValidateModel == null)
    		messageValidateModel = new MessageValidateModel(stack, req, res);
    	return messageValidateModel;
    }
}
