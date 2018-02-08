package codeutsava.wsso.Posts.model;

import java.util.List;

import codeutsava.wsso.Posts.HomeCallBack;
import codeutsava.wsso.Posts.api.PostsAPi;
import codeutsava.wsso.helper.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Posts_Provider implements PostsProviderInterface {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    Retrofit retrofit=new Retrofit.Builder().baseUrl(Urls.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
    PostsAPi qrRequest = retrofit.create(PostsAPi.class);

    @Override
    public void getProductData(String access, final HomeCallBack homeCallBack) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Call <PostsList>call=qrRequest.getPosts(access);
        call.enqueue(new Callback<PostsList>() {

            @Override
            public void onResponse(Call<PostsList> call, Response<PostsList> response) {
                homeCallBack.onSuccess(response.body().getValue());
            }

            @Override
            public void onFailure(Call<PostsList> call, Throwable t) {
                t.printStackTrace();
                homeCallBack.onFailure("No Internet Connection");
            }
        });
    }

}
