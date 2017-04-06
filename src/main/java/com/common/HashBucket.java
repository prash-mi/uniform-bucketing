package com.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashBucket {


	private static int buckets;//distribute the input key space in these many buckets
	private static final int MAX_NUMBER_OF_BUCKETS = 9999;

	
	public HashBucket(int buckets){
		if(buckets >MAX_NUMBER_OF_BUCKETS){
			throw new RuntimeException("Max buckets should be under: "+MAX_NUMBER_OF_BUCKETS);
		}
		this.buckets = buckets;
		
	}
	
	//gets the bucket number for the given input
	public int getBucketNumber(String input){
		if (input == null){
			throw new RuntimeException("null input");
		}
		int bucket = -1;
		try {
			int code = getHashCode(input);
			//get the bucket for the current word
			bucket = getBucket(code);
			return bucket;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bucket;
	}

	
	//platform independent code for calculating hashcode
	private static int getHashCode(String key) throws Exception{
		long h = 1;
		String shaDigest = SHA1(key);
		for(char c:shaDigest.toCharArray()){
			h = 31 * h + ((int)c);
		}
		if(h<0){
			h = h* (-1);
		}
		String hString = h +"";


		String subH = hString.substring(hString.length()-4, hString.length());



		if (new Integer(subH) == 0){//take the front 4 numbers in case last 4 are zero
			subH = hString.substring(0, 4);
		}

		return new Integer(subH);
	}

	//put the hashcode in a bucket
	private static int getBucket(double hashCode){

		double bucket_address = (9999/buckets);//number of addresses each bucket will hold, max address is 9999

		double bucket = Math.ceil(hashCode/bucket_address);
		if(bucket > buckets){
			bucket = buckets;
		}
		return (int)bucket;

	}

	//used for SHA1 computation
	private static String convertToHex(byte[] data) { 
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) { 
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do { 
				if ((0 <= halfbyte) && (halfbyte <= 9)) 
					buf.append((char) ('0' + halfbyte));
				else 
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while(two_halfs++ < 1);
		} 
		return buf.toString();
	} 

	//logic for generating sha1 hash
	private static String SHA1(String text) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	} 

}