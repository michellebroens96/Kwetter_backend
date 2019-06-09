package Kwetter.jwt;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public class Constant {

    public static final Key key = MacProvider.generateKey();
}
