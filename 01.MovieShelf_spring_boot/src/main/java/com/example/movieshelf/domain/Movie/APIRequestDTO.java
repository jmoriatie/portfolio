package com.example.movieshelf.domain.Movie;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class APIRequestDTO {
    private String title, link, image, subtitle, pubDate, director, actor, userRating;

    public APIRequestDTO(String title, String link, String image, String subtitle, String pubDate, String director, String actor, String userRating) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.subtitle = subtitle;
        this.pubDate = pubDate;
        this.director = director;
        this.actor = actor;
        this.userRating = userRating;

    }
}
