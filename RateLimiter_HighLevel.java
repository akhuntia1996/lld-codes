/*
Rate Limiter
-------------
You are asked to design a Rate Limiter for a large-scale backend system.

Problem Statement
Design a rate limiter that:
Limits requests per user ID.

Supports different rate limits:
Free users → 100 requests per minute
Premium users → 1000 requests per minute

Should work in:
Multi-threaded environment
Distributed environment (multiple app servers)

Should be extensible to support:
IP-based rate limiting
API-key-based rate limiting

Should have minimal latency impact

Token bucket strategy

Entities --
Client
RateLimiter
RateLimiterStrategy
    APIKeyStrategy
    UserBasedStrategy
    IpBasedStrategy
Token

*/

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

class Client {
    public static void main(String[] args) {

        RequestContext requestContext;

        User u1;
        ExecutorService executorService = Executor.newFixedThreadPool(10);
        executorService.submit(() -> u1.request(requestBody, requestContext));
    }
}

class User {
    public void request(String requestBody, RequestContext requestContext) {
        RateLimiter rateLimiter = new UserRateLimter();
        boolean isAllowed = rateLimiter.shouldAllow(requestContext);
        if(isAllowed)
            System.out.println("Processing ..."); // will be different for user, api
        else{
            System.out.println("Not Allowed");
            System.exit(0);
        }
    }
}

class RequestContext {
    String userId;
    String apiKey;
    String ip;
    String userType; // Free or Paid
}

abstract class RateLimiter {

    Map<String, Token> tokenMap = new ConcurrentHashMap<>();

    public abstract String getKey(RequestContext requestContext);
    public  boolean shouldAllow(RequestContext requestContext){
        String key = this.getKey(requestContext);

        Token token = null;
        if(requestContext.getUserType == User.FREE)
            token = new Token(100);
        else
            token = new Token(1000);

        tokenMap.computeIfAbsent(key, k -> token);
        return token.isAllowed();
    }
}

class APIKeyRateLimiter extends RateLimiter {
    public String getKey(RequestContext requestContext) {
        return "API : " + requestContext.getUserId();
    }
}

class UserRateLimter extends RateLimiter {
    public String getKey(RequestContext requestContext) {
        return "User : " + requestContext.getUserId();
    }
}

class Token {
    int capacity = 5;
    LocalDateTime lastRefill;
    int refillPerSec;
    int requestLeft;

    public Token(int capacity){
        this.capacity = capacity;
        refillPerSec = 100;
        requestLeft = this.capacity;
    }
    
    public boolean isAllowed() {

        // Refill if needed
        Duration duration = Duration.between(LocalDateTime.now(), lastRefill);
        long seconds = duration.toSeconds();

        // this will give us no. of request that can be process from now on
        double tokenToAdd = seconds * refillPerSec;

        if(tokenToAdd > 0) {
            lastRefill = LocalDateTime.now();
            requestLeft = Math.min(requestLeft, capacity + tokenToAdd);
        }

        if(requestLeft > 0){
            requestLeft--;
            return true;
        }

        return false;
    }
}

/*

Concurreny --
Redis Cluster
Distributed Lock
Atomic vars
ConcurrentHashMap and computeIfAbsent

Avoid Memory Leak --
EXPIRE Key 60 - Redis - cleanup after 60 days
TTL Cron Job

Scalability --

Bottlenecks:
Redis becoming hotspot
Single key high traffic (celebrity user)
Network latency
Large memory footprint

Solutions:
Redis Cluster (sharding)
Use hash tags for key distribution
Use local + distributed hybrid limiter
Use async pipeline
Monitor with metrics


*/
