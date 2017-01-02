package jp.gr.java_conf.hungrywalker.helper;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class CipherHelper
{
    public String encrypt(String source)
    {
        try
        {
            byte[] bytes = Base64.getUrlEncoder().encode(cipher(true, source.getBytes()));
            return new String(bytes);

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String decypt(String encryptString)
    {
        try
        {
            byte[] bytes = cipher(false, Base64.getUrlDecoder().decode(encryptString));
            return new String(bytes);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static byte[] cipher(boolean isEncrypt, byte[] source)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException
    {
        String secretKey = "AemxBmyEd0nkgdBf";
        String algorithm = "AES";

        byte[] secretKeyBytes = secretKey.getBytes();

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        if (isEncrypt)
        {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        } else
        {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        }

        return cipher.doFinal(source);
    }
}
