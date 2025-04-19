package com.mga44.complaint_service.complaint.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ComplaintRepository extends MongoRepository<ComplaintEntity, String> {

    Optional<ComplaintEntity> findByProductIdAndUserId(Long productId, Long userId);
}
