package com.project.event_management_system.service.implementation;

import com.project.event_management_system.model.Complaint;
import com.project.event_management_system.repository.ComplaintRepository;
import com.project.event_management_system.service.ComplaintService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImplementation implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintServiceImplementation(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint createComplaint(Complaint complaint) {
        return this.complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaint() {
        return this.complaintRepository.findAll();
    }

    @Override
    public Complaint getComplaintById(int id) {
        return this.complaintRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteComplaintById(int id) {
        this.complaintRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Complaint updateComplaint(int id, Complaint complaint) {
        Complaint existingComplaint = complaintRepository.findById(id).get();
        if(complaint.getComplaintDescription()!=null) {
            existingComplaint.setComplaintDescription(complaint.getComplaintDescription());
        }
        if(complaint.getApproval()!=null)
            existingComplaint.setApproval(complaint.getApproval());
        return complaintRepository.save(existingComplaint);
    }
}
