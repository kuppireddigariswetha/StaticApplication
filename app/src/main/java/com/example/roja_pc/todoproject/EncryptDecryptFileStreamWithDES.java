package com.example.roja_pc.todoproject;

import android.app.Activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by pc on 4/25/2017.
 */

public class EncryptDecryptFileStreamWithDES {

   private static Cipher ecipher;
    private static Cipher dcipher;
    private static byte[] iv = {
            (byte)0xB2, (byte)0x12, (byte)0xD5, (byte)0xB2,
            (byte)0x44, (byte)0x21, (byte)0xC3, (byte)0xC3
    };
    /*public static void main(String[] args) {
        try {

            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
           // encrypt(new FileInputStream("/storage/emulated/0/ZIP/file.zip"), new FileOutputStream("/storage/emulated/0/ZIP/file.zip"));
           // decrypt(new FileInputStream("encrypted.dat"), new FileOutputStream("cleartext-reversed.txt"));
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found:" + e.getMessage());
            return;
        }
        catch (InvalidAlgorithmParameterException e) {
            System.out.println("Invalid Alogorithm Parameter:" + e.getMessage());
            return;
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
            return;
        }
        catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
            return;
        }
        catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
            return;
        }

    }*/

    public static void encrypt(InputStream is, OutputStream os,Cipher xx) {
        try {

            byte[] buf = new byte[1024];
            // bytes at this stream are first encoded
            os = new CipherOutputStream(os, xx);
            // read in the clear text and write to out to encrypt
            int numRead = 0;
            while ((numRead = is.read(buf)) >= 0) {
                os.write(buf, 0, numRead);
            }
            // close all streams

            os.close();

        }
        catch (IOException e) {
            System.out.println("I/O Error:" + e.getMessage());
        }
    }
    /*private static void decrypt(InputStream is, OutputStream os) {
        try {
            byte[] buf = new byte[1024];
            // bytes read from stream will be decrypted
            CipherInputStream cis = new CipherInputStream(is, dcipher);
            // read in the decrypted bytes and write the clear text to out
            int numRead = 0;
            while ((numRead = cis.read(buf)) >= 0) {
                os.write(buf, 0, numRead);
            }
            // close all streams

            cis.close();
            is.close();
            os.close();

        }
        catch (IOException e) {
            System.out.println("I/O Error:" + e.getMessage());
        }
    }*/

}
