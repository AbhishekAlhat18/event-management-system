package com.project.event_management_system.service;

import com.project.event_management_system.model.Complaint;

import java.util.List;

public interface ComplaintService {

    public Complaint createComplaint(Complaint complaint);

    public List<Complaint> getAllComplaint();

    public Complaint getComplaintById(int id);

    public void deleteComplaintById(int id);

    public Complaint updateComplaint(int id, Complaint complaint);
}
