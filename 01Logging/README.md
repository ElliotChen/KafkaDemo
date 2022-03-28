# Logging

## 如何啟動

### Build docker image

第一步先將SpringBoot的程式包成docker image。

```shell
> mvn spring-boot:build-images
```

### Run docker-compose

在[docker](./docker)目錄下存放了已設定好的`docker-compose.yml`
只要進入該目錄下執行`docker-compose`即可

其中需要變更的是`KAFKA_ADVERTISED_LISTENERS` 中的 `PLAINTEXT_HOST://172.20.10.2:9092`使用的IP，
使用的ip為docker bridge可以連回的host ip，主要是Kafka連接是兩段式的，
即使內部用了`kafka:9092`可以連接第一步，但Kafka會把`PLAINTEXT_HOST`抛回給client，讓client再用這值去連接，
當然如果改用/etc/hosts來設定kafka對應的IP也是可行的。

```shell
> cd docker
> docker-compose up -d
```

