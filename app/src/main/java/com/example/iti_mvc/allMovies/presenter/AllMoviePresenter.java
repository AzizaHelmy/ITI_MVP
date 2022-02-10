package com.example.iti_mvc.allMovies.presenter;

import com.example.iti_mvc.allMovies.view.AllMoviesView;
import com.example.iti_mvc.model.Movies;
import com.example.iti_mvc.model.RepositoryInterface;
import com.example.iti_mvc.network.NetworkDelegate;

import java.util.List;

public class AllMoviePresenter implements AllMoviesPresenter, NetworkDelegate {
    private AllMoviesView allMoviesView;
    private RepositoryInterface repositoryInterface;

    public AllMoviePresenter(AllMoviesView allMoviesView, RepositoryInterface repositoryInterface) {
        this.allMoviesView = allMoviesView;
        this.repositoryInterface = repositoryInterface;

    }

    @Override
    public void getMovies() {
        repositoryInterface.getAllMovies(this);
    }

    @Override
    public void addToFav(Movies movie) {
        repositoryInterface.insertMovie(movie);
    }

    @Override
    public void onSuccessResult(List<Movies> movies) {
        allMoviesView.showData(movies);
    }

    @Override
    public void onFailureResult(String movies) {
        allMoviesView.hideRecyclerView();
    }
}
