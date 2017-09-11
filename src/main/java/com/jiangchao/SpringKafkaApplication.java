package com.jiangchao; /**
 * Created by Administrator on 2017/9/11.
 */
import com.jiangchao.kafka.producer.Sender;
import com.jiangchao.model.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaApplication {
    public static void main(String[] args) {
       ApplicationContext ctx =  SpringApplication.run(SpringKafkaApplication.class, args);
       Order order = new Order("100", 10,"orderID","mid","productName");
       int loop = 100;
       Sender sender = ctx.getBean(Sender.class);
       while (loop-- > 0) {
           sender.send(order);
       }
    }
}
