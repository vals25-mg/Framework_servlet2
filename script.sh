
#L' emplacement qu'on doit utiliser
cd /Users/valisoa/Documents/GitHub/Project/Framework/src/main/java/
pwd

#L'emplacement de chaque fichier java
FRONT_SERVLET="etu2034/framework/servlet/FrontServlet.java"
MAPPING="etu2034/framework/Mapping.java"
ANNOTATION="etu2034/framework/MethodAnnotation.java"
MODELVIEW="etu2034/framework/ModelView.java"
FILEUPLOAD="etu2034/framework/FileUpload.java"
SCOPE="etu2034/framework/Scope.java"
SINGLETON="etu2034/framework/Singleton.java"

#L'emplacement où on doit placer chaque fichier compilé
CLASS_DIR="/Users/valisoa/Documents/GitHub/Project/Framework/src/main/java/etu2034/classes/"

#Nom du fichier jar
JAR_FILE="framework.jar"

#Emplacement du fichier Gson
GSON_DIR="/Users/valisoa/Documents/GitHub/Project/Framework/src/main/webapp/WEB-INF/lib/gson-2.8.2.jar"

#L'emplacement où on doit placer le fichier jar
LIB_DIR="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/webapp/WEB-INF/lib/"

#Compilation des fichiers java
echo "Compilation des fichiers java:"
echo "\t - MethodAnnotation.java"
javac -parameters -classpath $CLASSPATH:"$CLASS_DIR$ANNOTATION" -d "$CLASS_DIR" "$ANNOTATION"
echo "\t - Mapping.java"
javac -parameters -classpath $CLASSPATH:"$CLASS_DIR$MAPPING" -d "$CLASS_DIR" "$MAPPING"
echo "\t - ModelView.java"
javac -parameters -classpath $CLASSPATH:"$CLASS_DIR$MODELVIEW" -d "$CLASS_DIR" "$MODELVIEW"
echo "\t - FileUpload.java: "
javac -parameters -classpath $CLASSPATH:"$CLASS_DIR$FILEUPLOAD" -d "$CLASS_DIR" "$FILEUPLOAD"
echo "\t - Scope.java: "
javac -parameters -classpath $CLASSPATH:"$CLASS_DIR$SCOPE" -d "$CLASS_DIR" "$SCOPE"
echo "\t - Singleton.java: "
javac -parameters -classpath $CLASSPATH:"$CLASS_DIR$SINGLETON" -d "$CLASS_DIR" "$SINGLETON"
echo "\t - FrontServlet.java"
javac -parameters -classpath $CLASSPATH:"$GSON_DIR"  -d "$CLASS_DIR" "$FRONT_SERVLET"


#Création fichier jar
cd $CLASS_DIR
jar -cf $LIB_DIR$JAR_FILE "etu2034"
echo "Les contenus du fichier $JAR_FILE:"
jar -tf $LIB_DIR$JAR_FILE


#Effacer les classes dans le dossier classes
rm -rf CLASS_DIR"*"
#expoter dans le classpath le fichier framework.jar
export CLASSPATH=$CLASSPATH:.:$LIB_DIR$JAR_FILE

cd /Users/valisoa/Documents/GitHub/Project/
#Emplacement du dossier webapps de tomcat
TOMCAT="/Applications/apache-tomcat-10.0.27/webapps/"

#Emplacement des fichiers java compilés dans le repertoire Test_Framework
WEBAPP="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/webapp/WEB-INF/classes/"
WEBINF="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/webapp/"
JAVA_FILE="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/java/"
WAR_FILE="/Applications/apache-tomcat-10.0.27/webapps/test_framework.war"

#Compilation du fichier java dans le repertoire Test_Framework
#echo $JAVA_FILE"*.java"
javac -parameters -classpath $CLASSPATH:$WEBAPP -d $WEBAPP $JAVA_FILE"objets/Test.java"
javac -parameters -classpath $CLASSPATH:$WEBAPP -d $WEBAPP $JAVA_FILE"objets/Employe.java"

#Copie des fichiers dans dossier temporaire
#cd $WEBINF
echo "Placement:"
pwd
 cp -R  /Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/webapp/*  ~/Documents/temporary
cd ~/Documents/temporary
pwd

ls
rm -rf $WAR_FILE
jar -cf $WAR_FILE .
jar -tf $WAR_FILE
cd /Applications/apache-tomcat-10.0.27/webapps/test_framework/
pwd
ls

/Applications/apache-tomcat-10.0.27/bin/shutdown.sh
/Applications/apache-tomcat-10.0.27/bin/startup.sh



