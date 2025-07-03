package com.example.todosApps.service;

import com.example.todosApps.dto.LoginDto;
import com.example.todosApps.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    String logIn(LoginDto loginDto);
}
