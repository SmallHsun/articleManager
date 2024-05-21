package com.example.articlemanager.anno;

import com.example.articlemanager.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class}) //指定提供校驗規則的類

public @interface State {
    //校驗失敗後的提示信息
    String message() default "{state參數只能是已發布或草稿}";

    //指定分組
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
