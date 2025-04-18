package com.mga44.complaint_service.complaint.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
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
public class ComplaintEntity {

    @Id
    private final String id;
    private final Long productId;

    private final String complaintContent;

    private final LocalDate creationDate;

    private final Long userId;

    private final String country;

    private final Integer complaintCounter;
}
