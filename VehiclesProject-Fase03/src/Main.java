import com.vehicles.project.Bike;
import com.vehicles.project.Car;
import com.vehicles.project.Vehicle;
import com.vehicles.project.Wheel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main (String[] args)
    {
        char transportationType = getTransportationType();
        Vehicle transportation = null;
        if ( transportationType == 'c')
        {
            transportation = createCar();
        }
        else if ( transportationType == 'm')
        {
            transportation = createBike();
        }
        System.out.println("Se ha registrado correctamente.");
    }

    /**
     * Metodo que pregunta al usuario si quiere registrar coche o moto
     * @return c - coche / m - moto
     */
    private static char getTransportationType ()
    {
        String transportationType;
        Scanner sc = new Scanner(System.in);
        /* we ask for the transportation type, until we get a coche or moto */
        do
        {
            System.out.println("¿Desea registrar un coche o una moto?");
            transportationType = sc.nextLine();
            if ( (! transportationType.equals("coche"))  && (! transportationType.equals("moto") ) )
            {
                System.out.println("Error: debe escoger entre coche y moto");
            }
        } while ( (! transportationType.equals("coche"))  && (! transportationType.equals("moto")));

        /* based on the selection we return c for coche or m for moto */
        if ( transportationType.equals("coche") )
        {
            return 'c';
        }
        else if ( transportationType.equals("moto") )
        {
            return 'm';
        }
        return '-';
    }

    /**
     * Metodo que crea el coche
     * @return
     */
    private static Car createCar ()
    {
        /* Request the car information */
        System.out.println("Por favor ingrese los siguientes datos de su coche:");
        /* Create the car */
        Car coche = new Car(getDetails ( "Matricula" ), getDetails ( "Marca" ), getDetails ( "Color" ));
        /* Add the wheels to the car */
        try {
            coche.addWheels(addWheelsInfo("Delanteras", 'c'), addWheelsInfo("Traseras", 'c'));
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Ha habido un problema agregando las ruedas");
            System.out.println(e.getMessage());
        }
        return coche;
    }
    /**
     * Metodo que crea el coche
     * @return
     */
    private static Bike createBike () {
        /* Request the car information */
        System.out.println("Por favor ingrese los siguientes datos de su moto:");
        /* Create the car */
        Bike bike = new Bike(getDetails ( "Matricula" ), getDetails ( "Marca" ), getDetails ( "Color" ));
        /* Add the wheels to the car */
        try {
            bike.addWheels(addWheelsInfo("Delanteras", 'm'), addWheelsInfo("Traseras", 'm'));
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Ha habido un problema agregando las ruedas");
            System.out.println(e.getMessage());
        }
        return bike;
    }
    /**
     * Pregunta los detalles (revisa que la matricula cumpla con los requerimientos)
     * @param detail - Matricula / Marca / Color
     * @return el valor introducido
     */
    private static String getDetails ( String detail )
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
                 info = getDetails ( detail );
            }


        }
        return info;
    }

    /**
     * Agrega la información de las ruedas
     * @param wheelLocation - delantera / trasera
     * @return Listado de las dos ruedas traseras o delanteras.
     */
    private static List<Wheel> addWheelsInfo (String wheelLocation, char transportationType )
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

        /* Si estamo agregando un a moto preguntamos el número de ruedas */
        int numWheels = 0;
        valueNotOk = true;
        if (transportationType == 'm')
        {
            try {
                System.out.println("¿Cuantas ruedas " + wheelLocation + " tiene?");
                numWheels = Integer.parseInt(sc.nextLine());
                /* Validate Diameter */
                if ( numWheels > 4 )
                {
                    throw ( new Exception());
                }
                valueNotOk = false;

            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Ingrese un número valido entre 1 y 4");
            }
        }
        else if (transportationType == 'm')
        {
            numWheels = 2;
        }
        for ( int i = 0; i < numWheels; i++)
        {
            WheelsList.add( new Wheel( brand, diameter ) );
        }
        return WheelsList;
    }
}
