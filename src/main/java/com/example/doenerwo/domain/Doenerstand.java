package com.example.doenerwo.domain;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Doenerstaende")
public class Doenerstand {

    @Id
    private String id;

    private String name;

    private String longitude;

    private String latitude;
}
