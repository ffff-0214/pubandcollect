package edu.qingtai.pubandcollect.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {
    private RabbitTemplate rabbitTemplate;
    private String topicExchange;
    private final String[] keys = {"publish.infer", "publish.impression", "publish.interview"};

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${topicExchangeName}") String topicExchange){
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    public void sendInfer(final Infer infer){
        rabbitTemplate.convertAndSend(topicExchange, keys[0], infer);
    }

    public void sendImpression(final Impression impression){
        rabbitTemplate.convertAndSend(topicExchange, keys[1], impression);
    }

    public void sendInterview(final Interview interview){
        rabbitTemplate.convertAndSend(topicExchange, keys[2], interview);
    }
}
