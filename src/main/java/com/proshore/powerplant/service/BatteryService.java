package com.proshore.powerplant.service;

import com.proshore.powerplant.dto.BatteryRequest;
import com.proshore.powerplant.dto.BatteryResponse;
import com.proshore.powerplant.dto.PostCodeRangeResponse;

import java.util.List;

public interface BatteryService {
    
    List<BatteryResponse> saveBattery(List<BatteryRequest> batteryRequests);

    PostCodeRangeResponse getBatteries(String startPostcode, String endPostcode);
}
