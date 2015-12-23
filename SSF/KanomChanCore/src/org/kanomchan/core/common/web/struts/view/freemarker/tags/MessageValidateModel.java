package org.kanomchan.core.common.web.struts.view.freemarker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.freemarker.tags.TagModel;
import org.kanomchan.core.common.web.struts.components.MessageValidate;

import com.opensymphony.xwork2.util.ValueStack;

public class MessageValidateModel extends TagModel {

  public MessageValidateModel(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
    super(stack, req, res);
  }

  @Override
  protected Component getBean()
  {
    return new MessageValidate(stack, req, res);
  }

}
