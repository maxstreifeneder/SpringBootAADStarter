package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @PreAuthorize("hasRole('TestGroup')")
    @RequestMapping("/")
    public String helloWorld(Principal user) {
        return "helloWorld";
    }

    @GetMapping("/test")
    public String index(Model model, OAuth2AuthenticationToken authentication) {
        final OAuth2AuthorizedClient authorizedClient = this.authorizedClientService
                .loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        System.out.println(authorizedClient.getAccessToken().getTokenValue());
        return "index";
    }
}