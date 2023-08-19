package com.nobroker.api.service;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class StripeService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    public PaymentIntent createPaymentIntent(long amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()

                .setAmount(amount)
                .setCurrency("usd")
                .build();

        return PaymentIntent.create(params);
    }
}
