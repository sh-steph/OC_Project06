package com.openclassrooms.mdd.mddapp.repositories;

import com.openclassrooms.mdd.mddapp.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByTheme_Id(Long themeId);
    List<Subscription> findByUser_Id(Long userId);
    List<Subscription> findByUser_IdAndTheme_Id(Long userId, Long themeId);

}
