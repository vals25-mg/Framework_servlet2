package etu2034.framework.servlet;

import etu2034.framework.Mapping;
import etu2034.framework.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FrontServlet extends HttpServlet {

    HashMap<String, Mapping> MappingUrls;

    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        MappingUrls = mappingUrls;
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

//        file directory

        String[] separed=req.getRequestURI().split("/");
        out.println("<br>"+req.getRequestURI());
        out.println("<br>"+separed[2]);
        if (MappingUrls.containsKey(separed[2])){
            Mapping mapping=MappingUrls.get(separed[2]);
            out.println("<br>Nom de Classe: "+mapping.getClassname()+"=> Methode: "+mapping.getMethod());
            Class<?> class1=Class.forName(mapping.getClassname());
            Object object=class1.getDeclaredConstructor().newInstance();
            out.println("<br>"+object.getClass());
            Method method=object.getClass().getMethod(mapping.getMethod());
            ModelView result=(ModelView) method.invoke(object); //Class ModelView
            out.println("<br>"+result.getClass());

//            Envoi Donnees ModelView vers l'url
            for (Map.Entry<String,Object> set: result.getData().entrySet()){
                req.setAttribute(set.getKey(),set.getValue());
            }

            for (Map.Entry<String,Object> set: result.getData().entrySet()){
                out.println("<br>"+req.getAttribute(set.getKey()));
            }

//            out.println("<br>"+req.getAttribute("Emp2"));

            req.getRequestDispatcher(result.getUrl()).forward(req,resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }    }

    @Override
    public void init() throws ServletException {
        System.out.println("This is init");
        String  filePath="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/java/objets";
        String package_name="objets";
        try {
            MappingUrls=Mapping.getAllMapping(filePath,package_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
