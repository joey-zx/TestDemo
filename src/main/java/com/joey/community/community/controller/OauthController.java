package com.joey.community.community.controller;

import com.joey.community.community.dto.AccessTokenDTO;
import com.joey.community.community.dto.GithubUser;
import com.joey.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OauthController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${GitHub.client.id}")
    private String clientId;

    @Value("${GitHub.redirect.uri}")
    private String redirectURI;

    @Value("${GitHub.client.secret}")
    private String clientSecret;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectURI);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSecret);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getGithubUser(token);
        System.out.println(user.getName());
        return "index";
    }

}
