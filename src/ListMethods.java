import java.lang.reflect.Method;

/**
 *
 * @author javadb.com
 */
public class ListMethods {
    
    /**
     * Lists the methods of a class using the Reflection api.
     */
    public void listMethodsUsingReflection() {

        //Obtain the Class instance
        Class personClass = QuadrooqlS.class;
        
        //Get the methods
        Method[] methods = personClass.getDeclaredMethods();
        
        //Loop through the methods and print out their names
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ListMethods().listMethodsUsingReflection();
    }
}  