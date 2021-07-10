package com.testing.assignemnt.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testing.assignemnt.modal.APIResponse;
import com.testing.assignemnt.modal.APIResponseMapper;
import com.testing.assignemnt.modal.Organizations;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${organization.api.url}")
	private String organizationUrl;
	
	private static final String ORG = "organization";
	private static final String REL_COUNT = "releaseCount";
	private static final String LABOR_HR = "labourHours";
	

	@Override
	public APIResponse getResponse() {
		APIResponseMapper result = restTemplate.getForObject(organizationUrl, APIResponseMapper.class);
		APIResponse aPIResponse = new APIResponse();
		List<Organizations> organizationsList = new ArrayList<>();
		Map<String, Set<String>> licenses = result.getLicenses();
		Map<String, Long> releaseCount = result.getReleaseCount();
		Map<String, Double> totalLabourHours = result.getTotalLabourHours();
		Map<String, Set<Integer>> most_active_months = result.getReleaseDates();
		Map<String, Boolean> production = result.isProduction();

		releaseCount.entrySet().stream().forEach(e -> {
			Organizations organizations = new Organizations();
			organizations.setOrganization(e.getKey());
			organizations.setRelease_count(releaseCount.get(e.getKey()));
			organizations.setTotal_labor_hours(totalLabourHours.get(e.getKey()));
			organizations.setAll_in_production(production.get(e.getKey()));
			organizations.setLicenses(licenses.get(e.getKey()));
			organizations.setMost_active_months(most_active_months.get(e.getKey()));

			organizationsList.add(organizations);
		});

		aPIResponse.setOrganizations(organizationsList);
		return aPIResponse;
	}

	@Override
	public APIResponse getDataBySortingASC(final String inputParam) {
		List<Organizations> organizations = new ArrayList<>();
		APIResponse aPIResponse = new APIResponse();
		if (inputParam.equals(ORG)) {
			organizations = getResponse().getOrganizations().stream()
					.sorted(Comparator.comparing(Organizations::getOrganization)).collect(Collectors.toList());
		} else if (inputParam.equals(REL_COUNT)) {
			organizations = getResponse().getOrganizations().stream()
					.sorted(Comparator.comparing(Organizations::getRelease_count)).collect(Collectors.toList());
		} else if (inputParam.equals(LABOR_HR)) {
			organizations = getResponse().getOrganizations().stream()
					.sorted(Comparator.comparing(Organizations::getTotal_labor_hours)).collect(Collectors.toList());
		}

		aPIResponse.setOrganizations(organizations);

		return aPIResponse;
	}

	@Override
	public APIResponse getDataBySortingDESC(final String inputParam) {
		APIResponse aPIResponse = new APIResponse();
		List<Organizations> organizations = new ArrayList<>();
		if (inputParam.equals(ORG)) {
			organizations = getResponse().getOrganizations().stream()
					.sorted(Comparator.comparing(Organizations::getOrganization).reversed())
					.collect(Collectors.toList());
		} else if (inputParam.equals(REL_COUNT)) {
			organizations = getResponse().getOrganizations().stream()
					.sorted(Comparator.comparing(Organizations::getRelease_count).reversed())
					.collect(Collectors.toList());
		} else if (inputParam.equals(LABOR_HR)) {
			organizations = getResponse().getOrganizations().stream()
					.sorted(Comparator.comparing(Organizations::getTotal_labor_hours).reversed())
					.collect(Collectors.toList());
		}

		aPIResponse.setOrganizations(organizations);

		return aPIResponse;
	}

}
