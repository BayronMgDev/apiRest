package com.iudigital.apirest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.iudigital.apirest.Interface.JsonPlaceHolderApi;
import com.iudigital.apirest.Model.Post;
import com.iudigital.apirest.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private TextView mjsonTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mjsonTextView = findViewById(R.id.jsonText);
        getPost();
    }
    private void getPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call <List<Post>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    mjsonTextView.setText("Codigo: " + response.code());
                    return;
                }
                List<Post> postList = response.body();
                for (Post post: postList){
                    String contend = "";
                    contend += "userId: " + post.getUserId() + "\n";
                    contend += "id: " + post.getId() + "\n";
                    contend += "title: " + post.getTitle() + "\n";
                    contend += "body: " + post.getBody() + "\n\n";
                    mjsonTextView.append(contend);
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                mjsonTextView.setText(t.getMessage());
            }
        });
     }
    }