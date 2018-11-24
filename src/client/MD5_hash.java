package client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class MD5_hash
{
	MD5_hash(){}
	String testMD5(String str) throws NoSuchAlgorithmException
	{
		String MD5 = "";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < byteData.length; i++)
		{
			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		}
		MD5 = sb.toString();
		
		return MD5;
	}
}