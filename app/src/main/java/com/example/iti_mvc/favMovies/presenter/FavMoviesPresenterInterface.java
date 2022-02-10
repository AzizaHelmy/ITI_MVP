package com.example.iti_mvc.favMovies.presenter;

import androidx.lifecycle.LifecycleOwner;

import com.example.iti_mvc.model.Movies;

public interface FavMoviesPresenterInterface {
    void getMovies(LifecycleOwner owner);
    void removeFromFav(Movies movie);


}
