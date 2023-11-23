import { Theme } from "./theme.interface";
import { User } from "./user.interface";

export interface Subscription {
    id: number;
    user: User;
    theme: Theme;
}

export interface SubscriptionList {
    subscriptions: Subscription[];
}

export interface SubscriptionRequest {
    message: String;
}

export interface SubscriptionResponse {
    message: String;
}
