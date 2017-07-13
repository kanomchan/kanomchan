/*
 * $Id$
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

package org.kanomchan.core.common.web.struts.view.jsp.ui;

import com.opensymphony.xwork2.util.ValueStack;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.TextField;
import org.apache.struts2.views.jsp.ui.AbstractUITag;
import org.kanomchan.core.common.web.struts.components.Share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @see TextField
 */
public class ShareTag extends AbstractUITag {

    private static final long serialVersionUID = 5811285953670562288L;

    protected String maxlength;
    protected String readonly;
    protected String size;
    protected String type;
    protected String socialNetwork;
    protected String shareUrl;
    

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Share(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        Share share = ((Share) component);
        share.setMaxlength(maxlength);
        share.setReadonly(readonly);
        share.setSize(size);
        share.setType(type);
        share.setSocialNetwork(socialNetwork);
        share.setShareUrl(shareUrl);
    }

    /**
     * @deprecated please use {@link #setMaxlength} instead
     */
    public void setMaxLength(String maxlength) {
        this.maxlength = maxlength;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setSocialNetwork(String socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
    
    public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
    
}
