package com.cab.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // This annotation makes this application a Eureka Server
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);

		System.out.println("""
            
            ╔════════════════════════════════════════════════════════════════╗
            ║                    EUREKA SERVER STARTED                       ║
            ║                                                                ║
            ║  🔍 Service Discovery Dashboard: http://localhost:8761         ║
            ║  📊 All microservices will register here                       ║
            ║  🔄 Provides load balancing and health checking                ║
            ║                                                                ║
            ║  LEARNING TIP: Open the dashboard to see registered services   ║
            ╚════════════════════════════════════════════════════════════════╝
            """);
	}
}
