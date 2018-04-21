package com.pibigstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pibigstar.domain.RentHouse;

public interface RentHouseRepository extends MongoRepository<RentHouse, Integer>{

}
