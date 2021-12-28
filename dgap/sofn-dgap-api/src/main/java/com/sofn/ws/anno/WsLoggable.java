package com.sofn.ws.anno;

import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface WsLoggable {
    String description()  default "";
}
