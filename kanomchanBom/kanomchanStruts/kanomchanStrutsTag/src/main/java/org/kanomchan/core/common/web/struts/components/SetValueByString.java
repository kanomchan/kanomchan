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

import org.apache.struts2.components.ContextBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="setValueByString", tldBodyContent="JSP", tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.SetValueByStringTag", description="Assigns a value to a variable in a specified scope")
public class SetValueByString extends ContextBean {
    protected String scope;
    protected String value;

    public SetValueByString(ValueStack stack) {
        super(stack);
    }

    public boolean end(Writer writer, String body) {
        ValueStack stack = getStack();

        Object o;
        if (value == null) {
            if (body != null && !body.equals("")) {
            	String exp = findString(body);
                o = findValue(exp);
//                o = body;
            } else {
                o = findValue("top");
            }
        } else {
        	String exp = findString(value);
            o = findValue(exp);
        }

        body="";

        if ("application".equalsIgnoreCase(scope)) {
            stack.setValue("#application['" + getVar() + "']", o);
        } else if ("session".equalsIgnoreCase(scope)) {
            stack.setValue("#session['" + getVar() + "']", o);
        } else if ("request".equalsIgnoreCase(scope)) {
            stack.setValue("#request['" + getVar() + "']", o);
        } else if ("page".equalsIgnoreCase(scope)) {
            stack.setValue("#attr['" + getVar() + "']", o, false);
        } else {
            stack.getContext().put(getVar(), o);
            stack.setValue("#attr['" + getVar() + "']", o, false);
        }

        return super.end(writer, body);
    }

    /*
     * TODO: set required=true when 'id' is dropped after 2.1
     */
    @StrutsTagAttribute(description="Name used to reference the value pushed into the Value Stack")
    public void setVar(String var) {
       super.setVar(var);
    }

    @StrutsTagAttribute(description="Deprecated. Use 'var' instead")
    public void setName(String name) {
        setVar(name);
    }

    @StrutsTagAttribute(description="The scope in which to assign the variable. Can be <b>application</b>" +
                ", <b>session</b>, <b>request</b>, <b>page</b>, or <b>action</b>.", defaultValue="action")
    public void setScope(String scope) {
        this.scope = scope;
    }

    @StrutsTagAttribute(description="The value that is assigned to the variable named <i>name</i>")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean usesBody() {
        return true;
    }
}
