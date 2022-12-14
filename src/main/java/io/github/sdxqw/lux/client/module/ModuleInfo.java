package io.github.sdxqw.lux.client.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleInfo {
    String name();

    String icon();

    boolean enabled() default false;

    int x() default 1;

    int y() default 1;
}
