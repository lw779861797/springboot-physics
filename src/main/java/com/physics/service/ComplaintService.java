package com.physics.service;

import com.physics.pojo.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> queryPage();

    void insert(Complaint complaint);
}
