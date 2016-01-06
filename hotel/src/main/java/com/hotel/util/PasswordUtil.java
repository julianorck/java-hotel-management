package com.hotel.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

	public static String convertStringToMd5(final String value) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			final byte[] valueMD5 = mDigest.digest(value.getBytes("UTF-8"));
			final StringBuffer sb = new StringBuffer();
			for (final byte b : valueMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
