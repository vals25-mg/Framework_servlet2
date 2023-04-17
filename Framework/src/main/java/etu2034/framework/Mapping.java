package etu2034.framework;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

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


    public static String[] get_java_files_name(String filePath){
        // array string to put all file names
        String [] java_files_name=new String[0];

        // get all file lists
        File dir= new File(filePath);
        File[] files=dir.listFiles();

        // function to filter all non-java files
        FilenameFilter filenameFilter=new FilenameFilter() {
            public boolean accept(File dir, String name){
                if (name.endsWith(".java")) return true;
                return false;
            }
        };
        //affect all java files name to the string array
        java_files_name=dir.list(filenameFilter);

        // Show All the directory components
        System.out.println("---All Files name ");
        for (File file : files) {
            System.out.println(file.getName());
        }
        System.out.println();

        // split the java file to have only the class name
        for (int i = 0; i < java_files_name.length; i++) {
            java_files_name[i]=java_files_name[i].split(".java")[0];

        }
        // Show java files inside the directory
        System.out.println("---All java files name");
        for (int i = 0; i < java_files_name.length; i++) {
            System.out.println(java_files_name[i]);
        }
        return java_files_name;
    }

    public static HashMap<String,Mapping> getAllMapping (String filePath,String package_name) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Init hashmap
        HashMap<String,Mapping> mappingUrls=new HashMap<>();
        String[] java_files_name=get_java_files_name(filePath);
        if(!package_name.equals("")) package_name+=".";
        for (int i = 0; i < java_files_name.length; i++) {
            Class<?> class1=Class.forName(package_name+java_files_name[i]);
            Object object=class1.getDeclaredConstructor().newInstance();
            Method[] methods= object.getClass().getDeclaredMethods();

            System.out.println();
            for (int j = 0; j < methods.length; j++) {
                if (methods[j].isAnnotationPresent(MethodAnnotation.class)) {
                    System.out.println("--Methods call by their url");
                    String url=methods[j].getAnnotation(MethodAnnotation.class).url();
                    System.out.println(object.getClass().getSimpleName()+": "+methods[j].getName()+",url=>"+url);
                    mappingUrls.put(url, new Mapping(class1.getName(), methods[j].getName()));
                }
            }
        }
        return mappingUrls;
    }
}
