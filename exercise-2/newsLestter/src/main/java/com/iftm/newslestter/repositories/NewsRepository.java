package com.iftm.newslestter.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iftm.newslestter.models.News;

@Repository
public interface NewsRepository extends MongoRepository<News, ObjectId> {
}
