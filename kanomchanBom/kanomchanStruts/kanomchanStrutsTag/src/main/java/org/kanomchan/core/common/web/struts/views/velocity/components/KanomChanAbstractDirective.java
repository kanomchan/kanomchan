package org.kanomchan.core.common.web.struts.views.velocity.components;

import org.apache.struts2.views.velocity.components.AbstractDirective;

public abstract class KanomChanAbstractDirective extends AbstractDirective {
  public String getName()
  {
    return "kc" + getBeanName();
  }
}
