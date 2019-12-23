package sara.kataf.javadevelopersfromhamburg;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

//this call has the methods for the json parsing
public interface JsonPlaceHolderApi {

    //get users with certain name
    @GET("users/{name}")
    Call<User> getUser(@Path ("name") String name);

    @GET("users/{name}")
    Call<List<User>> getUserDetail(@Path ("name") String name);

    //show searched users with critera and sort and order functions
    @Headers("Accept: application/vnd.github.v3.text-match+json")
    @GET("search/users")
    Call<SearchResults> searchForUser(@Query("q") String searchCriteria,
                                      @Query("sort") String sort,
                                      @Query("order") String order);
}