package com.sofn.core.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/** Created by Code.Ai on 16/8/10. Description: */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

  @Override
  public Subject createSubject(SubjectContext context) {
    // 不创建session
    context.setSessionCreationEnabled(true);
    return super.createSubject(context);
  }
}
