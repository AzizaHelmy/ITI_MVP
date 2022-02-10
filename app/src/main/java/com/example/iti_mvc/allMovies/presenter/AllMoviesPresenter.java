package com.example.iti_mvc.allMovies.presenter;

import com.example.iti_mvc.model.Movies;

public interface AllMoviesPresenter {
    void getMovies();
    void addToFav(Movies movie);

}
