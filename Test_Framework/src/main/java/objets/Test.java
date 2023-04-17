package objets;

import etu2034.framework.MethodAnnotation;
import etu2034.framework.ModelView;

public class Test {

 @MethodAnnotation(url = "haha")
public ModelView ccc(){
 return new ModelView("view.jsp");
}


}
