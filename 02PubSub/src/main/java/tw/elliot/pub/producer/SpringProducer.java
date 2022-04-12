package tw.elliot.pub.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author elliot
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SpringProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Scheduled(fixedDelay = 5000)
	public void sendToKafka() {
		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("pubsub", "test1");
	}
}
