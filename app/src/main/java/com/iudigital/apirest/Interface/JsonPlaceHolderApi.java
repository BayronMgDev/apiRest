package com.iudigital.apirest.Interface;

import com.iudigital.apirest.Model.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPost();
}
