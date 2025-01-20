package com.kimulegen.forohub.domain.user.validators;

import com.kimulegen.forohub.domain.user.RegisterUserDTO;

public interface UserValidator {
    void validate(RegisterUserDTO registerUserDTO);
}
