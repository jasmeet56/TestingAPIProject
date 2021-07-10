package com.testing.assignemnt.service;

import com.testing.assignemnt.modal.APIResponse;

public interface OrganizationService {

	public APIResponse getResponse();

	public APIResponse getDataBySortingASC(final String inputParam);

	public APIResponse getDataBySortingDESC(final String inputParam);

}
