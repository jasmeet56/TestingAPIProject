package com.testing.assignemnt.controller;

import static com.testing.assignemnt.constant.Constants.CONTENT_DISPO;
import static com.testing.assignemnt.constant.Constants.CONTENT_TYPE;
import static com.testing.assignemnt.constant.Constants.CSV_HEADER;
import static com.testing.assignemnt.constant.Constants.DATE_FORMAT;
import static com.testing.assignemnt.constant.Constants.NAME;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.testing.assignemnt.modal.APIResponse;
import com.testing.assignemnt.modal.Organizations;
import com.testing.assignemnt.service.OrganizationService;

@RestController
@RequestMapping("/api/v1/organization/data")
public class OrganizationController {

	@Autowired
	private OrganizationService orgService;

	@GetMapping("/")
	public ResponseEntity<APIResponse> getData() {
		APIResponse aPIResponse = orgService.getResponse();
		return ResponseEntity.ok(aPIResponse);

	}

	@GetMapping("/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType(CONTENT_TYPE);
		DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
		String currentDateTime = dateFormatter.format(new Date());
		String contentDisposition = CONTENT_DISPO;
		String headerValue = "attachment; filename=organization_" + currentDateTime + ".csv";
		response.setHeader(contentDisposition, headerValue);
		APIResponse result = orgService.getResponse();
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvWriter.writeHeader(CSV_HEADER);
		for (Organizations organizations : result.getOrganizations()) {
			csvWriter.write(organizations, NAME);
		}
		csvWriter.close();
	}

	@GetMapping("/sortByAsc")
	public ResponseEntity<APIResponse> getDataBySorting(@RequestParam(name = "inputParam") String inputParam) {
		return ResponseEntity.ok(orgService.getDataBySortingASC(inputParam));

	}

	@GetMapping("/sortByDesc")
	public ResponseEntity<APIResponse> getDataBySortingDesc(@RequestParam(name = "inputParam") String inputParam) {
		return ResponseEntity.ok(orgService.getDataBySortingDESC(inputParam));

	}

}
