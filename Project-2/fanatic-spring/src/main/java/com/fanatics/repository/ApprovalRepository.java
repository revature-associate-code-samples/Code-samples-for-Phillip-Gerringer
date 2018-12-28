package com.fanatics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fanatics.models.Approval;

@Repository
public interface ApprovalRepository extends JpaRepository <Approval,Integer>{

	List<Approval> findById(@Param("userid") Integer id);

}
