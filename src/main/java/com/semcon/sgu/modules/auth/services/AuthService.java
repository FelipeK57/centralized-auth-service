package com.semcon.sgu.modules.auth.services;

import com.semcon.sgu.modules.auth.dtos.LoginRequestDto;
import com.semcon.sgu.modules.auth.dtos.LoginResponseDto;
import com.semcon.sgu.modules.auth.exceptions.ForbiddenRoleException;
import com.semcon.sgu.modules.auth.exceptions.InvalidCredentialsException;
import com.semcon.sgu.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_admin"));

        if (!isAdmin) {
            throw new ForbiddenRoleException();
        }

        return new LoginResponseDto(jwtService.generateToken(userDetails));
    }
}
