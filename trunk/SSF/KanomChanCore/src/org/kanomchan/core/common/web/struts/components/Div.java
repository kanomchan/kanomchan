/*
 * $Id: Set.java 1412216 2012-11-21 18:02:53Z grobmeier $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.kanomchan.core.common.web.struts.components;

import java.io.Writer;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.processhandler.ProcessContext;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="div", tldBodyContent="JSP", tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.DivTag", description="Assigns a value to a variable in a specified scope")
public class Div extends org.apache.struts2.components.Div {
	
	protected String displayKey;
	private boolean keyStatus;
//	private DisplayField displayFieldService;
	
	public Div(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
	   super(stack, request, response);
	}

    @Override
    public boolean start(Writer writer) {
    	return keyStatus;
    }
    
    @Override
    public boolean end(Writer writer, String body) {
    	return keyStatus;
    }
    
    private boolean checkDisplayKey (String displayKey) throws RollbackException, NonRollBackException
    {
    	ProcessContext processContext = CurrentThread.getProcessContext();
    	
    	Long idZone = processContext.getZone();
    	Long idCountry = processContext.getCountry();
    	Long idProvince = processContext.getProvince();
    	
    	int pos = displayKey.indexOf("_");
    	String page = displayKey.substring(0,pos);
    	String field = displayKey.substring(pos);
//    	ServiceResult<DisplayField> displayField = displayFieldService.getDisplayFieldByMany(idZone, idCountry, idProvince, page, field);
    	//SELECT * FROM com_m_display_field where id_country=1 AND page='' AND filed=''
    	// wrong try to get displayField.getIsDisplay
//    	return "Y".equals(displayField);
    	return false;
    }
    
    @StrutsTagAttribute(description="The value that is assigned to the variable named <i>name</i>")
    public void setDisplayKey(String displayKey) throws RollbackException, NonRollBackException {
		this.displayKey = displayKey;
		keyStatus = checkDisplayKey(displayKey);
	}
    
}
