package com.openclassrooms.mdd.mddapp.payload.request;

import com.openclassrooms.mdd.mddapp.models.Subscription;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.models.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {

    private User user;
    private Theme theme;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageResponse {
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubscriptionResponse {
        private Iterable<Subscription> subscriptions;
    }
}

