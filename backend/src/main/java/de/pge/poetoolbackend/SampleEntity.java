package de.pge.poetoolbackend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Builder
@Document("sample")
@AllArgsConstructor
public class SampleEntity {

    @Id
    private final String id;
    private final String owner;
    private final Double value;
}
