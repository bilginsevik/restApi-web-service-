package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchRequest {
    private String name;
    private String address;
    private Boolean status;
    private Long restaurantId;
}
