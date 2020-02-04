package com.physics.mapper;

import com.physics.pojo.Complaint;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintMapper {
    List<Complaint> queryPage();

    void insert(Complaint complaint);
}
