package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;


public interface FeedingEventRepository extends CrudRepository<FeedEvent, Integer> {

}
