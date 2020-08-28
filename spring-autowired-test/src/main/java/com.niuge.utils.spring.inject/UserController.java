package com.niuge.utils.spring.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.FormContentFilter;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

  @Autowired
  CDPlayers cdPlayers;

  @GetMapping(value = "/getMobileNumber")
  public String getMobileNumber() {
    FormContentFilter formContentFilter;
    cdPlayers.printInfo();

    return "18600000000";

  }
}
