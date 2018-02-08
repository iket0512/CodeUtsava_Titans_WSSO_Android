package codeutsava.wsso.Posts.model;

/**
 * Created by iket on 4/2/18.
 */

public class ElementsData {
    private String name,count,limit;

    public ElementsData(String name, String count, String limit) {
        this.name = name;
        this.count = count;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public String getCount() {
        return count;
    }

    public String getLimit() {
        return limit;
    }
}
