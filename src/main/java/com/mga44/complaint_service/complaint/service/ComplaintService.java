package com.mga44.complaint_service.complaint.service;

import com.mga44.complaint.model.Complaint;
import com.mga44.complaint.model.ComplaintCreateRequest;
import com.mga44.complaint.model.ComplaintUpdateRequest;
import com.mga44.complaint_service.complaint.mapper.ComplaintMapper;
import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import com.mga44.complaint_service.complaint.persistence.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintMapper complaintMapper;

    @Autowired
    private CountryService countryService;

    public Optional<Complaint> findComplaint(String id) {
        Optional<ComplaintEntity> possibleComplaint = complaintRepository.findById(id);
        return possibleComplaint.map(complaintMapper::toComplaint);
    }

    public void updateComplaint(String id, ComplaintUpdateRequest complaintUpdateRequest) {
        var possibleComplaint = complaintRepository.findById(id);
        if (possibleComplaint.isEmpty()) {
            throw new IllegalArgumentException("Not found complaint with id [%s]".formatted(id));
        }

        var complaint = possibleComplaint.get();
        if (complaint.getComplaintContent().equals(complaintUpdateRequest.getComplaintContent())) {
            return;
        }

        complaint.setComplaintContent(complaintUpdateRequest.getComplaintContent());
        complaint.setComplaintCounter(complaint.getComplaintCounter() + 1);
        complaintRepository.save(complaint);
    }

    public void deleteComplaint(String id) {
        complaintRepository.deleteById(id);
    }

    public List<Complaint> findAllComplaints() {
        return complaintRepository.findAll().stream().map(complaintMapper::toComplaint).toList();
    }

    public void createComplaint(String xForwardedForHeader, ComplaintCreateRequest complaintCreateRequest) {
        var possibleExistingComplaint = complaintRepository.findByProductIdAndUserId(
                complaintCreateRequest.getProductId(),
                complaintCreateRequest.getUserId()
        );

        if (possibleExistingComplaint.isPresent()) {
            var complaint = possibleExistingComplaint.get();
            complaint.setComplaintCounter(complaint.getComplaintCounter() + 1);
            complaintRepository.save(complaint);
            return;
        }

        var originalIp = xForwardedForHeader.split(",")[0].trim();
        var possibleCountry = countryService.resolveByIp(originalIp);
        var complaintEntity = complaintMapper.toComplaintEntity(complaintCreateRequest, possibleCountry.orElse(null));
        complaintRepository.save(complaintEntity);
    }
}
