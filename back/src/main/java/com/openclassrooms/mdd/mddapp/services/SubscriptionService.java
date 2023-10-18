package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.models.Subscription;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ThemeService themeService;

    public Subscription getSubscriptionById(Long id) {
        return this.subscriptionRepository.findById(id).orElse(null);
    }

    public List<Subscription> getAllSubscribersFromTheme(Long themeId) {

        Theme theme = themeService.getThemeById(themeId);
        if (theme == null) {
            throw new NotFoundException("the theme id is missing");
        } else {
            return subscriptionRepository.findByTheme_Id(themeId);
        }
    }

    public void addNewSubscriptionToTheme(Long themeId, User user) {
        Subscription subscription = new Subscription();
        Theme theme = themeService.getThemeById(themeId);
        subscription.setTheme(theme);
        subscription.setUser(user);
        this.subscriptionRepository.save(subscription);
    }
}