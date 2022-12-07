# Kafka Stream



## API 概念

Kafka Stream提供兩類功能相近但概念不同的API模式，可視需求或習慣來使用。

### 1. Processer API

如果用過Storm就可以拿Topology的概念來對應，基本上Topology的想法都相近，就像是一份藍圖或流程規劃，資料在規劃裡的節點中流動。

以Storm為例，收資料的叫Spout，而處理資料的叫Bolt，整體仍叫Topology。

![Screen Shot 2022-05-12 at 16.45.21](https://picgo.ap-south-1.linodeobjects.com/20220512/e605003bf483b37a7c8b3826b680c212.png)

而Kafka Stream則統一叫Processor，但可再分為

1. Source Processor：讀取Topic的資料，傳給其他Processor，通常不做任何處理
2. Stream Processor：處理資料，其他的Processor會滙入資料，而本身則會滙出資料
3. Sink Processor：專門接收其他的Processor的資料存入Topic中

![kafka-stream-processing](https://picgo.ap-south-1.linodeobjects.com/20220512/4ffe6c317ac0a2a7514c0e7478c646d2.png)

#### Topology

Topology是整個執行的邏輯描述（藍圖），當規劃到一段落，可透過StreamBuilder產生Topology，再交由KafkaStream啟動，開始執行。

### 2. DSL

#### StreamBuilder

StreamBuilder是Kafka Stream使用的起點，StreamBuilder被指定了Topic後產生一個KStream，或者提供一個KeyValueStore做為Topic資料儲存的KTable。

再以build()產生一個Topology，而此Topology可交由KafkaStreams執行

#### KStream

KStream代表可處理的時序資料，處理後可以再產生其他的KStream繼續進行或者將結果存入KTable

#### KTable

KTable代表可處理的最新資料，可以被過濾，分類到另一個KTable，但也可以做為一個KStream的起點。



#### KafkaStream

整個Stream處理的發動者，交付Topology（藍圖）給KafkaStream後，再以KafkaStream.start()開始，KafkaStream.close()結束。

#### Basic Process

```
StreamsBuilder streamsBuilder;
StreamsConfig streamsConfig;

KStream kstream = streamsBuilder.stream("topic_name");
KTable ktable = kstream.toTable();

Topology topology = streamsBuilder.build();

KafkaStreams ks = new KafkaStreams(topology, streamsConfig);
ks.start();
ks.close();

```

