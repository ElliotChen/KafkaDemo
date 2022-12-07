# Pub / Sub

## 說明

拿kafka做為event或是message的傳遞是最常見到的功能，優異的性能，良好的確認機制，實在沒什麼可挑剔的。
早期的版本需要zookeeper，算是比較明顥的困擾，現行的版本可以不用zookeeper，應該可以更加地普及。

### SpringBoot 整合

要整合Kafka到SpringBoot中十分容易，三步驟的設定即可。

#### library

Include spring-kafka

```xml
<dependency>
	<groupId>org.springframework.kafka</groupId>
	<artifactId>spring-kafka</artifactId>
</dependency>
```

#### annotation

使用`@EnableKafka`

```java
@Configuration
@EnableKafka
public class KafkaCofnig {


}
```

#### properties

```yaml
spring:
  kafka:
    bootstrapServers: localhost:9092
    producer:
      retries: 1
    consumer:
      groupId: spring
      enableAutoCommit: true
```

`bootstrapServers`：可以讓producer與consumer共用，當然也可以分別設定。
`consumer.gropuId`：這裡可以做預設，在建立listener時就不用指定。

### Producer

傳送訊息到Kafka很容易，使用KafkaTemplate即可，
指定`topic`與要送達的訊息，
由於JSON字串為主流，不建議傳送檔案以外的物件，使用JSON應可解決大部份的需求。

```java
@Component
@Slf4j
@RequiredArgsConstructor
public class SpringProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Scheduled(fixedDelay = 5000)
	public void sendToKafka() {
		this.kafkaTemplate.send("pubsub", "test1");
	}
}
```

### Consumer

收訊息就更容易了

`@KafkaListener`做了大部份的事，只要指定對應的`topic`即可。

```java
@Component
@Slf4j
public class SpringConsumer {

	@KafkaListener(topics = {"pubsub"})
	public void listen(ConsumerRecord<String, String> recored) {
		log.info("Got a message from kafka: [{}]", recored.value());
	}
}

```