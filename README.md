# uniform-bucketing (hashing)
This API returns a bucket number (1 &lt;= bucketNumber &lt;= MaxBucketNumber) for any given String input. It does uniform distribution of input strings in all the buckets while keeping the bucket number of a given input fixed. One use case for this utility could to map a given input to it hash slot, it can help us solve horizontal distribution problem


## sample code
```java
  	    HashBucket hashBucket = new HashBucket(10);//HashBucket instance for max number of buckets = 10
		int bucket = hashBucket.getBucketNumber("Test Input");
		System.out.println(bucket);
```

