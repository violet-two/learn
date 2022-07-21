package com.example.network.github;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {
    @GET("user/{user}/repos")
    Call<List<Repo>> listRepos(@path("user") String uer);
}
