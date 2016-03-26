package com.kevin.gank.utils;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by jianfei.zhang on 2015/12/19.
 */
public class PasswordUtils {

    /**
     * 密码加密
     *
     * @param pwd 密码明文
     * @return 密文
     */
    public static String encrypt(String pwd) {
        String key = "44bc64daf3c37a7a";
        return encrypt(pwd, key);
    }

    /**
     * 加密算法
     *
     * @param pwd 明文密码
     * @param key 密钥
     * @return 返回密文
     */
    private static String encrypt(String pwd, String key) {
        key = MD5.getMD5(key);
        int x = 0;
        int len = pwd.length();
        int l = key.length();
        String ch = "";
        for (int i = 0; i < len; i++) {
            if (x == l) {
                x = 0;
            }
            ch += key.toCharArray()[x];
            x++;
        }
        String str = "";
        for (int i = 0; i < len; i++) {
            str += (char) (((int) pwd.toCharArray()[i] + (int) ch.toCharArray()[i]) % 128);
        }
//        return Base64.encode(str.getBytes());
        return "";
    }

    public static class SSLSocketFactoryEx extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public SSLSocketFactoryEx(KeyStore truststore, char[] arry) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(truststore);
            KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            localKeyManagerFactory.init(truststore, arry);
            KeyManager[] arrayOfKeyManager = localKeyManagerFactory.getKeyManagers();
            TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslContext.init(arrayOfKeyManager, new TrustManager[]{tm}, new SecureRandom());
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }
    }

}
