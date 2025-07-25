package com.cab.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PricingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingServiceApplication.class, args);

		System.out.println("""
            
            ╔════════════════════════════════════════════════════════════════╗
            ║                      PRICING SERVICE STARTED                   ║
            ║                                                                ║
            ║  📝 Handles price calculation                                  ║
            ║  🔗 Communicates with Order service                            ║
            ║                                                                ║
            ║                                                                ║
            ║  Available at: http://localhost:8082/api/pricing               ║
            ╚════════════════════════════════════════════════════════════════╝
            """);
	}
}