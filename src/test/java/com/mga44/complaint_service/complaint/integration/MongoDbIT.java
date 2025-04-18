package com.mga44.complaint_service.complaint.integration;

import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import com.mga44.complaint_service.complaint.persistence.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

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
}
