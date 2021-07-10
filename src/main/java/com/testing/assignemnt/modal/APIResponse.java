package com.testing.assignemnt.modal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class APIResponse {
    private List<Organizations> organizations;
}
