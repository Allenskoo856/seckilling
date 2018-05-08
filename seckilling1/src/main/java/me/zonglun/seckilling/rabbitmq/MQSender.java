package me.zonglun.seckilling.rabbitmq;

import me.zonglun.seckilling.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Administrator
 * @create 2018-05-04 19:34
 */
@Service
public class MQSender {

    private static Logger log = LoggerFactory.getLogger(MQReciver.class);

    @Autowired
    AmqpTemplate amqpTemplate;

	public void sendMiaoshaMessage(MiaoshaMessage mm) {
		String msg = RedisService.beanToString(mm);
		log.info("send message:"+msg);
		amqpTemplate.convertAndSend(MQconfig.QUEUE, msg);
	}

}
