package org.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.WeatherRecord;

import java.util.Properties;

public class WeatherProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {

            WeatherRecord weatherRecord = WeatherRecord.builder()
                    .station("New York")
                    .temperature(25.00)
                    .humidity(60.0)
                    .build();

            String message = weatherRecord.getStation() + "," + weatherRecord.getTemperature() + "," + weatherRecord.getHumidity();

            producer.send(new ProducerRecord<>("weather-data", null, message), (metadata, exception) -> {
                if (exception != null) exception.printStackTrace();
                else System.out.printf("Produced record [%s] to topic %s partition %d offset %d%n",
                        message, metadata.topic(), metadata.partition(), metadata.offset());
            });
        }
    }
}
