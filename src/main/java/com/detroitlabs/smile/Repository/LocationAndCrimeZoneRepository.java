package com.detroitlabs.smile.Repository;

import com.detroitlabs.smile.Model.LocationAndCrimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.Location;


@Repository
public interface LocationAndCrimeZoneRepository extends JpaRepository<LocationAndCrimeZone, Integer> {


}
