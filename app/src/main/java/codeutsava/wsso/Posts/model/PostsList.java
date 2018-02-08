package codeutsava.wsso.Posts.model;

import java.util.List;

/**
 * Created by iket on 4/2/18.
 */

public class PostsList {
    private List<PostsData> value;

    public PostsList(List<PostsData> value) {
        this.value = value;
    }

    public List<PostsData> getValue() {
        return value;
    }
}
