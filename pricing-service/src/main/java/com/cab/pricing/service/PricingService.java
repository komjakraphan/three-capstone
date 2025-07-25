package com.cab.pricing.service;

import com.cab.pricing.dto.OrderPricingRequest;
import com.cab.pricing.dto.PricingResponse;
import org.springframework.stereotype.Service;

//Simplified pricing service
// basic price calculation functionality
//microservice communication pattern

@Service
public class PricingService {

    //Basic pricing constants
    private static final double BASE_PRICE_PER_MILE = 1.5;
    private static final double TAX_RATE = .1;

    //takes order details and calculates total price
    //distance/complexity to total price

    public PricingResponse calculateOrderPrice(OrderPricingRequest request) {
        //calculate base price based on distance
        double basePrice = request.getDistance() * BASE_PRICE_PER_MILE;

        //Add complexity factor based on cab type
        double complexityFactor = calculateComplexityFactor(request.getCabType());
        double adjustedPrice = basePrice * complexityFactor;

        //calculate tax
        double tax = adjustedPrice * TAX_RATE;

        //calculate total
        double totalAmount = adjustedPrice + tax;

        return new PricingResponse(totalAmount);
    }

    private double calculateComplexityFactor(String cabType) {
        if ( cabType == null || cabType.trim().isEmpty()) {
            return 1.0;
        }

        //simple complexity multiplier based on cab type
        String lowerType = cabType.toLowerCase();
        double factor = 1.0;

        if(lowerType.contains("expedited") || lowerType.contains("priority")) {
            factor += 0.5;
        }
        if (lowerType.contains("premium") || lowerType.contains("luxury")) {
            factor += 0.5;
        }
        if (lowerType.contains("budget") || lowerType.contains("saver")) {
            factor -= 0.2;
        }
        return Math.max(0.8, Math.min(2.0, factor));
    }
}
