package tw.elliot.pub.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.ListenableFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderCtrlTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Test
	public void test() {
		Assert.notNull(kafkaTemplate);

		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("pubsub", "test1");

		log.info("done ? {}", future.isDone());
	}

}