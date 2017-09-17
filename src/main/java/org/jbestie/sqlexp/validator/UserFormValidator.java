package org.jbestie.sqlexp.validator;

import org.jbestie.sqlexp.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.userForm.login", "Should be filled");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.userForm.password", "Should be filled");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty.userForm.email", "Should be filled");
    }

}
