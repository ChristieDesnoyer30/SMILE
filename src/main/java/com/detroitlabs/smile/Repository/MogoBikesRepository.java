package com.detroitlabs.smile.Repository;

import com.detroitlabs.smile.Model.MogoBikesAndBlockId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MogoBikesRepository extends JpaRepository<MogoBikesAndBlockId, Integer> {

    MogoBikesAndBlockId findByCityBlockId(String blockId);
}
