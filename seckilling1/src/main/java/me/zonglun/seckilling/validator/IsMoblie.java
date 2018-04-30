package me.zonglun.seckilling.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author : Administrator
 * @create 2018-04-30 11:06
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class })
public @interface IsMoblie {

    boolean required() default false;

    String message() default "手机号码格式错误，请检查手机号码";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
