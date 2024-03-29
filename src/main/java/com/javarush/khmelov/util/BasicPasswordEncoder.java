package com.javarush.khmelov.util;

public class BasicPasswordEncoder implements PasswordEncoder{

    @Override
    public String encode(String password) {
        // В данной реализации просто возвращает переданный пароль как есть
        return password;
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        // Сравниваем пароли как строки
        return rawPassword.equals(encodedPassword);
    }

}
