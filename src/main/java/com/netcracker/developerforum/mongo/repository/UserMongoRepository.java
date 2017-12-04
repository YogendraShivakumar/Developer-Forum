package com.netcracker.developerforum.mongo.repository;

import com.netcracker.developerforum.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User,Integer> {
}
