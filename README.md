# Framework_servlet2

### - framework.jar:


##### - Mise en place environnement:
                - importation etu2034.framework.MethodAnnotation
                - importation etu2034.framework.ModelView;
                - import jakarta.servlet.*   

##### - web.xml:
    <servlet>
        <servlet-name>FrontServlet</servlet-name>
        <servlet-class>etu2034.framework.servlet.FrontServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>FrontServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>


### -SPRINT2:

##### - Création d'une classe Mapping avec 2 attributs (package etu2034.framework):
                - className de type String
                - Method de type String
##### - Création d'un attribut MappingUrls de type HashMap<String,Mapping> dans la classe FrontServlet
