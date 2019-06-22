package com.lin.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PwUtil {

	@Value(value = "${randomDefaultStr}")
	private String randomDefaultStr;

	private static final Logger log = LoggerFactory.getLogger(PwUtil.class);

	public String encrypt(String rawPassword) {
		String encryptedPw = null;
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			String temp = (rawPassword + "830507" + randomDefaultStr);
			byte[] encodedHash = sha256.digest(temp.getBytes(Charset.defaultCharset()));
			Encoder base64 = Base64.getEncoder();
			encryptedPw = new String(base64.encode(encodedHash), "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			log.error("failed to encrypt the password:{}", e);
		} catch (UnsupportedEncodingException e) {
			log.error("failed to encrypt the password:{}", e);
		}
		return encryptedPw;
	}

}
