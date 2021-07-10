package com.testing.assignemnt.modal;

import static com.testing.assignemnt.constant.Constants.PROD;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class APIResponseMapper {

	private List<Releases> releases;

	public Map<String, Long> getReleaseCount() {
		Map<String, Long> releaseCount = this.getReleases().stream()
				.collect(Collectors.groupingBy(Releases::getOrganization, Collectors.counting()));
		return releaseCount;
	}

	public Map<String, Boolean> isProduction() {
		Map<String, Boolean> all_in_production = this.getReleases().stream().collect(Collectors.toMap(
				Releases::getOrganization, e -> e.getStatus().equals(PROD), (existing, replacement) -> existing));

		return all_in_production;
	}

	public Map<String, Double> getTotalLabourHours() {
		Map<String, Double> totalLabourHours = this.releases.stream().collect(
				Collectors.groupingBy(Releases::getOrganization, Collectors.summingDouble(Releases::getLaborHours)));
		return totalLabourHours;
	}

	public Map<String, Set<String>> getLicenses() {
		Map<String, Set<String>> permissions = this.getReleases().stream()
				.collect(Collectors.groupingBy(Releases::getOrganization, mapping(e -> e.getPermissions().getLicenses()
						.stream().map(Licenses::getName).collect(Collectors.joining(",")), toSet())));
		return permissions;
	}

	public Map<String, Set<Integer>> getReleaseDates() {
		Map<String, Set<Integer>> releaseDates = this.getReleases().stream()
				.collect(Collectors.groupingBy(Releases::getOrganization,
						mapping(e -> LocalDate.parse(e.getDate().getCreated()).getMonth().getValue(), toSet())));

		return releaseDates;
	}
}
