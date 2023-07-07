package etu2034.framework.servlet;

import etu2034.framework.FileUpload;
import etu2034.framework.Mapping;
import etu2034.framework.ModelView;
import etu2034.framework.Scope;
import etu2034.framework.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.*;

@MultipartConfig
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;
    Vector<FileUpload> fileUploads;
    HashMap<String, Object> classeInstances;
    HashMap<String, Object> sessions;

    public Vector<FileUpload> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(Vector<FileUpload> fileUploads) {
        this.fileUploads = fileUploads;
    }

    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        MappingUrls = mappingUrls;
    }

    public HashMap<String, Object> getClasseInstances() {
        return classeInstances;
    }

    public void setClasseInstances(HashMap<String, Object> classeInstances) {
        this.classeInstances = classeInstances;
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // file directory

        String[] separed = req.getRequestURI().split("/");
        out.println("<br>" + req.getRequestURI());
        out.println("<br>" + separed[2]);

        if (MappingUrls.containsKey(separed[2])) {
            Mapping mapping = MappingUrls.get(separed[2]);
            out.println("<br>Nom de Classe: " + mapping.getClassname() + "=> Methode: " + mapping.getMethod());
            Class<?> class1 = Class.forName(mapping.getClassname());
            Object object = class1.getDeclaredConstructor().newInstance();
            out.println("<br>" + object.getClass());
            try {
                Method method = rightMethod(req, object, mapping.getMethod(), out);
                out.println("<br>Methode: " + method.getName() + "/ type de retour :" + method.getReturnType());
                // Si le type de retour est ModelView
                if (method.getReturnType().equals(ModelView.class)) {
                    // Si la methode n'a pas d'argument
                    if (method.getParameters().length == 0) {
                        showList(req, resp, object, method);
                    }
                    // Si la methode a un certain argument
                    else {
                        // String[] parameters = Arrays.stream(method.getParameters())
                        // .map(java.lang.reflect.Parameter::getName)
                        // .toArray(String[]::new);
                        Object[] parameters = findArgsValues(method, req);
                        ModelView result = (ModelView) method.invoke(object, parameters);
                        for (Map.Entry<String, Object> set : result.getData().entrySet()) {
                            out.print("<br>" + set.getValue());
                        }
                    }
                }

                // else throw new Exception("La méthode doit retourner un ModelView");
            } catch (Exception e) {
                out.println("<br>Erreur: " + e.getMessage());
            }

            // if (method.getReturnType().equals(Void.TYPE)){
            // checkAvailable(object,req,out);
            // showObjectContent(object,out);
            // }
            out.println("<br>Fin");
        }
    }

    public void showObjectContent(Object object, PrintWriter out)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        out.println("<br> Les contenus :");
        for (Field field : fields) {
            Method method = object.getClass().getMethod("get" + upperCaseFirstLetter(field.getName()));
            out.println("<br>-" + method.invoke(object));
        }
    }

    public void checkAvailable(Object object, HttpServletRequest req, PrintWriter out) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        Enumeration<String> attributs = req.getParameterNames();
        while (attributs.hasMoreElements()) {
            String paramName = attributs.nextElement();
            out.print("<br>Avant Boucle, taille field:" + fields.length + ", paramName: " + paramName);
            for (int i = 0; i < fields.length; i++) {
                out.print("<br>" + fields[i].getName() + " et " + paramName);
                String arg = req.getParameter(paramName);
                if (fields[i].getName().equals(paramName)) {
                    out.print("<br>" + fields[i].getName() + " == set" + upperCaseFirstLetter(paramName) + "("
                            + req.getParameter(paramName) + ")");
                    object.getClass().getDeclaredMethod("set" + upperCaseFirstLetter(paramName), fields[i].getType())
                            .invoke(object, getTypeChanged(fields[i], arg));
                    out.print("<br>---" + object.getClass().getDeclaredMethod("get" + upperCaseFirstLetter(paramName))
                            .invoke(object));
                    break;
                }
            }
        }
    }

    public String upperCaseFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
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
        }
    }

    @Override
    public void init() throws ServletException {
        System.out.println("This is init");
        String filePath = "/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/java/objets";
        String package_name = "objets";
        try {
            MappingUrls = Mapping.getAllMapping(filePath, package_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getTypeChanged(Field field, String arg) {
        if (field.getType() == double.class)
            return Double.parseDouble(arg);
        if (field.getType() == int.class)
            return Integer.parseInt(arg);
        if (field.getType() == boolean.class)
            return Boolean.parseBoolean(arg);
        return arg;
    }

    public Object getTypeChanged(Type type, String value) {
        if (type == double.class)
            return Double.parseDouble(value);
        if (type == int.class)
            return Integer.parseInt(value);
        if (type == float.class)
            return Float.parseFloat(value);
        if (type == long.class)
            return Long.parseLong(value);
        if (type == boolean.class)
            return Boolean.parseBoolean(value);
        if (type == java.sql.Date.class)
            return java.sql.Date.valueOf(value);
        return value;
    }

    public Object[] findArgsValues(Method method, HttpServletRequest req) throws Exception {
        Object[] values = new Object[method.getParameterCount()];
        Parameter[] methodParameters = method.getParameters();
        Parameter[] parameters = methodParameters;
        for (int i = 0; i < values.length; i++) {
            String paramNameSimple = parameters[i].getName();
            values[i] = getTypeChanged(parameters[i].getType(), req.getParameter(paramNameSimple));
        }
        return values;
    }

    // Mamadika null ny attributs rehetra anah object iray
    public void reset(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        Method methodSet = null;
        for (Field f : fields) {
            try {
                methodSet = object.getClass().getDeclaredMethod("set" + upperCaseFirstLetter(f.getName()));
            } catch (Exception e) {
                continue;
            }
        }
    }

    public boolean checkArgs(HttpServletRequest request, Method method, PrintWriter out) {
        for (Parameter p : method.getParameters()) {
            out.println("<br>p.getName(): " + p.getName());
            if (request.getParameter(p.getName()) == null) {
                out.println("<br>p.getName() inside: " + p.getName());
                return false;
            }
        }
        return true;
    }

    public Method rightMethod(HttpServletRequest request, Object object, String methodName, PrintWriter out)
            throws Exception {
        Method method = null;
        Method[] methods = object.getClass().getDeclaredMethods();
        out.println("<br>Taille tableau methods: " + methods.length);
        out.println("<br>methodName: " + methodName);
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName() == methodName && checkArgs(request, methods[i], out)) {
                method = methods[i];
            }
            out.println("<br>methods[i]: " + methods[i].getName());
        }
        return method;
    }

    public void showList(HttpServletRequest req, HttpServletResponse resp, Object object, Method method)
            throws Exception {

        ModelView result = (ModelView) method.invoke(object); // Class ModelView
        resp.getWriter().println("<br>" + result.getClass());

        // Envoi Données ModelView vers l'url
        for (Map.Entry<String, Object> set : result.getData().entrySet()) {
            req.setAttribute(set.getKey(), set.getValue());
        }

        for (Map.Entry<String, Object> set : result.getData().entrySet()) {
            resp.getWriter().println("<br>" + req.getAttribute(set.getKey()));
        }
        // Renvoie a la prochaine page
        req.getRequestDispatcher(result.getUrl()).forward(req, resp);
    }

    /*
     * sprint 10
     * atsoin'lah rehefa le micheck an'le fichier rehetra
     * url essai de la class Dept ny test an'ito
     */

    public void checkSingleton(Class classToChecked) {
        if (classToChecked.isAnnotationPresent(Singleton.class)) {
            classeInstances.put(classToChecked.getName(), null);
        }
    }

    public Object AddInClassInstances(Class classToChecked) throws InstantiationException, IllegalAccessException {
        if (classeInstances.containsKey(classToChecked.getName())) {
            if (classeInstances.get(classToChecked.getName()) == null) {
                classeInstances.replace(classToChecked.getName(), classToChecked.newInstance());
            }
            return classeInstances.get(classToChecked.getName());
        }
        return classToChecked.newInstance();
    }

    /*
     * jerena raha misy sessions ao anaty modelView
     * raha toa ka misy de atsoina i fillSessions
     * le checkAuthorisation atsoy ao anaty processRequest, efa misy condition
     */

    public void fillSessions(HttpServletRequest req, HashMap<String, Object> sessionsFromDataObject) {
        for (Map.Entry<String, Object> entry : sessionsFromDataObject.entrySet()) {
            if (sessions.containsValue(entry.getKey())) {
                req.getSession().setAttribute(entry.getKey(), entry.getValue());
            }
        }
    }

    public void checkAuthorisation(Method m, HttpServletRequest req) throws Exception {
        if (m.isAnnotationPresent(Scope.class)) {
            int hierarchieForMethod = m.getAnnotation(Scope.class).hierarchie();
            String sessionProfilName = (String) sessions.get("sessionValue");
            // check si la fonction a besoin d'un identifiant
            if (req.getSession().getAttribute(sessionProfilName) == null) {
                throw new Exception("cette fonction requiert une connexion");
            }
            // fin du checking
            int userProfilHierarchie = (int) req.getSession().getAttribute(sessionProfilName);
            if (hierarchieForMethod > userProfilHierarchie) {
                String exceptionMessage = "Cette méthode ne peut etre appellée par vous ";
                String exceptionExplanation = "vous : " + userProfilHierarchie + " --- la fonction requiert : "
                        + hierarchieForMethod;
                throw new Exception(exceptionMessage + "------" + exceptionExplanation);
            }
        }
    }

    public String changeToJson(ModelView mv) {
        String sessionToJson = "test";
        Gson gson = new Gson();
        sessionToJson = gson.toJson(mv.getData());
        return sessionToJson;
    }
}
