package etu2034.framework;

public class Mapping {
    String classname;
    String method;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Mapping(String classname, String method) {
        this.classname = classname;
        this.method = method;
    }

    public Mapping() {
    }
}
