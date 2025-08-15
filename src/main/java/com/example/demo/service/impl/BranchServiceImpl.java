package com.example.demo.service.impl;

import com.example.demo.dto.BranchRequest;
import com.example.demo.dto.BranchResponse;
import com.example.demo.model.Branch;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.IBranchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements IBranchService {

    private final BranchRepository branchRepository;
    private final RestaurantRepository restaurantRepository;

    public BranchServiceImpl(BranchRepository branchRepository, RestaurantRepository restaurantRepository) {
        this.branchRepository = branchRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public BranchResponse addBranch(BranchRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restoran bulunamadı"));

        Branch branch = new Branch();
        branch.setName(request.getName());
        branch.setAddress(request.getAddress());
        branch.setStatus(request.getStatus());
        branch.setRestaurant(restaurant);

        Branch saved = branchRepository.save(branch);
        return convertToResponse(saved);
    }

    @Override
    public List<BranchResponse> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branches.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BranchResponse> getBranchById(Long id) {
        return branchRepository.findById(id).map(this::convertToResponse);
    }

    @Override
    public BranchResponse updateBranch(Long id, BranchRequest request) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şube bulunamadı"));

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restoran bulunamadı"));

        branch.setName(request.getName());
        branch.setAddress(request.getAddress());
        branch.setStatus(request.getStatus());
        branch.setRestaurant(restaurant);

        Branch updated = branchRepository.save(branch);
        return convertToResponse(updated);
    }

    @Override
    public void deleteBranch(Long id) {
        if (!branchRepository.existsById(id)) {
            throw new RuntimeException("Şube bulunamadı");
        }
        branchRepository.deleteById(id);
    }

    private BranchResponse convertToResponse(Branch branch) {
        String statusText = (branch.getStatus() != null && branch.getStatus()) ? "Aktif" : "Pasif";
        String restaurantName = branch.getRestaurant() != null ? branch.getRestaurant().getRestaurantName() : null;

        return new BranchResponse(
                branch.getId(),
                branch.getName(),
                branch.getAddress(),
                statusText,
                restaurantName
        );
    }
}
