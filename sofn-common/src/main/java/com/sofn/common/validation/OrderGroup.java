/*
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 *
 *
 */
package com.sofn.common.validation;

import javax.validation.GroupSequence;

/**
 * 分组校验顺序接口
 *
 * @author John
 * @version 1.0
 */
@GroupSequence({ValidationSequence.First.class, ValidationSequence.Second.class, ValidationSequence.Third.class})
public interface OrderGroup {
}
