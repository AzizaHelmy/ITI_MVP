package com.example.iti_mvc.allMovies.view;

import com.example.iti_mvc.model.Movies;

import java.util.List;

public interface AllMoviesView {
    void showData(List<Movies> movies);

    void addMovie(Movies movie);

    void hideRecyclerView();
}
