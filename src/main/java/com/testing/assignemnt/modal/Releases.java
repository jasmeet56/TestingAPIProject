package com.testing.assignemnt.modal;

import lombok.Data;

@Data
public class Releases {

    private String organization;
    private Double laborHours;
    private String status;
    private Permissions permissions;
    private ReleaseDates date;



}
