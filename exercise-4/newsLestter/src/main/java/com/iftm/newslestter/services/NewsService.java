package com.iftm.newslestter.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iftm.newslestter.repositories.NewsRepository;
import com.iftm.newslestter.models.dtos.LogDTO;
import com.iftm.newslestter.messages.RabbitMqSendLog;
import com.iftm.newslestter.models.dtos.NewsDTO;

import com.iftm.newslestter.models.News;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
private NewsRepository repository;

@Autowired
private RabbitMqSendLog rabbitMqSendLog;

public ResponseEntity<List<NewsDTO>> findAll() {
    List<News> dbNews = repository.findAll();

    if (dbNews.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    List<NewsDTO> newsDtos = dbNews.stream().map(NewsDTO::new).toList();

    return ResponseEntity.ok(newsDtos);
}

public ResponseEntity<NewsDTO> findById(final ObjectId id) {
    if (id == null) {
        return ResponseEntity.badRequest().build();
    }

    Optional<News> dbNews = repository.findById(id);

    return dbNews.map(news -> ResponseEntity.ok(new NewsDTO(news)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
}

public ResponseEntity<NewsDTO> save(final NewsDTO newsDTO) {
    if (newsDTO == null) {
        return ResponseEntity.badRequest().build();
    }

    News savedNews = repository.save(newsDTO.toCustomNews());
    NewsDTO savedNewsDto = new NewsDTO(savedNews);

    rabbitMqSendLog.sendLog(new LogDTO<NewsDTO>("created", savedNewsDto));
    return ResponseEntity.ok(savedNewsDto);
}

public ResponseEntity<NewsDTO> update(final NewsDTO newsDTO) {
    if (newsDTO.getId() == null) {
        return ResponseEntity.badRequest().build();
    }

    ObjectId objectId = new ObjectId(String.valueOf(newsDTO.getId()));
    Optional<News> dbNews = repository.findById(objectId);

    if (dbNews.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    News dbNewsObj = dbNews.get();
    dbNewsObj.setDate(newsDTO.getDate());
    dbNewsObj.setEditorName(newsDTO.getEditorName());
    dbNewsObj.setPosts(newsDTO.getPosts());
    dbNewsObj.setTitle(newsDTO.getTitle());

    return ResponseEntity.ok(new NewsDTO(repository.save(dbNewsObj)));
}

public ResponseEntity<?> delete(final ObjectId id) {
    if (id == null) {
        return ResponseEntity.badRequest().build();
    }

    Optional<News> deletedNews = repository.findById(id);

    if (deletedNews.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    repository.deleteById(id);
    rabbitMqSendLog.sendLog(new LogDTO<NewsDTO>("deleted", new NewsDTO(deletedNews.get())));

    return ResponseEntity.ok().build();
}

}
