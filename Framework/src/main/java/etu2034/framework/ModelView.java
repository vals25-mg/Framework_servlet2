package etu2034.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelView {
    String url;
    HashMap<String, Object> data;
    HashMap<String, Object> sessions;
    boolean isJson = false;
    boolean isRedirect = false;
    boolean invalidateSession = false; // Remove all session items if true
    List<String> removeSessions = new ArrayList<>(); // Remove specific session items

    public ModelView(String url, HashMap<String, Object> data) {
        this.setUrl(url);
        this.setData(data);
    }

    public boolean isJson() {
        return isJson;
    }

    public void setJson(boolean json) {
        isJson = json;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }

    public List<String> getRemoveSessions() {
        return removeSessions;
    }

    public void setRemoveSessions(List<String> removeSessions) {
        this.removeSessions = removeSessions;
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
