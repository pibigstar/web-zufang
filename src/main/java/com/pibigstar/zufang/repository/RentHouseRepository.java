package com.pibigstar.zufang.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pibigstar.zufang.domain.RentHouse;

public interface RentHouseRepository extends MongoRepository<RentHouse, Integer>{

}
