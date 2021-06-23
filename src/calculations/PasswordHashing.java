package calculations;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {

    private static final byte[] PasswordSalt = { (byte)0x72, (byte)0xf8, (byte)0x0f, (byte)0xb1 };

    private static String bytesToHex(final byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String Hash(final String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(PasswordSalt);
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }
}
