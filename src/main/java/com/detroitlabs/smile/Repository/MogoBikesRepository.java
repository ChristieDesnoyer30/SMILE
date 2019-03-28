package com.detroitlabs.smile.Repository;

import com.detroitlabs.smile.Model.MogoBikesAndBlockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MogoBikesRepository extends JpaRepository<MogoBikesAndBlockId, Integer> {

    MogoBikesAndBlockId findByCityBlockId(String cityBlockId);
}
