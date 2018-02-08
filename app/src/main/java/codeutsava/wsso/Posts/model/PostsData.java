package codeutsava.wsso.Posts.model;


import java.util.List;

public class PostsData {
    private String alert_level,address;
    private List<ElementsData> elements;

    public PostsData(String alert_level, String address, List<ElementsData> elements) {
        this.alert_level = alert_level;
        this.address = address;
        this.elements = elements;
    }

    public String getAlert_level() {
        return alert_level;
    }

    public String getAddress() {
        return address;
    }

    public List<ElementsData> getElements() {
        return elements;
    }
}
