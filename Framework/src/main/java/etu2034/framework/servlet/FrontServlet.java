package etu2034.framework.servlet;

import etu2034.framework.Mapping;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class FrontServlet extends HttpServlet {

    HashMap<String, Mapping> MappingUrls;

    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        MappingUrls = mappingUrls;
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

//        file directory

        String[] separed=req.getRequestURI().split("/");
        out.println("<br>"+req.getRequestURI());
        out.println("<br>"+separed[2]);
        if (MappingUrls.containsKey(separed[2])){
            Mapping mapping=MappingUrls.get(separed[2]);
            out.println("<br>Nom de Classe: "+mapping.getClassname()+"=> Methode: "+mapping.getMethod());

        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("This is init");
        String  filePath="/Users/valisoa/Documents/GitHub/Project/Framework/src/main/java/etu2034/framework/objets";
        String package_name="etu2034.framework.objets";
        try {
            MappingUrls=Mapping.getAllMapping(filePath,package_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
