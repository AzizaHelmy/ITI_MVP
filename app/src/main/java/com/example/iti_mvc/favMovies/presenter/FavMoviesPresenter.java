package com.example.iti_mvc.favMovies.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.iti_mvc.favMovies.view.FavMoviesInterface;
import com.example.iti_mvc.model.Movies;
import com.example.iti_mvc.model.RepositoryInterface;

import java.util.List;

public class FavMoviesPresenter implements FavMoviesPresenterInterface {
    RepositoryInterface repositoryInterface;
    FavMoviesPresenterInterface favMoviesPresenterInterface;
    FavMoviesInterface favMoviesInterface;
    //private RepositoryInterface repositoryInterface;
    public FavMoviesPresenter(RepositoryInterface repositoryInterface,  FavMoviesInterface favMoviesInterface) {
        this.repositoryInterface = repositoryInterface;
        this.favMoviesInterface =favMoviesInterface ;
    }

    @Override
    public void getMovies(LifecycleOwner owner) {
        repositoryInterface.getFavMovies().observe(owner, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
              favMoviesInterface.showData(movies);
            }
        });
    }

    @Override
    public void removeFromFav(Movies movie) {
        repositoryInterface.deleteMovie(movie);
    }
}
