package codeutsava.wsso.Posts.api;


import java.util.List;

import codeutsava.wsso.Posts.model.PostsData;
import codeutsava.wsso.Posts.model.PostsList;
import codeutsava.wsso.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface PostsAPi {

    @GET(Urls.REQUEST_POSTS)
    Call<PostsList> getPosts(@Query("access_token") String access);

}
