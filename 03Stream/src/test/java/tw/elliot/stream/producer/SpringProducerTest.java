package tw.elliot.stream.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class SpringProducerTest {
	SpringProducer producer = new SpringProducer(null, null);

	@Test
	public void test() {
		log.info("Generate 10 digital number [{}]", producer.randomFixLengthNumber(10));
	}
}