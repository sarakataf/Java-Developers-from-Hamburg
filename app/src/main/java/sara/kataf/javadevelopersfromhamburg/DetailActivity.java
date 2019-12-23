package sara.kataf.javadevelopersfromhamburg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static sara.kataf.javadevelopersfromhamburg.MainActivity.EXTRA_LOGIN;
import static sara.kataf.javadevelopersfromhamburg.MainActivity.EXTRA_URL;


public class DetailActivity extends AppCompatActivity {

    TextView textViewName,textViewCompany,textViewLocation,textViewEmail,textViewFollowing,textViewFollowers,textViewPublicRepos;
    TextView textViewPublicGists,textViewBio,textViewBlog;

    //json methods to get data
    JsonPlaceHolderApi jsonPlaceHolderApi;

    String userLogin,imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //the library used to get user variables
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //get varibles from previous activity
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(EXTRA_URL);
        userLogin = intent.getStringExtra(EXTRA_LOGIN);

        //posting user variables
        ImageView imageView = findViewById(R.id.user_image_view);
        textViewName = findViewById(R.id.user_name);
        textViewCompany = findViewById(R.id.user_company);
        textViewLocation = findViewById(R.id.user_location);
        textViewEmail = findViewById(R.id.user_email);
        textViewFollowing = findViewById(R.id.user_following);
        textViewFollowers = findViewById(R.id.user_followers);
        textViewPublicRepos = findViewById(R.id.user_public_repos);
        textViewPublicGists = findViewById(R.id.user_public_gists);
        textViewBio = findViewById(R.id.user_bio);
        textViewBlog = findViewById(R.id.user_blog);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);

        //method to get user data
        getUser();
    }

    private void getUser() {

        //call for json retrofit
        Call<User> call = jsonPlaceHolderApi.getUser(userLogin);//parameters for input

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code" + response.code());
                    return;
                }

                User users = response.body();

                textViewName.setText("Name: " + users.getName());
                textViewCompany.setText("Company: " + users.getCompany());
                textViewLocation.setText("Location: "+users.getLocation());
                textViewEmail.setText("Email: "+users.getEmail());
                textViewFollowing.setText("Following: "+Integer.toString(users.getFollowing()));
                textViewFollowers.setText("Followers: "+Integer.toString(users.getFollowers()));
                textViewPublicRepos.setText("Public Repos: "+Integer.toString(users.getPublic_repos()));
                textViewPublicGists.setText("Public Gists: "+Integer.toString(users.getPublic_gists()));
                textViewBio.setText("Bio: "+users.getBio());
                textViewBlog.setText("Blog: "+users.getBlog());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}