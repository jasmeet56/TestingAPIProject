package com.testing.assignemnt.modal;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organizations {

    private String organization;
    private Long release_count;
    private Double total_labor_hours;
    private boolean all_in_production;
    private Set<String> licenses;
    private Set<Integer> most_active_months;
}
