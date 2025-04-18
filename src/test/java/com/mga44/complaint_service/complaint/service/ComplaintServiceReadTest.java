package com.mga44.complaint_service.complaint.service;

import com.mga44.complaint.model.Complaint;
import com.mga44.complaint_service.complaint.mapper.ComplaintMapperImpl;
import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import com.mga44.complaint_service.complaint.persistence.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceReadTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @Spy
    private ComplaintMapperImpl complaintMapper;
    @InjectMocks
    ComplaintService complaintService;

    @Test
    void shouldReturnComplaintOnSuccessfulRead() {
        when(complaintRepository.findById("abc"))
                .thenReturn(Optional.of(ComplaintEntity.builder().id("abc").build()));

        var retrievedComplaint = complaintService.findComplaint("abc").orElseThrow();

        var expected = new Complaint();
        expected.id("abc");
        assertThat(retrievedComplaint).isEqualTo(expected);
    }

    @Test
    void shouldReturnEmptyOptionalIfDataNotFound() {
        when(complaintRepository.findById("abc")).thenReturn(Optional.empty());

        var retrievedComplaint = complaintService.findComplaint("abc");

        assertThat(retrievedComplaint).isEmpty();
    }
}