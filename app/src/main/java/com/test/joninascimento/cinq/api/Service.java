package com.test.joninascimento.cinq.api;

import com.test.joninascimento.cinq.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/photos")
    Call<List<Album>> recuperarDados();

}
