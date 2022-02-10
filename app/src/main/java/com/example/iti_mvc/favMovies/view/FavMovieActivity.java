package com.example.iti_mvc.favMovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iti_mvc.*;
import com.example.iti_mvc.db.ConcretLocalSource;
import com.example.iti_mvc.favMovies.presenter.FavMoviesPresenter;
import com.example.iti_mvc.favMovies.presenter.FavMoviesPresenterInterface;
import com.example.iti_mvc.model.Repository;
import com.example.iti_mvc.model.Movies;
import com.example.iti_mvc.network.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

public class FavMovieActivity extends AppCompatActivity implements OnFavClickListener,FavMoviesInterface {
    RecyclerView recyclerFav;
    FavMovieAdapter favMovieAdapter;
    List<Movies> movies;
    ImageView imgHolderEmpty;
    FavMoviesPresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movie);

        presenterInterface=new FavMoviesPresenter(new Repository(this,new ConcretLocalSource(this),new RetrofitFactory()),this);
        presenterInterface.getMovies(this);
        recyclerFav = findViewById(R.id.rv_fav_movie);
        imgHolderEmpty = findViewById(R.id.empty_iv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerFav.setLayoutManager(layoutManager);
        favMovieAdapter = new FavMovieAdapter(FavMovieActivity.this, new ArrayList<Movies>(), this);
        recyclerFav.setAdapter(favMovieAdapter);

    }

    @Override
    public void onFavClick(Movies movie) {
        //delet from repo
      presenterInterface.removeFromFav(movie);
        Toast.makeText(this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
        favMovieAdapter.notifyDataSetChanged();
        //checkForFavMovies(movie);
    }

    @Override
    public void showData(List<Movies> movies) {
        favMovieAdapter.setList(movies);
        cheackFav(movies);
        favMovieAdapter.notifyDataSetChanged();
    }
//====================================================
    private void cheackFav(List<Movies> movies) {
        if (movies.isEmpty()) {
            recyclerFav.setVisibility(View.GONE);
            imgHolderEmpty.setVisibility(View.VISIBLE);
        } else {
            imgHolderEmpty.setVisibility(View.GONE);
            recyclerFav.setVisibility(View.VISIBLE);
        }
    }

}