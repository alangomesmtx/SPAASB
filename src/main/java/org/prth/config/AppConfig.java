package org.prth.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.prth.controller.EmployeeController;

public class AppConfig extends ResourceConfig {
    public AppConfig() {
        register(EmployeeController.class);
        packages("org.prth"); // Optional: to scan all packages under org.prth
    }
}