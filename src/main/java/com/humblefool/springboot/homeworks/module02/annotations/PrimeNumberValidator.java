package com.humblefool.springboot.homeworks.module02.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation,Integer> {
    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
        if(input == null) {return true;}
        if(input<=1) return false;
        for(int i=2;i<=input/2;i++){
            if(input%i==0){
                return false;
            }
        }
        return true;
    }

}
