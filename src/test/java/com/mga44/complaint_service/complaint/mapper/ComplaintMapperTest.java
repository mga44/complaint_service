package com.mga44.complaint_service.complaint.mapper;

import com.mga44.complaint.model.Complaint;
import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ComplaintMapperTest {

    ComplaintMapper mapper = Mappers.getMapper(ComplaintMapper.class);

    @Test
    void shouldMapComplaintEntityToComplaint() {
        var complaint = ComplaintEntity.builder()
                .id("someId")
                .productId(1L)
                .complaintContent("some message")
                .creationDate(LocalDate.ofEpochDay(100))
                .userId(2L)
                .country("PL")
                .complaintCounter(3)
                .build();

        var complaintDto = mapper.toComplaint(complaint);

        var expectedDto = new Complaint()
                .id("someId")
                .productId(1L)
                .complaintContent("some message")
                .creationDate(LocalDate.ofEpochDay(100))
                .userId(2L)
                .country("PL")
                .complaintCounter(3);
        assertThat(complaintDto).isEqualTo(expectedDto);
    }

}