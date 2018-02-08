package codeutsava.wsso.Posts;


import java.util.List;

import codeutsava.wsso.Posts.model.PostsData;


public interface HomeCallBack {
    void onSuccess(List<PostsData> dataList );
    void onFailure(String error);
}
