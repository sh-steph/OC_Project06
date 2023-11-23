package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.dto.SubscriptionDto;
import com.openclassrooms.mdd.mddapp.dto.ThemeDto;
import com.openclassrooms.mdd.mddapp.dto.UserDto;
import com.openclassrooms.mdd.mddapp.models.Subscription;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.PostRequest;
import com.openclassrooms.mdd.mddapp.payload.request.SubscriptionRequest;
import com.openclassrooms.mdd.mddapp.payload.response.MessageResponse;
import com.openclassrooms.mdd.mddapp.services.SubscriptionService;
import com.openclassrooms.mdd.mddapp.services.ThemeService;
import com.openclassrooms.mdd.mddapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserService userService;
    private final ThemeService themeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Subscription subscription = this.subscriptionService.getSubscriptionById(id);
        if (subscription == null) {
            return ResponseEntity.notFound().build();
        } else {
//            get user
            UserDto userDto = new UserDto();
            userDto.setId(subscription.getUser().getId());
            userDto.setUsername(subscription.getUser().getUsername());
            userDto.setEmail(subscription.getUser().getEmail());
            userDto.setAdmin(subscription.getUser().getAdmin());
            userDto.setCreatedAt(subscription.getUser().getCreatedAt());
            userDto.setUpdatedAt(subscription.getUser().getUpdatedAt());
//            get theme
            ThemeDto themeDto = new ThemeDto();
            themeDto.setId(subscription.getTheme().getId());
            themeDto.setTitle(subscription.getTheme().getTitle());
            themeDto.setDescription(subscription.getTheme().getDescription());
            themeDto.setCreatedAt(subscription.getTheme().getCreatedAt());
            themeDto.setUpdatedAt(subscription.getTheme().getUpdatedAt());
//            get subscription
            SubscriptionDto subscriptionDto = new SubscriptionDto();
            subscriptionDto.setId(subscription.getId());
            subscriptionDto.setTheme(themeDto);
            subscriptionDto.setUser(userDto);
            return ResponseEntity.ok().body(subscriptionDto);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllSubscriptions() {
        List<Subscription> subscriptionList = subscriptionService.getAllSubscriptions();
        List<SubscriptionDto> subscriptionDtos = new ArrayList<>();
        if (subscriptionList == null) {
            return ResponseEntity.notFound().build();
        } else {
            subscriptionList.forEach(subscription -> {
                //            get user
                UserDto userDto = new UserDto();
                userDto.setId(subscription.getUser().getId());
                userDto.setUsername(subscription.getUser().getUsername());
                userDto.setEmail(subscription.getUser().getEmail());
                userDto.setAdmin(subscription.getUser().getAdmin());
                userDto.setCreatedAt(subscription.getUser().getCreatedAt());
                userDto.setUpdatedAt(subscription.getUser().getUpdatedAt());
//            get theme
                ThemeDto themeDto = new ThemeDto();
                themeDto.setId(subscription.getTheme().getId());
                themeDto.setTitle(subscription.getTheme().getTitle());
                themeDto.setDescription(subscription.getTheme().getDescription());
                themeDto.setCreatedAt(subscription.getTheme().getCreatedAt());
                themeDto.setUpdatedAt(subscription.getTheme().getUpdatedAt());
//            get subscription
                SubscriptionDto subscriptionDto = new SubscriptionDto();
                subscriptionDto.setId(subscription.getId());
                subscriptionDto.setTheme(themeDto);
                subscriptionDto.setUser(userDto);
                subscriptionDtos.add(subscriptionDto);
            });
            return ResponseEntity.ok(new SubscriptionRequest.SubscriptionResponse(subscriptionDtos));
        }
    }

    @GetMapping("/theme/{themeId}")
    public ResponseEntity<?> getAllSubscriptionsFromTheme(@PathVariable("themeId") Long themeId) {
        List<Subscription> subscriptionList = subscriptionService.getAllSubscriptionsFromTheme(themeId);
        List<SubscriptionDto> subscriptionDtos = new ArrayList<>();
        if (subscriptionList == null) {
            return ResponseEntity.notFound().build();
        } else {
            subscriptionList.forEach(subscription -> {
                //            get user
                UserDto userDto = new UserDto();
                userDto.setId(subscription.getUser().getId());
                userDto.setUsername(subscription.getUser().getUsername());
                userDto.setEmail(subscription.getUser().getEmail());
                userDto.setAdmin(subscription.getUser().getAdmin());
                userDto.setCreatedAt(subscription.getUser().getCreatedAt());
                userDto.setUpdatedAt(subscription.getUser().getUpdatedAt());
//            get theme
                ThemeDto themeDto = new ThemeDto();
                themeDto.setId(subscription.getTheme().getId());
                themeDto.setTitle(subscription.getTheme().getTitle());
                themeDto.setDescription(subscription.getTheme().getDescription());
                themeDto.setCreatedAt(subscription.getTheme().getCreatedAt());
                themeDto.setUpdatedAt(subscription.getTheme().getUpdatedAt());
//            get subscription
                SubscriptionDto subscriptionDto = new SubscriptionDto();
                subscriptionDto.setId(subscription.getId());
                subscriptionDto.setTheme(themeDto);
                subscriptionDto.setUser(userDto);
                subscriptionDtos.add(subscriptionDto);
            });
            return ResponseEntity.ok(new SubscriptionRequest.SubscriptionResponse(subscriptionDtos));
        }

    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllSubscriptionsFromUser() {
        List<Subscription> subscriptionList = subscriptionService.getAllSubscriptionsFromUserId();
        List<SubscriptionDto> subscriptionDtos = new ArrayList<>();
        if (subscriptionList == null) {
            return ResponseEntity.notFound().build();
        } else {
            subscriptionList.forEach(subscription -> {
                //            get user
                UserDto userDto = new UserDto();
                userDto.setId(subscription.getUser().getId());
                userDto.setUsername(subscription.getUser().getUsername());
                userDto.setEmail(subscription.getUser().getEmail());
                userDto.setAdmin(subscription.getUser().getAdmin());
                userDto.setCreatedAt(subscription.getUser().getCreatedAt());
                userDto.setUpdatedAt(subscription.getUser().getUpdatedAt());
//            get theme
                ThemeDto themeDto = new ThemeDto();
                themeDto.setId(subscription.getTheme().getId());
                themeDto.setTitle(subscription.getTheme().getTitle());
                themeDto.setDescription(subscription.getTheme().getDescription());
                themeDto.setCreatedAt(subscription.getTheme().getCreatedAt());
                themeDto.setUpdatedAt(subscription.getTheme().getUpdatedAt());
//            get subscription
                SubscriptionDto subscriptionDto = new SubscriptionDto();
                subscriptionDto.setId(subscription.getId());
                subscriptionDto.setTheme(themeDto);
                subscriptionDto.setUser(userDto);
                subscriptionDtos.add(subscriptionDto);
            });
            return ResponseEntity.ok(new SubscriptionRequest.SubscriptionResponse(subscriptionDtos));
        }

    }

    @PostMapping("/theme/{themeId}")
    public ResponseEntity<?> addNewSubscriptionToTheme(@PathVariable("themeId") Long themeId) {
        List<Subscription> subscriptionList = subscriptionService.getAllSubscriptionsFromUserId();
        Optional<Subscription>  optionalSubscription = Optional.empty();
        if(subscriptionList != null) {
            optionalSubscription = subscriptionList.stream().filter(subscription ->
                Objects.equals(subscription.getTheme().getId(), themeId)
            ).findFirst();
        }
        if (optionalSubscription.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You are already subscribed to this theme"));
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByEmail(authentication.getName());
            this.subscriptionService.addNewSubscriptionToTheme(themeId, user);
            return ResponseEntity.ok().body(new PostRequest.MessageResponse("The subscription was successfully created"));
        }
    }

    @DeleteMapping("/theme/{themeId}")
    public ResponseEntity<?> removeSubscriptionByThemeId(@PathVariable("themeId") Long themeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
            this.subscriptionService.removeSubscriptionByThemeId(themeId, user);
            return ResponseEntity.ok().body(new SubscriptionRequest.MessageResponse("The unsubscription was successfully done"));
        }
}
