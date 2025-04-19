package com.mga44.complaint_service.complaint.controller;

import com.mga44.complaint.api.ComplaintApi;
import com.mga44.complaint.model.Complaint;
import com.mga44.complaint.model.ComplaintCreateRequest;
import com.mga44.complaint.model.ComplaintUpdateRequest;
import com.mga44.complaint_service.complaint.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ComplaintController implements ComplaintApi {

    @Autowired
    private ComplaintService complaintService;

    @Override
    public ResponseEntity<Void> addComplaint(String xForwardedFor, ComplaintCreateRequest complaintCreateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteComplaint(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Complaint>> getComplaint() {
        Complaint e1 = new Complaint();
        e1.complaintContent("Not working");
        e1.setCountry("PL");
        e1.setCreationDate(LocalDate.ofEpochDay(10));
        e1.setComplaintCounter(1);
        Complaint e2 = new Complaint();
        e1.complaintContent("Not working #2");
        e1.setCountry("PL");
        e1.setCreationDate(LocalDate.ofEpochDay(10));
        e1.setComplaintCounter(1);
        return ResponseEntity.ok(List.of(e1, e2)); //TODO implement pagination
    }

    @Override
    public ResponseEntity<Complaint> getSingleComplaint(String id) {
        Complaint complaint = complaintService.findComplaint(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        return ResponseEntity.ok(complaint);
    }

    @Override
    public ResponseEntity<Void> updateComplaint(String id, ComplaintUpdateRequest complaintUpdateRequest) {
        try {
            complaintService.updateComplaint(id, complaintUpdateRequest);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }
}
