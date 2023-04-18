package etu2034.framework;

import java.util.HashMap;

public class ModelView {
    String url;
    HashMap<String,Object> data;


    public ModelView(String url, HashMap<String, Object> data) {
        this.setUrl(url);
        this.setData(data);
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;

    }

    public ModelView(String url) {
        this.url = url;
        this.setData(new HashMap<>());
    }

    public ModelView() {
        this.setData(new HashMap<>());
    }

    public void addItem(String key, Object value){
        this.getData().put(key, value);
    }
}
