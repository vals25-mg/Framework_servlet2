
#L' emplacement qu'on doit utiliser
cd /Users/valisoa/Documents/GitHub/Project/
pwd

#L'emplacement de chaque fichier java
FRONT_SERVLET="/Users/valisoa/Documents/GitHub/Project/Framework/src/main/java/etu2034/framework/servlet/FrontServlet.java"
MAPPING="/Users/valisoa/Documents/GitHub/Project/Framework/src/main/java/etu2034/framework/Mapping.java"
ANNOTATION="/Users/valisoa/Documents/GitHub/Project/Framework/src/main/java/etu2034/framework/MethodAnnotation.java"

#L'emplacement où on doit placer chaque fichier compilé
CLASS_DIR="/Users/valisoa/Documents/GitHub/Project/Framework/src/main/java/etu2034/classes/"

#Nom du fichier jar
JAR_FILE="framework.jar"

#L'emplacement où on doit placer le fichier jar
LIB_DIR="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/webapp/WEB-INF/lib/"

#Compilation des fichiers java
echo "Compilation des fichiers java:"
echo "\t - MethodAnnotation.java"
javac -classpath $CLASSPATH:"$CLASS_DIR" -d "$CLASS_DIR" "$ANNOTATION"
echo "\t - Mapping.java"
javac -classpath $CLASSPATH:"$CLASS_DIR" -d "$CLASS_DIR" "$MAPPING"
echo "\t - FrontServlet.java"
javac -classpath $CLASSPATH:"$CLASS_DIR" -d "$CLASS_DIR" "$FRONT_SERVLET"

#Création fichier jar
jar -cf $LIB_DIR$JAR_FILE $CLASS_DIR"etu2034"
echo "Les contenus du fichier $JAR_FILE:"
jar -tf $LIB_DIR$JAR_FILE

#Effacer les class dans le dossier classes
rm -rf CLASS_DIR"*"
#expoter dans le classpath le fichier framework.jar
export CLASSPATH=$CLASSPATH:.:$LIB_DIR$JAR_FILE

#Emplacement du dossier webapps de tomcat
TOMCAT="/Applications/apache-tomcat-10.0.27/webapps/"

#Emplacement des fichiers java compilés dans le repertoire Test_Framework
WEBAPP="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/webapp/WEB-INF/classes/"
WEBINF="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/webapp/"
JAVA_FILE="/Users/valisoa/Documents/GitHub/Project/Test_Framework/src/main/java/"

#Compilation du fichier java dans le repertoire Test_Framework
echo $JAVA_FILE"*.java"
javac -classpath $CLASSPATH:$WEBAPP -d $WEBAPP $JAVA_FILE"Test.java"

cd $WEBINF
pwd
ls
jar -cf /Applications/apache-tomcat-10.0.27/webapps/test_framework.war ./*
pwd
ls



