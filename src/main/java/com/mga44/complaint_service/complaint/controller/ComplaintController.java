package com.mga44.complaint_service.complaint.controller;

import com.mga44.complaint.api.ComplaintApi;
import com.mga44.complaint.model.Complaint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ComplaintController implements ComplaintApi {
    @Override
    public ResponseEntity<List<Complaint>> getComplaint() {
        Complaint e1 = new Complaint();
        e1.setId(1L);
        e1.complaintUserId(1L);
        e1.complaintContent("Not working");
        e1.setCountry("PL");
        e1.setCreationDate(LocalDate.ofEpochDay(10));
        e1.setComplaintCounter(1);
        Complaint e2 = new Complaint();
        e1.setId(2L);
        e1.complaintUserId(1L);
        e1.complaintContent("Not working #2");
        e1.setCountry("PL");
        e1.setCreationDate(LocalDate.ofEpochDay(10));
        e1.setComplaintCounter(1);
        return ResponseEntity.ok(List.of(e1, e2)); //TODO implement pagination
    }
}
