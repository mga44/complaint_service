package com.mga44.complaint_service.complaint.integration;

import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import com.mga44.complaint_service.complaint.persistence.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class MongoDbIT {

    @Autowired
    private ComplaintRepository repository;

    @Test
    void shouldDeleteDatabaseEntity() { //TODO: refactor
        var complaint = ComplaintEntity.builder()
                .complaintContent("bad product")
                .build();

        var saved = repository.save(complaint);
        repository.deleteById(saved.getId());

        assertThat(repository.findById(saved.getId())).isEmpty();
    }

    @Test
    void shouldStoreComplaintEntity() {
        var complaint = ComplaintEntity.builder()
                .productId(1L)
                .complaintContent("some message")
                .creationDate(LocalDate.ofEpochDay(100))
                .userId(2L)
                .country("PL")
                .complaintCounter(1)
                .build();

        var saved = repository.save(complaint);
        var fromDb = repository.findById(saved.getId()).orElseThrow();

        assertThat(saved)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(fromDb);
    }

    @Test
    void shouldEnsureUniqueness() {
        var complaint = ComplaintEntity.builder()
                .productId(1L)
                .userId(2L)
                .build();

        var complaint2 = ComplaintEntity.builder()
                .productId(1L)
                .userId(2L)
                .build();

        var saved = repository.save(complaint);
        var saved2 = repository.save(complaint2);

        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void shouldUpdateEntity() {
        var complaint = ComplaintEntity.builder().build();

        var saved = repository.save(complaint);

        saved.setComplaintContent("New content");
        repository.save(saved);

        assertThat(repository.findById(saved.getId()).orElseThrow())
                .extracting(ComplaintEntity::getComplaintContent)
                .isEqualTo("New content");
    }
}
