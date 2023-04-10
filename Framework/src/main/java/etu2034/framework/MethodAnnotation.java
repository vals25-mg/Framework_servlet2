package etu2034.framework;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnotation {
    String url();
}
