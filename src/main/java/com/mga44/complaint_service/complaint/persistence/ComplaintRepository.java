package com.mga44.complaint_service.complaint.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplaintRepository extends MongoRepository<ComplaintEntity, String> {
}
