package com.semcon.sgu.modules.systems.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HexFormat;

@Component
public class ApiKeyGenerator {

    private static final SecureRandom random = new SecureRandom();

    public String generateApiKey(){
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);

        return HexFormat.of().formatHex(bytes);
    }
}
