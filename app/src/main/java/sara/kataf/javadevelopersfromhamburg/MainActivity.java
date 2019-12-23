package sara.kataf.javadevelopersfromhamburg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    //variables to be used for intent
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_LOGIN = "login";

    JsonPlaceHolderApi jsonPlaceHolderApi;

    //used for view of data
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    String login,imageUrl,createdDate;
    int numberOfRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the varables used for view of data
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    //this method is used for parsing the search results
    //living in hamburg and java developers
    //sorted by followers in desc (default )order
    private void parseJSON() {
        //url for parsing the json
        String url = "https://api.github.com/search/users?q=location:hamburg+java+developer&sort=followers&order=desc";

        //json object request that takes the url to parse
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //items is the name of array of objects
                            JSONArray jsonArray = response.getJSONArray("items");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                //the variables that are shown when load app
                                login = hit.getString("login");
                                imageUrl = hit.getString("avatar_url");

                                //get the number of repos and create date method
                                getUserData();

                                mExampleList.add(new ExampleItem(imageUrl, login, numberOfRepos,createdDate));
                            }
                            //apply adapter for view purposes
                            mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    //this method is for sending variables to detail activity
    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getUserImageUrl());
        detailIntent.putExtra(EXTRA_LOGIN, clickedItem.getUserLogin());

        startActivity(detailIntent);
    }

    public void getUserData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //call for json retrofit
        Call<List<User>> call = jsonPlaceHolderApi.getUserDetail(login);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<User> posts = response.body();

                for (User post : posts) {
                    String content = "";
                    createdDate = post.getCreated_at();
                    numberOfRepos = post.getPublic_repos();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}