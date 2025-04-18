package com.mga44.complaint_service.complaint.mapper;

import com.mga44.complaint.model.Complaint;
import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ComplaintMapper {
    Complaint toComplaint(ComplaintEntity entity);
}
