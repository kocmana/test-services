package at.kocmana.testservices.ecommerceservice.config;

import at.kocmana.testservice.commons.delay.EnableDelaySimulation;
import at.kocmana.testservice.commons.failure.EnableFailureSimulation;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDelaySimulation
@EnableFailureSimulation
public class SimulationConfiguration {

}
