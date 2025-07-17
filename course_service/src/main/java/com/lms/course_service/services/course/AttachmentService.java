package com.lms.course_service.services.course;

import com.lms.commonlib.exceptions.EntityNotFound;
import com.lms.course_service.dtos.request.attachment.AttachmentRequestDTO;
import com.lms.course_service.entities.course.attachment.Attachment;
import com.lms.course_service.repositories.attachment.AttachmentRepository;
import com.lms.course_service.utils.AttachmentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
//    private final AttachmentUtils attachmentUtils;
    public Attachment createAttachment(AttachmentRequestDTO attachmentRequestDTO) {
        Attachment attachment = new Attachment();
        attachment.setTitle(attachmentRequestDTO.getTitle());
        attachment.setDescription(attachmentRequestDTO.getDescription());
        attachment.setAttachmentEntityType(attachmentRequestDTO.getAttachmentEntityType());
        attachment.setUrl(attachmentRequestDTO.getUrl());
        attachment.setUploadedBy(attachmentRequestDTO.getUploadedBy());
        attachment.setAvailableForStudents(attachmentRequestDTO.isAvailableForStudents());
        attachment.setEntityType(attachmentRequestDTO.getEntityType());

        return attachmentRepository.save(attachment);
    }
    public Page<Attachment> getAllAttachments(Pageable pageable, Specification<Attachment> specification) {
        return attachmentRepository.findAll(specification,pageable);
    }
    public Attachment getAttachmentById(Long id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Attachment", "id",String.valueOf(id)));
    }
    public List<Attachment> getAttachmentsByEntityTypeAndAttachedTo(String entityType, Long attachedTo) {
        return attachmentRepository.findByEntityTypeAndAttachedTo(entityType, attachedTo);
    }
    public Attachment updateAttachment(Long id, Attachment updatedAttachment) {
        Attachment existing = getAttachmentById(id);
if(existing == null)
    throw new EntityNotFound("Attachment", "id", String.valueOf(id));
        existing.setTitle(updatedAttachment.getTitle());
        existing.setDescription(updatedAttachment.getDescription());
        existing.setUrl(updatedAttachment.getUrl());
        existing.setType(updatedAttachment.getType());
        existing.setEntityType(updatedAttachment.getEntityType());
        existing.setAttachmentEntityType(updatedAttachment.getAttachmentEntityType());
        existing.setAttachedTo(updatedAttachment.getAttachedTo());
        existing.setAvailableForStudents(updatedAttachment.isAvailableForStudents());
        existing.setUploadedBy(updatedAttachment.getUploadedBy());

        return attachmentRepository.save(existing);
    }
    public void deleteAttachment(Long id) {
        Attachment existing = getAttachmentById(id);
        if(existing == null)
            throw new EntityNotFound("Attachment", "id", String.valueOf(id));
        attachmentRepository.delete(existing);
    }
}