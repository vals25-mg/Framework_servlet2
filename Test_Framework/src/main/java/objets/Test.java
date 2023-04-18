package objets;

import etu2034.framework.MethodAnnotation;
import etu2034.framework.ModelView;

public class Test {

    @MethodAnnotation(url = "emp-list")
    public ModelView emplist(){
      ModelView mv=new ModelView("view.jsp");
      mv.addItem("Emp1",23000);
      mv.addItem("Emp2",35000);
     return mv;
    }


}
