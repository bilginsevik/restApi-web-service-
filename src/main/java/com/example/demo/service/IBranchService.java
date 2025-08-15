package com.example.demo.service;

import com.example.demo.dto.BranchRequest;
import com.example.demo.dto.BranchResponse;

import java.util.List;
import java.util.Optional;

public interface IBranchService {

    BranchResponse addBranch(BranchRequest request);

    List<BranchResponse> getAllBranches();

    Optional<BranchResponse> getBranchById(Long id);

    BranchResponse updateBranch(Long id, BranchRequest request);

    void deleteBranch(Long id);
}
