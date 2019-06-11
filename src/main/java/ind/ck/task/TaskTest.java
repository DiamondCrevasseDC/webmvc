package ind.ck.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

@Component("taskJob")
public class TaskTest {

    @Autowired
    private JedisPool jedisPool;

//  @Scheduled(fixedRate = 60000)　　表示每60秒执行一次
    @Scheduled(cron = "0/5 * * * * ?") //从0秒开始，每5秒执行一次
    public void setTime(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("date", "2019-01-01");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
