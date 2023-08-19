package com.proshore.powerplant;

import com.proshore.powerplant.controller.BatteryController;
import com.proshore.powerplant.repository.BatteryRepository;
import com.proshore.powerplant.service.BatteryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PowerplantApplicationTests {

    @Autowired
    private BatteryController batteryController;

    @Autowired
    private BatteryService batteryService;

    @Autowired
    private BatteryRepository batteryRepository;

    @Test
    void contextLoads() {
        // Check that the context loads successfully
    }

    @Test
    void batteryControllerNotNull() {
        assertNotNull(batteryController, "BatteryController is not null");
    }

    @Test
    void batteryServiceNotNull() {
        assertNotNull(batteryService, "BatteryService is not null");
    }

    @Test
    void batteryRepositoryNotNull() {
        assertNotNull(batteryRepository, "BatteryRepository is not null");
    }
}
