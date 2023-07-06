package etu2034.framework;

public class FileUpload {
    String name;
    String path;
    byte[] bytes;


    public FileUpload() {
    }

    public FileUpload(String name, String path, byte[] bytes) {
        this.setName(name);
        this.setPath(path);
        this.setBytes(bytes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
