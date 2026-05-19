package com.semcon.sgu.modules.systems.services;

import com.semcon.sgu.modules.auth.dtos.LoginRequestDto;
import com.semcon.sgu.modules.auth.dtos.LoginResponseDto;
import com.semcon.sgu.modules.auth.exceptions.InvalidCredentialsException;
import com.semcon.sgu.modules.systems.entity.System;
import com.semcon.sgu.modules.systems.entity.SystemUser;
import com.semcon.sgu.modules.systems.exceptions.SystemNotFound;
import com.semcon.sgu.modules.systems.repository.SystemRepository;
import com.semcon.sgu.modules.systems.repository.SystemUserRepository;
import com.semcon.sgu.modules.users.entity.User;
import com.semcon.sgu.modules.users.repository.UserRepository;
import com.semcon.sgu.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SystemAccessService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final SystemRepository systemRepository;
    private final SystemUserRepository systemUserRepository;
    private final JwtService jwtService;

    public LoginResponseDto systemAuthentication(LoginRequestDto loginRequestDto, String key) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        System system = systemRepository.findByKey(key);
        if (system == null) {
            throw new SystemNotFound();
        }

        SystemUser systemUser = systemUserRepository.findById(user.getId()).orElse(null);

        if (systemUser == null || Objects.equals(systemUser.getRole().getName(), "Sin rol")) {
            throw new InvalidCredentialsException();
        }

        Map<String, Object> claims = Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "lastName", user.getLastName(),
                "role", user.getRole(),
                "workArea", user.getWorkArea().getName()
        );

        return new LoginResponseDto(jwtService.generateToken(userDetails, claims));
    }
}
