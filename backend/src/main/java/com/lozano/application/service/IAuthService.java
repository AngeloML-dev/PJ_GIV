package com.lozano.application.service;

import com.lozano.application.dto.Login.LoginRequest;
import com.lozano.application.dto.Login.LoginResponse;

public interface IAuthService {
    LoginResponse authenticate(LoginRequest loginRequest);
}
