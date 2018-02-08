package codeutsava.wsso.Posts.presenter;

import android.util.Log;

import java.util.List;

import codeutsava.wsso.Posts.HomeCallBack;
import codeutsava.wsso.Posts.model.PostsData;
import codeutsava.wsso.Posts.model.PostsProviderInterface;
import codeutsava.wsso.Posts.view.PostsView;


public class Posts_Presenter implements PostsPresenterInterface {
    private PostsView postsView;
    private PostsProviderInterface postsProviderInterface;

    public Posts_Presenter(PostsView postsView, PostsProviderInterface postsProviderInterface) {
        this.postsView = postsView;
        this.postsProviderInterface = postsProviderInterface;
    }

    @Override
    public void getProductData(String access) {
        postsView.showProgressBAr(true);
        postsProviderInterface.getProductData(access, new HomeCallBack() {
            @Override
            public void onSuccess(List<PostsData> postsData) {
                Log.d("presenter",""+postsData.size());
                postsView.setData(postsData);
                postsView.showProgressBAr(false);
            }

            @Override
            public void onFailure(String error) {
                postsView.showProgressBAr(false);
                postsView.showError(error);
            }
        });
    }


}
