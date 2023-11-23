package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.models.Subscription;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ThemeService themeService;
    private final UserService userService;

    public Subscription getSubscriptionById(Long id) {
        return this.subscriptionRepository.findById(id).orElse(null);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public List<Subscription> getAllSubscriptionsFromTheme(Long themeId) {

        Theme theme = themeService.getThemeById(themeId);
        if (theme == null) {
            throw new NotFoundException("the theme id is missing");
        } else {
            return subscriptionRepository.findByTheme_Id(themeId);
        }
    }

    public List<Subscription> getAllSubscriptionsFromUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        if (user.getId() == null) {
            throw new NotFoundException("the user id is missing");
        } else {
            return subscriptionRepository.findByUser_Id(user.getId());
        }
    }

    public void addNewSubscriptionToTheme(Long themeId, User user) {
        Subscription subscription = new Subscription();
        Theme theme = themeService.getThemeById(themeId);
        subscription.setTheme(theme);
        subscription.setUser(user);
        this.subscriptionRepository.save(subscription);
    }

    public void removeSubscriptionByThemeId(Long themeId, User user) {
        List<Subscription> subscriptions = subscriptionRepository.findByUser_IdAndTheme_Id(user.getId(), themeId);
        if (subscriptions.isEmpty()) {
            throw new NotFoundException("Subscription not found for the given user and theme.");
        }
        subscriptionRepository.deleteAll(subscriptions);
    }
}