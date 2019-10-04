import com.vehicles.project.Car;
import com.vehicles.project.Wheel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main (String[] args)
    {
        /* Request the car information */
        System.out.println("Por favor ingrese los siguientes datos de su coche:");
        /* Create the car */
        Car coche = new Car(getCarDetails ( "Matricula" ), getCarDetails ( "Marca" ), getCarDetails ( "Color" ));
        /* Add the wheels to the car */
        try {
            coche.addWheels(addWheelsInfo("Delanteras"), addWheelsInfo("Traseras"));
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Ha habido un problema agregando las ruedas");
            System.out.println(e.getMessage());
        }
    }
    /* Ask for the car details */
    private static String getCarDetails ( String detail )
    {
        /* Create the Scanner object */
        Scanner sc = new Scanner(System.in);
        System.out.print(detail + ": ");
        String info = sc.nextLine();
        /* Validate data */
        if ( detail == "Matricula")
        {
            String re = "\\d{4}\\w{2,3}"; /* Regular expression */
            Pattern pt = Pattern.compile(re); /* Compiles the regular expression into a pattern */
            Matcher mt = pt.matcher(info); /* Looks for matches */
            if ( ! ( mt.matches() ) )
            {
                System.out.println("La matricula no cumple con el patrón de 4 números y 2 o 3 letras.");
                 info = getCarDetails ( detail );
            }


        }
        return info;
    }
    /* Ask the information of the wheels depending on the location */
    private static List<Wheel> addWheelsInfo (String wheelLocation )
    {
        /* Create the Scanner object */
        Scanner sc = new Scanner(System.in);
        /* Prepare variables for Wheel information */
        String brand;
        String lado;
        double diameter = 0;
        List<Wheel> WheelsList  = new ArrayList<>();

        System.out.println("Por favor ingrese los datos de las ruedas " + wheelLocation + ":");
        System.out.print("Marca: ");
        brand = sc.nextLine();

        /* Get the diameter of the wheel */
        boolean valueNotOk = true;
        do
        {
            try {
                System.out.print("Diametro: ");
                diameter = Double.parseDouble(sc.nextLine());
                /* Validate Diameter */
                if ( diameter < 0.4 || diameter > 4 )
                {
                    throw ( new Exception());
                }
                valueNotOk = false;

            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Ingrese un número valido entre 0.4 y 4.");
            }
        } while ( valueNotOk );


        WheelsList.add( new Wheel( brand, diameter ) );
        WheelsList.add( new Wheel( brand, diameter ) );
        return WheelsList;
    }
}
