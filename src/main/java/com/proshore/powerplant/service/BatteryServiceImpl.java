package com.proshore.powerplant.service;

import com.proshore.powerplant.dto.BatteryRequest;
import com.proshore.powerplant.dto.BatteryResponse;
import com.proshore.powerplant.dto.PostCodeRangeResponse;
import com.proshore.powerplant.entity.Battery;
import com.proshore.powerplant.exception.ResourceNotFoundException;
import com.proshore.powerplant.repository.BatteryRepository;
import com.proshore.powerplant.util.TransferObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;
import java.util.UUID;

@Service
@Slf4j
public class BatteryServiceImpl implements BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    /**
     * Saves a list of battery requests, prepares and maps them to Battery entities,
     * and returns a list of corresponding BatteryResponse objects.
     *
     * @param batteryRequests The list of BatteryRequest objects to be saved.
     * @return A list of BatteryResponse objects representing the saved batteries.
     */
    @Override
    public List<BatteryResponse> saveBattery(List<BatteryRequest> batteryRequests) {
        log.info("Received battery requests from API: {}", batteryRequests);

        // Save the battery requests and map them to Battery entities
        List<Battery> savedBatteryList = batteryRepository
                .saveAll(batteryRequests
                        .stream().map(this::prepareBattery).toList());

        // Map the saved Battery entities to BatteryResponse objects
        return TransferObject.mapToList(savedBatteryList, BatteryResponse.class);
    }

    /**
     * Prepares a Battery entity based on the given BatteryRequest.
     *
     * @param batteryRequest The BatteryRequest object to be converted to a Battery entity.
     * @return The prepared Battery entity.
     */
    private Battery prepareBattery(BatteryRequest batteryRequest) {
        return Battery.builder()
                .capacity(batteryRequest.getCapacity())
                .name(batteryRequest.getName())
                .postcode(batteryRequest.getPostcode())
                .entityId(UUID.randomUUID().toString())
                .build();
    }

    /**
     * Retrieves batteries within the specified postcode range, calculates total and average capacity,
     * and constructs a PostCodeRangeResponse with the battery information.
     *
     * @param startPostcode The starting postcode of the range.
     * @param endPostcode   The ending postcode of the range.
     * @return A PostCodeRangeResponse containing battery information within the specified range.
     * @throws ResourceNotFoundException if no batteries are found within the specified range.
     */
    @Override
    public PostCodeRangeResponse getBatteries(String startPostcode, String endPostcode) {
        List<Battery> batteries = batteryRepository.findBatteriesInRange(startPostcode, endPostcode);

        // Handle the case when no batteries are found
        if (batteries.isEmpty()) {
            log.error("Resource not found with the range :: {} - {}", startPostcode, endPostcode);
            throw new ResourceNotFoundException(String.format("Resource not found with the range :: %s - %s", startPostcode, endPostcode));
        }

        // Calculate optional average capacity
        OptionalDouble optionalAverageCapacity = batteries.stream()
                .mapToLong(Battery::getCapacity)
                .average();

        // Calculate total capacity
        long totalCapacity = batteries.stream()
                .mapToLong(Battery::getCapacity)
                .sum();

        // Construct and return the response
        return PostCodeRangeResponse.builder()
                .names(batteries.stream().map(Battery::getName).toList())
                .totalCapacity(totalCapacity)
                .averageCapacity(optionalAverageCapacity.orElse(0.0))
                .build();
    }

}
