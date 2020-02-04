package com.physics.service.impl;

import com.physics.mapper.ComplaintMapper;
import com.physics.pojo.Complaint;
import com.physics.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    ComplaintMapper complaintMapper;
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Complaint> queryPage() {
        return complaintMapper.queryPage();
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(Complaint complaint) {
        complaintMapper.insert(complaint);
    }
}
