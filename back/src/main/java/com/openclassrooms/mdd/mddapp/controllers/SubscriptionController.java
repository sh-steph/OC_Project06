package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.models.Subscription;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.PostRequest;
import com.openclassrooms.mdd.mddapp.payload.request.SubscriptionRequest;
import com.openclassrooms.mdd.mddapp.services.SubscriptionService;
import com.openclassrooms.mdd.mddapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Subscription subscription = this.subscriptionService.getSubscriptionById(id);
        if (subscription == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(subscription);
        }
    }

    @GetMapping("/theme/{themeId}")
    public ResponseEntity<?> getAllSubscribersFromTheme(@PathVariable("themeId") Long themeId) {
        subscriptionService.getAllSubscribersFromTheme(themeId);
        return ResponseEntity.ok(new SubscriptionRequest.SubscriptionResponse(subscriptionService.getAllSubscribersFromTheme(themeId)));
    }

    @PostMapping("/theme/{themeId}")
    public ResponseEntity<?> addNewSubscriptionToTheme(@PathVariable("themeId") Long themeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        this.subscriptionService.addNewSubscriptionToTheme(themeId, user);
        return ResponseEntity.ok().body(new PostRequest.MessageResponse("The subscription was successfully created"));
    }

}
