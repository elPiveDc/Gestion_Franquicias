package com.MiNegocio.configuracioncentral.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    public static String hashear(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    public static boolean verificar(String passwordPlano, String passwordHash) {
        return BCrypt.checkpw(passwordPlano, passwordHash);
    }
}
