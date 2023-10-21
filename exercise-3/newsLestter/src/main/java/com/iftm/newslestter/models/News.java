package com.iftm.newslestter.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "news")
public class News {
    @Id
    private ObjectId id;
    private String title;
    private String date;
    private String editorName;
    private List<Post> posts;

    public News() {
    }

    public News(final ObjectId id, final String title, final String date, final String editorName, final List<Post> posts) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.editorName = editorName;
        this.posts = posts;
    }

    public News(final String title, final String date, final String editorName, final List<Post> posts) {
        this.title = title;
        this.date = date;
        this.editorName = editorName;
        this.posts = posts;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(final String editorName) {
        this.editorName = editorName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(final List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(date, news.date) && Objects.equals(editorName, news.editorName) && Objects.equals(posts, news.posts);
    }
}
