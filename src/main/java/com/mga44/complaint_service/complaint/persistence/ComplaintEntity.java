package com.mga44.complaint_service.complaint.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("complaints")
@CompoundIndex(name = "user_product_idx", def = "{'userId': 1, 'productId': 1}", unique = true)
@Builder
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ComplaintEntity {

    @Id
    private final String id;
    private final Long productId;

    @Setter
    private String complaintContent;

    private final LocalDate creationDate;

    private final Long userId;

    private final String country;

    @Setter
    private Integer complaintCounter;
}
