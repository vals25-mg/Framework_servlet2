package etu2034.framework;

import java.util.HashMap;

public class ModelView {
    String url;
    HashMap<String, Object> data;
    HashMap<String, Object> sessions;
    boolean isJson = false;

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

    public HashMap<String, Object> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, Object> sessions) {
        this.sessions = sessions;
    }

    public boolean getIsJson() {
        return isJson;
    }

    public void setIsJson(boolean isJson) {
        this.isJson = isJson;
    }

    public ModelView(String url) {
        this.url = url;
        this.setData(new HashMap<>());
    }

    public ModelView() {
        this.setData(new HashMap<>());
    }

    public void addItem(String key, Object value) {
        this.getData().put(key, value);
    }

    public void addSessions(String key, Object value) {
        sessions.put(key, value);
    }
}
