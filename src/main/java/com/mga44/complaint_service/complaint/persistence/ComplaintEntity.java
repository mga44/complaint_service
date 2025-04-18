package com.mga44.complaint_service.complaint.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@Document("complaints")
public class ComplaintEntity {

    @Id
    private final String id;
    private final Long productId;

    private final String complaintContent;

    private final LocalDate creationDate = null;

    private final Long userId;

    private final String country;

    private final Integer complaintCounter;
}
