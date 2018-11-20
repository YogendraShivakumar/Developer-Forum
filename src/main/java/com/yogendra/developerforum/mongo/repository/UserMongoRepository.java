package com.yogendra.developerforum.mongo.repository;

import com.yogendra.developerforum.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User,Integer> {


}
