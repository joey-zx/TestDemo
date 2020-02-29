package com.joey.community.community.controller;

import com.joey.community.community.dto.AccessTokenDTO;
import com.joey.community.community.dto.GithubUser;
import com.joey.community.community.mapper.UserMapper;
import com.joey.community.community.model.User;
import com.joey.community.community.provider.GithubProvider;
import com.joey.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class OauthController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${GitHub.client.id}")
    private String clientId;

    @Value("${GitHub.redirect.uri}")
    private String redirectURI;

    @Value("${GitHub.client.secret}")
    private String clientSecret;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse httpServletResponse) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectURI);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        User realUser = userService.findUserByAccountId(githubUser.getId().toString());

        if(githubUser != null && realUser == null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.insert(user);
            httpServletResponse.addCookie(new Cookie("token",token));
            return "redirect:/";
        } else {
            realUser.setAvatarUrl(githubUser.getAvatarUrl());
            realUser.setName(githubUser.getName());
            realUser.setGmtModified(System.currentTimeMillis());
            userService.update(realUser);
            String token = realUser.getToken();
            httpServletResponse.addCookie(new Cookie("token",token));
            return "redirect:/";
        }
    }

}
