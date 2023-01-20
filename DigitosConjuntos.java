/*
*Desarrollar una aplicación en java con archivos con los métodos de :
	• Crear un archivo
	• Generar aleatoriamente n números enteros y guardarlos en el archivo.
	• Leer los números del archivo e imprimirlos.
	• Leer los números del archivo y pasar los pares al conjunto n-pares de tipo  TREESET, imprimir el conjunto
	• Leer los números del archivo y pasar los impares al conjunto n-impares del tipo TREESET, imprimir el conjunto
	• Realizar la unión de los conjuntos npares e impares en un conjunto universal números de tipo TREESET
	• Validar si existe el archivo
Mostrar la  útima fecha de modificación
*/

import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

class DigitosConjuntos {
    String archivo = "C:\\Users\\erick\\OneDrive\\UAdeC\\src\\Sistemas\\DA.txt";
    TreeSet even;
    TreeSet odd;
    int[] contenidoArchivo;

    public static void main(String[] args) {

        int numQty = 10;

        DigitosConjuntos dc = new DigitosConjuntos();
        dc.crearArchivo(dc.archivo);
        dc.generarAleatoriosSave(numQty);
        dc.leerArchivoImprimir(dc.archivo);
        dc.filtrarPares();
        dc.filtrarImpares();
        dc.unirConjuntos();
        dc.validarArchivo(dc.archivo);
        dc.imprimirModificacion(dc.archivo);

    }

    public void crearArchivo(String filename) {

        try {
            File file = new File(filename);
            // Si el archivo no existe es creado
            if (!this.validarArchivo(filename)) {
                file.createNewFile();
            }
        } catch (Exception exc) {

        }
    }

    public void generarAleatoriosSave(int numQty) {
        int[] aleatorios = this.generarAleatorios(numQty);
        this.guardarArchivo(aleatorios);
    }

    public void leerArchivoImprimir(String filename) {
        this.leerArchivo(filename);
        this.imprimirContenido();
    }

    public int[] generarAleatorios(int n) {
        int[] functionResult = new int[n];
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            functionResult[i] = (int) (ran.nextDouble() * 10 + 0);
        }
        return functionResult;
    }

    public void guardarArchivo(int[] aleatorios) {
        try {
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            // do{
            for (int i = 0; i < aleatorios.length; i++) {
                out.print(aleatorios[i] + "\n");
            }
            out.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println(
                    "archivo No Existe");
        } catch (IOException ioe) {
            System.out.println("no se puede hacer lectura");
        }
    }

    public void leerArchivo(String filename) {
        try {
            BufferedReader obj = new BufferedReader(new FileReader(new File(filename)));

            String line;
            ArrayList<Integer> dynamicArray = new ArrayList();
            while ((line = obj.readLine()) != null) {
                System.out.print(line + ", ");
                dynamicArray.add(Integer.parseInt(line));
            }
            contenidoArchivo = convertIntegers(dynamicArray);
        } catch (Exception exc) {

        }
    }

    public static int[] convertIntegers(ArrayList <Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    public void imprimirContenido() {

    }

    public void filtrarPares() {
        even = new TreeSet();
        for (int i = 0; i < contenidoArchivo.length; i++) {
            if (contenidoArchivo[i] % 2 == 0) {
                even.add(contenidoArchivo[i]);
            }
        }
    }

    public void filtrarImpares() {
        odd = new TreeSet();
        for (int i = 0; i < contenidoArchivo.length; i++) {
            if (contenidoArchivo[i] % 2 != 0) {
                // impar
                odd.add(contenidoArchivo[i]);
            }
        }
    }

    public boolean validarArchivo(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    public void imprimirModificacion(String filename) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
        System.out.println("\nDate of modification: \n" + dateFormat.format(new File(filename).lastModified()));
    }

    public void unirConjuntos() {
        TreeSet union = new TreeSet(even);
        union.addAll(odd);

        System.out.println("\nEven: " + even.toString());
        System.out.println("Odd: " + odd.toString());
        System.out.println("Union: " + union.toString());
    }
}