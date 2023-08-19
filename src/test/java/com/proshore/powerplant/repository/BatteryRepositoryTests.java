package com.proshore.powerplant.repository;

import com.proshore.powerplant.entity.Battery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BatteryRepositoryTests {

    @Autowired
    private BatteryRepository batteryRepository;

    @Test
    public void testFindBatteriesInRange() {
        // Create and save batteries with different postcodes
        Battery battery1 = new Battery("1", "Battery 1", "10001", 100L);
        Battery battery2 = new Battery("2", "Battery 2", "20002", 200L);
        Battery battery3 = new Battery("3", "Battery 3", "30003", 150L);
        batteryRepository.saveAll(List.of(battery1, battery2, battery3));

        // Test finding batteries in a postcode range
        List<Battery> batteriesInRange = batteryRepository.findBatteriesInRange("10000", "25000");
        assertEquals(2, batteriesInRange.size());
    }

    @Test
    public void testFindBatteriesInRange_NoBatteriesInBetween() {
        // Create and save batteries with different postcodes
        Battery battery1 = new Battery("1", "Battery 1", "10001", 100L);
        Battery battery2 = new Battery("2", "Battery 2", "20002", 200L);
        batteryRepository.saveAll(List.of(battery1, battery2));

        // Test finding batteries in a postcode range with no batteries in between
        List<Battery> batteriesInRange = batteryRepository.findBatteriesInRange("25000", "30000");
        assertEquals(0, batteriesInRange.size());
    }

    @Test
    public void testFindBatteriesInRange_EmptyRange() {
        // Create and save batteries with different postcodes
        Battery battery1 = new Battery("1", "Battery 1", "10001", 100L);
        Battery battery2 = new Battery("2", "Battery 2", "20002", 200L);
        batteryRepository.saveAll(List.of(battery1, battery2));

        // Test finding batteries in an empty postcode range
        List<Battery> batteriesInRange = batteryRepository.findBatteriesInRange("30000", "20000");
        assertEquals(0, batteriesInRange.size());
    }
}