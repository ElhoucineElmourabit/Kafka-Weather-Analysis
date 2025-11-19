package org.example.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Admin {
    public void createTopics() {

        Properties config = new Properties();
        config.put("bootstrap.servers", "localhost:9092");

        try (final AdminClient client = AdminClient.create(config)) {

            final List<NewTopic> topics = new ArrayList<>();

            topics.add(new NewTopic("weather-data", 3, (short) 1));
            topics.add(new NewTopic("station-averages",1, (short)1));

            client.createTopics(topics);

        }
    }
}
