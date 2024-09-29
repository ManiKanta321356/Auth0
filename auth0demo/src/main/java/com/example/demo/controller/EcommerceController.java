package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.Auth0Service;

@Controller
public class EcommerceController {
	
	@Autowired
	private Auth0Service auth0Service;

    @GetMapping("/home")
    public String home( @AuthenticationPrincipal OidcUser oidcUser) {
    	
    	
            return "home";  
    }
    @GetMapping("/home1")
    public String home1() {
            return "home1";  
    }
    @GetMapping("/home2")
    public String home2() {
            return "home2";  
    }

    @GetMapping("/categories")
    public String categories() {
        return "categories"; 
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";  
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";  
    }

    @GetMapping("/login")
    public String login() {
        return "login";  
    }

 

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
    	if (oidcUser == null) {
            System.out.println("OidcUser is null. User might not be authenticated.");
            return "login"; // Redirect to login or handle appropriately
        }

    	model.addAttribute("profile", oidcUser.getClaims());
    	System.out.println(oidcUser.getClaims());
    	if (oidcUser.getUserInfo() != null) {
            OidcUserInfo userInfo = oidcUser.getUserInfo();
            System.out.println("User Info Claims: " + userInfo.getClaims());
            System.out.println("User Email: " + userInfo.getEmail());  // Example to get the email
            System.out.println("User Name: " + userInfo.getFullName()); 
            System.out.println("User Name: " + userInfo.getPhoneNumber());
            System.out.println(userInfo.getAddress());// Example to get the name
            
        } else {
            System.out.println("No UserInfo available");
        }

        // Print specific claims from OidcIdToken
        OidcIdToken idToken = oidcUser.getIdToken();
        System.out.println("ID Token Claims: " + idToken.getClaims());
        System.out.println("ID Token Subject: " + idToken.getSubject());  // Example to get the subject
        System.out.println("ID Token Issuer: " + idToken.getIssuer()); 
        System.out.println(idToken.getTokenValue());
    	
    	
        return "profile"; // Return the view name
    }

    

    @GetMapping("/fetch-metadata")
    public String fetchMetadata(@AuthenticationPrincipal OidcUser oidcUser) {
    	 
    	 OidcIdToken idToken = oidcUser.getIdToken();
        String userId = idToken.getSubject();        // Replace with the actual user ID
        System.out.println("User Id"+userId);
        // Fetch metadata
        auth0Service.getUserMetadata(userId);
        
        
        return "Metadata fetched successfully";
    }

}

