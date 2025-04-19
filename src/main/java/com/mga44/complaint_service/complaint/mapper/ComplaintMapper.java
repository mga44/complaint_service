package com.mga44.complaint_service.complaint.mapper;

import com.mga44.complaint.model.Complaint;
import com.mga44.complaint.model.ComplaintCreateRequest;
import com.mga44.complaint_service.complaint.persistence.ComplaintEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {
    Complaint toComplaint(ComplaintEntity entity);


    @Mapping(target="id", expression = "java(null)")
    @Mapping(target="complaintCounter", expression = "java(1)")
    ComplaintEntity toComplaintEntity(ComplaintCreateRequest complaintCreateRequest, String country);
}
