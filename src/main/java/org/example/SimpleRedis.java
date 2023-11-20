package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Angga [ADL.ANGGAP@xl.co.id]
 * created at ${DATE} ${TIME}
 */
public class SimpleRedis {

    public static void main(String[] args) {
        System.out.printf("HOST: %s, PORT: %s%n", args[0], args[1]);
        JedisPool pool = new JedisPool(args[0], Integer.parseInt(args[1]), args[2], args[3]);

        try (Jedis jedis = pool.getResource()) {
            switch (args[4]) {
                case "SET":
                    setCache(jedis, args[5], args[6]);
                    break;
                case "GET":
                    System.out.println(getCache(jedis, args[5]));
                    break;
                case "DEL":
                    deleteCache(jedis, args[5]);
                    break;
                default:
                    System.out.println("Current supported commands are GET, SET and DEL.");
            }
        } catch (Exception e) {
            System.out.println("ERROR, caused by: " + e.getMessage());
        }

    }

    private static void setCache(Jedis jedis, String key, String value) {
        jedis.set(key, value);
    }

    private static String getCache(Jedis jedis, String key) {
        return jedis.get(key);
    }

    private static void deleteCache(Jedis jedis, String key) {
        jedis.del(key);
    }
}