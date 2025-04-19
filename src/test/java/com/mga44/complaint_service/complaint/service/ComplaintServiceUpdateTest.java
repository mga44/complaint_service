package com.mga44.complaint_service.complaint.service;

import com.mga44.complaint.model.ComplaintUpdateRequest;
import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import com.mga44.complaint_service.complaint.persistence.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceUpdateTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @InjectMocks
    ComplaintService complaintService;

    @Test
    void shouldUpdateComplaintOnSuccess() {
        var updateRequest = new ComplaintUpdateRequest().complaintContent("New content");
        var complaintEntity = Optional.of(ComplaintEntity.builder()
                .complaintContent("Old content")
                .complaintCounter(4)
                .build()
        );
        when(complaintRepository.findById("1")).thenReturn(complaintEntity);

        complaintService.updateComplaint("1", updateRequest);

        var updated = ComplaintEntity.builder()
                .complaintContent("New content")
                .complaintCounter(5)
                .build();
        verify(complaintRepository).save(updated);
    }

    @Test
    void shouldThrowExceptionOnFailedUpdate() {
        var updateRequest = new ComplaintUpdateRequest().complaintContent("New content");
        when(complaintRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> complaintService.updateComplaint("1", updateRequest));
        verify(complaintRepository, never()).save(any());
    }
}