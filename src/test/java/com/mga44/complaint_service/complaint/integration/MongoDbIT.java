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
    void shouldHandleDatabaseOperations() { //TODO: refactor
        var complaint = ComplaintEntity.builder()
                .complaintContent("bad product")
                .build();

        var saved = repository.save(complaint);
        var fromDb = repository.findById(saved.getId());


        assertThat(saved.getId()).isNotNull();
        assertThat(fromDb).isPresent();
        assertThat(fromDb.get().getComplaintContent()).isEqualTo("bad product");

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
        var fromDb = repository.findById(saved2.getId()).orElseThrow();

        assertThat(saved2)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(fromDb);
    }
}
