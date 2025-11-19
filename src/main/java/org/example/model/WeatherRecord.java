package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class WeatherRecord {
    String station;
    Double temperature;
    Double humidity;
}