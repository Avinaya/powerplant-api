package com.proshore.powerplant.controller;

import com.proshore.powerplant.dto.BatteryRequest;
import com.proshore.powerplant.dto.BatteryResponse;
import com.proshore.powerplant.dto.PostCodeRangeResponse;
import com.proshore.powerplant.service.BatteryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/batteries")
@Tag(name = "Battery", description = "Controller for battery APIs")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    /**
     * Endpoint to save a list of batteries.
     *
     * @param batteryRequests The list of BatteryRequest objects to be saved.
     * @return A ResponseEntity containing the list of saved BatteryResponse objects.
     */
    @Operation(summary = "Save Batteries", description = "Save a list of batteries.")
    @PostMapping
    public ResponseEntity<List<BatteryResponse>> saveBatteries(@Valid @RequestBody List<BatteryRequest> batteryRequests) {
        List<BatteryResponse> savedBatteries = batteryService.saveBattery(batteryRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBatteries);
    }

    /**
     * Endpoint to retrieve batteries within a specified postcode range.
     *
     * @param startPostcode The starting postcode of the range.
     * @param endPostcode   The ending postcode of the range.
     * @return A ResponseEntity containing the PostCodeRangeResponse with battery information within the specified range.
     */
    @Operation(summary = "Get Batteries Within Range", description = "Retrieve batteries within a specified postcode range.")
    @GetMapping
    public ResponseEntity<PostCodeRangeResponse> getBatteriesWithInRange(
            @RequestParam(name = "startPostcode") String startPostcode,
            @RequestParam(name = "endPostcode") String endPostcode) {
        PostCodeRangeResponse response = batteryService.getBatteries(startPostcode, endPostcode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}