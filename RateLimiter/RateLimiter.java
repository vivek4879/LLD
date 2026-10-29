

class RateLimiter{
    Map<String, Limiter> limiters;
    Limiter defaultLimiter;

    RateLimiter(List<Map<String,Object>> configs, Map<String,String> defaultConfig){
        this.limiters = new HashMap<>();
        this.defaultLimiter = LimiterFactory.create(defaultConfig);
        LimiterFactory factory = new LimiterFactory();

        for(Map<String,Object> config:configs){
            String endpoint  = config.get(endpoint);
            if(endpoint  == null)continue;

            Limiter limiter = factory.create(config);
            limiters.put(endpoint, limiter);
        }
    }

    public RateLimiterResult allow(String clientId, String endpoint){
        Limiter limiter = limiters.getOrDefault(endpoint,defaultLimiter);
        return limiter.allow(clientId);
    }
}

public interface Limiter{
    RateLimiterResult allow(String clientId);
}

class BucketLimiter implements Limiter{
    private final Map<String, Bucket> buckets;
    private final int threshold;
    private final int refreshRatePerSecond;

    BucketLimiter(int threshold, int refreshRatePerSecond){
        this.threshold = threshold;
        this.refreshRatePerSecond = refreshRatePerSecond;
        buckets = new HashMap<>();
    }

    public boolean allow(String clientId){
        
        Bucket bucket = buckets.computeIfAbsent(clientId, h -> new Bucket(threshold, System.currentTimeMillis()));
        
        long curTime = System.currentTimeMillis();
        long timeElapsed = bucket.getLastTime() - curTime;
        double tokensToAdd = (timeElapsed * refreshRatePerSecond)/1000.0
        bucket.count = Math.min(threshold, bucket.getCount()+tokensToAdd);

        if(bucket.count >=1){
            bucket.count -=1;
            int remaining =(int) Math.floor(bucket.count);
            return new RateLimiterResult(true,remaining,null);
        }

        int tokensNeeded = 1 - bucket.count;
        long retryAfterMillis = (long)Math.ceil((tokensNeeded/refreshRatePerSecond)*1000);
        return new RateLimiterResult(false,0,retryAfterMillis);

    }
    private static class Bucket{
        int count;
        long lastRefreshedTime;

        Bucket(int tokens){
            this.count = tokens;
            this.lastRefreshedTime = current time;
        }
        int getCount(){
            return this.count;
        }
        void refresh(int threshold, int rate){
            int timeDiff = cruTime - this.lastRefreshedTime;
            this.count = Math.min(threshold, timeDiff * rate + this.count);
        }
    }
}

class WindowLimiter  implements Limiter{

}

class LimiterFactory{
    public Limiter create(Map<String,String> config){
        String algo = config.get("Algorithm");
        if(algo.equals("bucket")){
            return new bucketLimiter();
        }
        return new defaultLimiter();
    }
}
