package com.example.demo.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@Service
//public class Auth0Service {
//
//    private final WebClient webClient;
//
//    public Auth0Service(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://dev-m0pu2uuglyzgrxx5.us.auth0.com").build();
//    }
//
//    public void getUserMetadata(String accessToken, String userId) {
//        String userMetadataUrl ="/api/v2/users/"+userId;
//        System.out.println(userMetadataUrl);
//        Mono<String> response = webClient.get()
//            .uri(userMetadataUrl)
//            .header("Authorization", "Bearer " + accessToken)
//            .retrieve()
//            .bodyToMono(String.class);
//
//        // Print the user metadata
//        response.subscribe(userMetadata -> System.out.println("User Metadata: " + userMetadata));
//    }
//}

//
//package com.example.demo.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.util.UriComponentsBuilder;
//import reactor.core.publisher.Mono;
//
//@Service
//public class Auth0Service {
//
//    private final WebClient webClient;
//
//    public Auth0Service(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://dev-m0pu2uuglyzgrxx5.us.auth0.com").build();
//    }
//
//    public void getUserMetadata(String accessToken, String userId) {
//        // Use UriComponentsBuilder to construct the URL safely without encoding the pipe (|)
//        String userMetadataUrl = UriComponentsBuilder.fromUriString("/api/v2/users/{userId}")
//                .build(userId)  // Substitute {userId} with actual value
//                .toString();
//
//        System.out.println("Constructed URL: " + userMetadataUrl);
//
//        Mono<String> response = webClient.get()
//            .uri(userMetadataUrl)  // Use constructed URL here
//            .header("Authorization", "Bearer " + accessToken)  // Add Bearer token in Authorization header
//            .retrieve()
//            .bodyToMono(String.class);
//
//        // Print the user metadata in console
//        response.subscribe(
//            userMetadata -> System.out.println("User Metadata: " + userMetadata),
//            error -> System.err.println("Error: " + error.getMessage())  // Handle any errors here
//        );
//    }
//}


//package com.example.demo.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.util.UriComponentsBuilder;
//import reactor.core.publisher.Mono;
//
//@Service
//public class Auth0Service {
//
//    private final WebClient webClient;
//
//    public Auth0Service(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://dev-m0pu2uuglyzgrxx5.us.auth0.com").build();
//    }
//
//    public void getUserMetadata(String accessToken, String userId) {
//        // Construct URL without double encoding the pipe character '|'
//        String userMetadataUrl = UriComponentsBuilder.fromPath("/api/v2/users/{userId}")
//                .buildAndExpand(userId)  // Properly substitute {userId}
//                .toUriString();
//
//        System.out.println("Constructed URL: " + userMetadataUrl);
//
//        Mono<String> response = webClient.get()
//            .uri(userMetadataUrl)
//            .header("Authorization", "Bearer " + accessToken)  // Bearer token must have correct scopes
//            .retrieve()
//            .bodyToMono(String.class);
//
//        // Print the user metadata or handle errors
//        response.subscribe(
//            userMetadata -> System.out.println("User Metadata: " + userMetadata),
//            error -> System.err.println("Error: " + error.getMessage())  // Error handling
//        );
//    }
//}


//package com.example.demo.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//
//@Service
//public class Auth0Service {
//
//    private final WebClient webClient;
//
//    public Auth0Service(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://dev-m0pu2uuglyzgrxx5.us.auth0.com").build();
//    }
//
//    public void getUserMetadata(String accessToken, String userId) {
//        // Construct the URL manually
//        String userMetadataUrl = "https://dev-m0pu2uuglyzgrxx5.us.auth0.com/api/v2/users/" + userId;
//
//        System.out.println("Constructed URL: " + userMetadataUrl);
//
//        Mono<String> response = webClient.get()
//            .uri(URI.create(userMetadataUrl))  // Pass the URI directly to prevent encoding
//            .header("Authorization", "Bearer " + accessToken)  // Bearer token must have correct scopes
//            .retrieve()
//            .bodyToMono(String.class);
//
//        // Print the user metadata or handle errors
//        response.subscribe(
//            userMetadata -> System.out.println("User Metadata: " + userMetadata),
//            error -> System.err.println("Error: " + error.getMessage())  // Error handling
//        );
//    }
//}

//package com.example.demo.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//@Service
//public class Auth0Service {
//
//    private final WebClient webClient;
//
//    public Auth0Service(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://dev-m0pu2uuglyzgrxx5.us.auth0.com").build();
//    }
//
//    public void getUserMetadata(String accessToken, String userId) {
//        try {
//            // Encode the userId to make sure the | character is properly encoded as %7C
//            String encodedUserId = URLEncoder.encode(userId, StandardCharsets.UTF_8.toString());
//
//            // Construct the full URL using the encoded userId
//            String userMetadataUrl = "https://dev-m0pu2uuglyzgrxx5.us.auth0.com/api/v2/users/" + encodedUserId;
//
//            System.out.println("Constructed URL: " + userMetadataUrl);
//
//            Mono<String> response = webClient.get()
//                .uri(userMetadataUrl)  // Use the encoded URL here
//                .header("Authorization", "Bearer " + accessToken)  // Bearer token must have correct scopes
//                .retrieve()
//                .bodyToMono(String.class);
//
//            // Print the user metadata or handle errors
//            response.subscribe(
//                userMetadata -> System.out.println("User Metadata: " + userMetadata),
//                error -> System.err.println("Error: " + error.getMessage())  // Error handling
//            );
//        } catch (Exception e) {
//            System.err.println("Error encoding userId: " + e.getMessage());
//        }
//    }
//}


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class Auth0Service {

    private final WebClient webClient;

    public Auth0Service(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://dev-m0pu2uuglyzgrxx5.us.auth0.com").build();
    }

    public void getUserMetadata(String userId) {
        try {
            // Encode the userId to make sure the | character is properly encoded as %7C
            String encodedUserId = URLEncoder.encode(userId, StandardCharsets.UTF_8.toString());

            // First, get the access token for the Auth0 Management API
            Mono<String> accessTokenMono = getAccessToken();

            accessTokenMono.subscribe(accessToken -> {
                String userMetadataUrl = "https://dev-m0pu2uuglyzgrxx5.us.auth0.com/api/v2/users/" + encodedUserId;
                System.out.println("Constructed URL: " + userMetadataUrl);

                Mono<String> response = webClient.get()
                        .uri(userMetadataUrl)
                        .header("Authorization", "Bearer " + accessToken)
                        .retrieve()
                        .bodyToMono(String.class);

                response.subscribe(
                        userMetadata -> System.out.println("User Metadata: " + userMetadata),
                        error -> System.err.println("Error: " + error.getMessage())  // Error handling
                );
            });
        } catch (Exception e) {
            System.err.println("Error encoding userId: " + e.getMessage());
        }
    }

    private Mono<String> getAccessToken() {
        return webClient.post()
                .uri("https://dev-m0pu2uuglyzgrxx5.us.auth0.com/oauth/token")
                .bodyValue(Map.of(
                        "client_id", "R2cFlMGSpRXmdCaeui4d0xZgdafgeMLm",  // Use your M2M application's client_id
                        "client_secret", "93MJTama7sITdhX4QqQ1FLFvR66rpf8YYBkM8-Ufwk2KvpRAdC5fU2sIcunpiMC7",  // Use your M2M application's client_secret
                        "audience", "https://dev-m0pu2uuglyzgrxx5.us.auth0.com/api/v2/",
                        "grant_type", "client_credentials"
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("access_token"));
    }
}
