package tw.elliot.stream.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import tw.elliot.stream.domain.Purchase;

import java.util.Random;

/**
 * @author elliot
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SpringProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	private final ObjectMapper mapper;

	@Scheduled(fixedDelay = 5000)
	public void sendToKafka() throws JsonProcessingException {
		Purchase purchase = new Purchase();
		String memberId = String.valueOf(this.randomFixLengthNumber(10));
		purchase.setMemberId(memberId);
		purchase.setItemId("SKU_"+String.valueOf(this.randomFixLengthNumber(6)));
		purchase.setAmount(this.randomAmount());
		this.kafkaTemplate.send("streamsin", memberId, mapper.writeValueAsString(purchase));
	}

	public long randomFixLengthNumber(int length) {
		return (long) ((long) Math.floor(Math.random() * 9*Math.pow(10, length - 1)) +  Math.pow(10, length - 1));
	}
	public long random10Number() {
		return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
	}

	public long random6Number() {
		return (long) Math.floor(Math.random() * 900_000L) + 100_000L;
	}

	public double randomAmount() {
		return Math.random() * 100;
	}
}
