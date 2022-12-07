package tw.elliot.stream.topology;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;
import tw.elliot.stream.domain.Purchase;

import java.util.Arrays;

@Component
@Slf4j
public class PurchaseProcessor {
	private static final Serde<String> STRING_SERDE = Serdes.String();

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	void buildPipeline(StreamsBuilder streamsBuilder) {
		JsonSerde<Purchase> jsonSerde = new JsonSerde<>(Purchase.class, mapper);
		KStream<String, Purchase> messageStream = streamsBuilder
				.stream("streamsin", Consumed.with(STRING_SERDE, jsonSerde));
		KafkaStreams streams;
		messageStream.foreach((key, purchase) -> {
			log.info("Got Key [{}] and Value [{}]", key, purchase);
			log.info("Got Member [{}]", purchase.getMemberId());
		});

	}
}
