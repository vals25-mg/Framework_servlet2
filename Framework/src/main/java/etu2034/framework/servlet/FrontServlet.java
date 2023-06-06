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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
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

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
            out.println("<br>djfblsdjfbl "+object.getClass());
//            Method[] ms=object.getClass().getDeclaredMethods();
//            for (Method m:
//                 ms) {
//                out.print("<br>"+m.getName());
//            }
//            try {
//            out.println("<br>Avant method:"+object.getClass().getDeclaredMethod(mapping.getMethod()).getName());
//            }catch (Exception e){
//                out.println("<br>Erreur(s): "+e.getMessage());
//            }
            Method method=object.getClass().getDeclaredMethod(mapping.getMethod());

            out.println("<br>Methode: "+method.getName()+"/ type de retour :"+method.getReturnType());
            if(method.getReturnType().equals(ModelView.class)) {
                ModelView result = (ModelView) method.invoke(object); //Class ModelView
                out.println("<br>" + result.getClass());

                //            Envoi Donnees ModelView vers l'url
                for (Map.Entry<String, Object> set : result.getData().entrySet()) {
                    req.setAttribute(set.getKey(), set.getValue());
                }

                for (Map.Entry<String, Object> set : result.getData().entrySet()) {
                    out.println("<br>" + req.getAttribute(set.getKey()));
                }

                req.getRequestDispatcher(result.getUrl()).forward(req, resp);
            }


            if (method.getReturnType().equals(Void.TYPE)){
                checkAvailable(object,req,out);
                showObjectContent(object,out);
            }
            out.println("<br>Fin");
        }
    }


    public void showObjectContent(Object object,PrintWriter out) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] fields=object.getClass().getDeclaredFields();
        out.println("<br> Les contenus :");
        for (Field field: fields) {
            Method method=object.getClass().getMethod("get"+upperCaseFirstLetter(field.getName()));
            out.println("<br>-"+method.invoke(object));
        }
    }
    public void checkAvailable(Object object, HttpServletRequest req,PrintWriter out ) throws Exception{
        Field[] fields=object.getClass().getDeclaredFields();
        Enumeration<String> attributs= req.getParameterNames();
        while (attributs.hasMoreElements()){
            String paramName= attributs.nextElement();
            out.print("<br>Avant Boucle, taille field:"+fields.length+", paramName: "+paramName);
            for (int i = 0; i < fields.length; i++) {
                out.print("<br>"+fields[i].getName()+" et "+paramName);
                String arg=req.getParameter(paramName);
                if(fields[i].getName().equals(paramName)){
                    out.print("<br>"+fields[i].getName()+" == set"+upperCaseFirstLetter(paramName)+"("+req.getParameter(paramName)+")");
                    object.getClass().getDeclaredMethod("set"+upperCaseFirstLetter(paramName),fields[i].getType()).invoke(object,getTypeChanged(fields[i],arg));
                    out.print("<br>---"+object.getClass().getDeclaredMethod("get"+upperCaseFirstLetter(paramName)).invoke(object));
                    break;
                }
            }
        }
    }

    public String upperCaseFirstLetter(String word){
        return word.substring(0,1).toUpperCase()+word.substring(1);
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

    public Object getTypeChanged(Field field,String arg){
        if (field.getType()==double.class) return Double.parseDouble(arg);
        if (field.getType()==int.class) return Integer.parseInt(arg);
        if (field.getType()==boolean.class) return Boolean.parseBoolean(arg);
        return arg;
    }
}
