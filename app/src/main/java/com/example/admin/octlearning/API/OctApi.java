package com.example.admin.octlearning.API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
public interface OctApi {
    public static final String BASE_URL = "http://api.octlearning.com/api/";

    // we have here GET request method to get data from webAPI
    @GET
    Call<String> getLogin(@Url String URL);

    @GET
    Call<String> getCreateAccount(@Url String URL);


}
