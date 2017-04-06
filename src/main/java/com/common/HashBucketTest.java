package com.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashBucketTest {

	@Test
	public void testGetBucketNumber() {
		HashBucket hashBucket = new HashBucket(10);//HashBucket instance for max number of buckets = 10
		int bucket = hashBucket.getBucketNumber("Test Input");
		System.out.println(bucket);
		if (!(bucket >=1 && bucket <= 10)){
			fail("Bucket number beyond range");
		}
		
	}

}
