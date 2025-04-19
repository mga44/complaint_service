package com.mga44.complaint_service.complaint.service;

import com.mga44.complaint_service.complaint.persistence.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceDeleteTest {
    @Mock
    private ComplaintRepository complaintRepository;
    @InjectMocks
    ComplaintService complaintService;

    @Test
    void shouldDeleteEntityFromRepository() {

        complaintService.deleteComplaint("33");

        verify(complaintRepository).deleteById("33");
    }
}