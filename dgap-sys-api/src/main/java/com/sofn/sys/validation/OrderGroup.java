/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.sys.validation;

import javax.validation.GroupSequence;

/**
 * 分组顺序接口
 *
 * @author lijiang
 * @create 2019-04-16 19:00
 **/
@GroupSequence({First.class, Second.class, Third.class})
public interface OrderGroup {
}
