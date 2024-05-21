package com.example.articlemanager.validation;

import com.example.articlemanager.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//ConstraintValidator<給哪個註解提供校驗規則,校驗的數據類型>
public class StateValidation implements ConstraintValidator<State,String> {

    /**
     *
     * @param value 將來要校驗的數據
     * @param constraintValidatorContext 约束校验上下文
     *
     * @return true:校驗成功 false:校驗失敗
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value.equals("已發布") || value.equals("草稿")){
            return true;
        }else {
            return false;
        }
    }
}
