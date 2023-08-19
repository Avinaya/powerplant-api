package com.proshore.powerplant.service;

import com.proshore.powerplant.dto.BatteryRequest;
import com.proshore.powerplant.dto.BatteryResponse;
import com.proshore.powerplant.dto.PostCodeRangeResponse;
import com.proshore.powerplant.entity.Battery;
import com.proshore.powerplant.exception.ResourceNotFoundException;
import com.proshore.powerplant.repository.BatteryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BatteryServiceImplTests {

    @Mock
    private BatteryRepository batteryRepository;

    @InjectMocks
    private BatteryServiceImpl batteryService;

    @Test
    public void testSaveBattery() {
        // Prepare mock data
        List<BatteryRequest> batteryRequests = List.of(
                new BatteryRequest("Battery 1", "10001", 100L),
                new BatteryRequest("Battery 2", "20002", 150L)
        );
        List<Battery> savedBatteries = List.of(
                new Battery(UUID.randomUUID().toString(), "Battery 1", "10001", 100L),
                new Battery(UUID.randomUUID().toString(), "Battery 2", "20002", 150L)
        );

        // Mock repository behavior
        when(batteryRepository.saveAll(any())).thenReturn(savedBatteries);

        // Call the service method
        List<BatteryResponse> batteryResponses = batteryService.saveBattery(batteryRequests);

        // Verify the repository method was called with the correct argument
        verify(batteryRepository).saveAll(anyList());

        // Assertions
        assertEquals(savedBatteries.size(), batteryResponses.size());
        assertEquals(savedBatteries.get(0).getName(), batteryResponses.get(0).getName());
        assertEquals(savedBatteries.get(1).getCapacity(), batteryResponses.get(1).getCapacity());
    }

    @Test
    public void testGetBatteriesInRange() {
        // Prepare mock data
        String startPostcode = "10000";
        String endPostcode = "20000";
        List<Battery> batteries = List.of(
                new Battery(UUID.randomUUID().toString(), "Battery 1", "15000", 100L),
                new Battery(UUID.randomUUID().toString(), "Battery 2", "18000", 200L)
        );

        // Mock repository behavior
        when(batteryRepository.findBatteriesInRange(startPostcode, endPostcode)).thenReturn(batteries);

        // Call the service method
        PostCodeRangeResponse response = batteryService.getBatteries(startPostcode, endPostcode);

        // Assertions
        assertEquals(batteries.size(), response.getNames().size());
        assertEquals(300L, response.getTotalCapacity());
        assertEquals(150.0, response.getAverageCapacity());
    }

    @Test
    public void testGetBatteriesInRange_NoBatteriesFound() {
        // Prepare mock data
        String startPostcode = "30000";
        String endPostcode = "40000";

        // Mock repository behavior
        when(batteryRepository.findBatteriesInRange(startPostcode, endPostcode)).thenReturn(Collections.emptyList());

        // Call the service method and expect a ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> batteryService.getBatteries(startPostcode, endPostcode));
    }
}
