package tw.elliot.pub.ctrl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCtrl {

	private KafkaTemplate<String, String> kafkaTemplate;
	public String order() {
		return "success";
	}
}
