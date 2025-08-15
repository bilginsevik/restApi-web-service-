package com.example.demo.controller;

import com.example.demo.dto.BranchRequest;
import com.example.demo.dto.BranchResponse;
import com.example.demo.service.IBranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private final IBranchService branchService;

    public BranchController(IBranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<BranchResponse> addBranch(@RequestBody BranchRequest request) {
        BranchResponse response = branchService.addBranch(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BranchResponse>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponse> getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchResponse> updateBranch(@PathVariable Long id, @RequestBody BranchRequest request) {
        BranchResponse updated = branchService.updateBranch(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
