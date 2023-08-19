package com.proshore.powerplant.repository;

import com.proshore.powerplant.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

    @Query(value = "SELECT * FROM battery WHERE CAST(postcode AS UNSIGNED) BETWEEN CAST(?1 AS UNSIGNED) AND CAST(?2 AS UNSIGNED) ORDER BY name", nativeQuery = true)
    List<Battery> findBatteriesInRange(String startPostcode, String endPostcode);


}
