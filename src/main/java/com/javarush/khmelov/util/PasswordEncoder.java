package com.javarush.khmelov.util;

public interface PasswordEncoder {

    String encode(String password);

    boolean matches(String rawPassword, String encodedPassword);
}
