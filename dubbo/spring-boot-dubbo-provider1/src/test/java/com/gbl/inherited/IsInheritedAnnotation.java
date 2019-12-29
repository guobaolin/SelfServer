package com.gbl.inherited;

import java.lang.annotation.*;

/**
 * Created by guobaolin on 2019/12/25.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface IsInheritedAnnotation {
}
