package codeutsava.wsso.Posts.model;

import codeutsava.wsso.Posts.HomeCallBack;


public interface PostsProviderInterface {
    void getProductData(String access, HomeCallBack homeCallBack);
//    void transfer(String prod_id, String receiver, String access, HomeCallBack homeCallBack);
}
