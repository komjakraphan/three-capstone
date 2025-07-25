package com.cab.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);

		System.out.println("""
            
            ╔════════════════════════════════════════════════════════════════╗
            ║                      API GATEWAY STARTED                       ║
            ║                                                                ║
            ║  🚪 Single Entry Point: http://localhost:8080                  ║
            ║  🔀 Routes requests to appropriate microservices               ║
            ║  ⚖️  Load balances across service instances                     ║
            ║  🛡️  Handles cross-cutting concerns (auth, logging)            ║
            ║                                                                ║
            ║  LEARNING TIP: All client requests go through this gateway     ║
            ╚════════════════════════════════════════════════════════════════╝
            """);
	}
}