package com.example.iti_mvc.allMovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iti_mvc.R;
import com.example.iti_mvc.allMovies.presenter.AllMoviePresenter;
import com.example.iti_mvc.allMovies.presenter.AllMoviesPresenter;
import com.example.iti_mvc.db.ConcretLocalSource;
import com.example.iti_mvc.model.Repository;
import com.example.iti_mvc.model.Movies;
import com.example.iti_mvc.network.RetrofitFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener, AllMoviesView {
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    List<Movies> movies;
    ImageView imageHolder;
    AllMoviesPresenter moviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesPresenter = new AllMoviePresenter(this, new Repository(this, new ConcretLocalSource(this), new RetrofitFactory()));
        moviesPresenter.getMovies();
        recyclerView = findViewById(R.id.rv_movie);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(MainActivity.this, movies, this);

    }

    @Override
    public void onFavClicked(Movies movie) {
        movie.setFav(true);
        moviesPresenter.addToFav(movie);
        Toast.makeText(this, "added to Fav", Toast.LENGTH_SHORT).show();
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShareClicked(Movies movie) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hey this film is so amazing " + movie.getTitle());
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Share With "));
        }
    }

    @Override
    public void showData(List<Movies> movies) {
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.setList(movies);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMovie(Movies movie) {
        moviesPresenter.addToFav(movie);
    }

    @Override
    public void hideRecyclerView() {
        recyclerView.setVisibility(View.GONE);
    }


}