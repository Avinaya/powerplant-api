package com.proshore.powerplant.controller;

import com.proshore.powerplant.dto.BatteryRequest;
import com.proshore.powerplant.dto.BatteryResponse;
import com.proshore.powerplant.dto.PostCodeRangeResponse;
import com.proshore.powerplant.service.BatteryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BatteryController.class)
public class BatteryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatteryService batteryService;

    @InjectMocks
    private BatteryController batteryController;

    @Test
    public void testSaveBatteries() throws Exception {
        // Prepare mock data
        List<BatteryRequest> batteryRequests = Collections.singletonList(
                new BatteryRequest("Battery 1", "10001", 100L));

        List<BatteryResponse> savedBatteries = Collections.singletonList(
                new BatteryResponse("1", "Battery 1", "10001", 100L)); // Assuming "1" is the entityId

        // Mock service behavior
        when(batteryService.saveBattery(any())).thenReturn(savedBatteries);

        // Perform POST request
        mockMvc.perform(post("/v1/battery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\": \"Battery 1\", \"postcode\": \"10001\", \"capacity\": 100}]")) // Match BatteryResponse structure
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].name").value("Battery 1"))
                .andExpect(jsonPath("$[0].capacity").value(100));
    }


    @Test
    public void testGetBatteriesWithInRange() throws Exception {
        // Prepare mock data
        String startPostcode = "10000";
        String endPostcode = "20000";

        // Create a mock PostCodeRangeResponse with corrected expected values
        PostCodeRangeResponse response = new PostCodeRangeResponse(
                Collections.singletonList("Battery 1"), 300.0, 150L);

        // Mock service behavior
        when(batteryService.getBatteries(startPostcode, endPostcode)).thenReturn(response);

        // Perform GET request
        mockMvc.perform(get("/v1/battery")
                        .param("startPostcode", startPostcode)
                        .param("endPostcode", endPostcode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.names[0]").value("Battery 1"))
                .andExpect(jsonPath("$.totalCapacity").value(150L))  // Update the expected totalCapacity value
                .andExpect(jsonPath("$.averageCapacity").value(300.0));
    }
}

