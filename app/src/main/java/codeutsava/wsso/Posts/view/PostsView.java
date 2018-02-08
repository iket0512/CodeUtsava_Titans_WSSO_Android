package codeutsava.wsso.Posts.view;


import java.util.List;

import codeutsava.wsso.Posts.model.PostsData;

public interface PostsView {
    void showProgressBAr(Boolean bool);

    void setData(List<PostsData> data);
    void showError(String error);
}
