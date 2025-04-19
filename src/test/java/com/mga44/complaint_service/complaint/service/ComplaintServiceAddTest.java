package com.mga44.complaint_service.complaint.service;

import com.mga44.complaint.model.ComplaintCreateRequest;
import com.mga44.complaint_service.complaint.mapper.ComplaintMapperImpl;
import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import com.mga44.complaint_service.complaint.persistence.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceAddTest {

    @Mock
    ComplaintRepository complaintRepository;

    @Spy
    ComplaintMapperImpl complaintMapper;

    @InjectMocks
    ComplaintService complaintService;

    @Test
    void shouldSaveComplaint() {
        var complaintCreateRequest = new ComplaintCreateRequest()
                .productId(1L)
                .complaintContent("some message")
                .creationDate(LocalDate.ofEpochDay(100))
                .userId(2L);

        complaintService.createComplaint("5432", complaintCreateRequest);

        var entityArgumentCaptor = ArgumentCaptor.forClass(ComplaintEntity.class);
        verify(complaintRepository).save(entityArgumentCaptor.capture());
        var captured = entityArgumentCaptor.getValue();
        assertThat(captured.getProductId()).isEqualTo(1L);
        assertThat(captured.getComplaintContent()).isEqualTo("some message");
        assertThat(captured.getCreationDate()).isEqualTo(LocalDate.ofEpochDay(100));
        assertThat(captured.getUserId()).isEqualTo(2L);
    }

    @Test
    void shouldUpdateExistingComplaintCounterIfComplaintWithUserAndProductIsAlreadyPresent() {
        var complaintCreateRequest = new ComplaintCreateRequest()
                .productId(1L)
                .userId(2L);
        var complaintEntity = Optional.of(
                ComplaintEntity.builder()
                        .id("3")
                        .complaintCounter(1)
                        .build()
        );
        when(complaintRepository.findByProductIdAndUserId(1L, 2L)).thenReturn(complaintEntity);

        complaintService.createComplaint("5432", complaintCreateRequest);

        var expectedToUpdate = ComplaintEntity.builder().id("3").complaintCounter(2).build();
        verify(complaintRepository).save(expectedToUpdate);
    }

    @Test
    void shouldResolveCountryByIp() {
        fail();
    }
}