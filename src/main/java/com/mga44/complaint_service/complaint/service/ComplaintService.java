package com.mga44.complaint_service.complaint.service;

import com.mga44.complaint.model.Complaint;
import com.mga44.complaint.model.ComplaintUpdateRequest;
import com.mga44.complaint_service.complaint.mapper.ComplaintMapper;
import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import com.mga44.complaint_service.complaint.persistence.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintMapper complaintMapper;

    public Optional<Complaint> findComplaint(String id) {
        Optional<ComplaintEntity> possibleComplaint = complaintRepository.findById(id);
        return possibleComplaint.map(complaintMapper::toComplaint);
    }

    public void updateComplaint(String id, ComplaintUpdateRequest complaintUpdateRequest) {
        var possibleComplaint = complaintRepository.findById(id);
        if (possibleComplaint.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"); //TODO: change this to better exception, translated by handler
        }

        var complaint = possibleComplaint.get();
        if (complaint.getComplaintContent().equals(complaintUpdateRequest.getComplaintContent())) {
            return;
        }

        complaint.setComplaintContent(complaintUpdateRequest.getComplaintContent());
        complaint.setComplaintCounter(complaint.getComplaintCounter() + 1);
        complaintRepository.save(complaint);
    }
}
