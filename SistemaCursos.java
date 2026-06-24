import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SistemaCursos {

    static ArrayList<Curso> cursos = new ArrayList<>();
    static Stack<String> historial = new Stack<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {

            System.out.println("\n===== SISTEMA DE CURSOS UTC =====");
            System.out.println("1. Agregar curso");
            System.out.println("2. Mostrar cursos");
            System.out.println("3. Buscar curso");
            System.out.println("4. Inscribir estudiante");
            System.out.println("5. Dar de baja estudiante");
            System.out.println("6. Eliminar curso");
            System.out.println("7. Historial");
            System.out.println("8. Estadística");
            System.out.println("9. Salir");

            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){

                case 1:
                    agregarCurso();
                    break;

                case 2:
                    mostrarCursos();
                    break;

                case 3:
                    buscarCurso();
                    break;

                case 4:
                    inscribir();
                    break;

                case 5:
                    baja();
                    break;

                case 6:
                    eliminarCurso();
                    break;

                case 7:
                    mostrarHistorial();
                    break;

                case 8:
                    estadisticas();
                    break;

                case 9:
                    System.out.println("Hasta luego.");
                    break;

                default:
                    System.out.println("Opción inválida.");

            }

        } while(opcion != 9);

    }

    static void agregarCurso(){

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Docente: ");
        String docente = sc.nextLine();

        System.out.print("Cupo máximo: ");
        int cupo = sc.nextInt();
        sc.nextLine();

        Curso nuevo = new Curso(clave,nombre,docente,cupo);

        cursos.add(nuevo);

        historial.push("Se agregó el curso " + nombre);

        System.out.println("Curso agregado.");
    }

    static void mostrarCursos(){

        if(cursos.isEmpty()){
            System.out.println("No hay cursos.");
            return;
        }

        for(Curso c : cursos){
            System.out.println(c);
        }

    }

    static void buscarCurso(){

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        for(Curso c : cursos){

            if(c.getClave().equalsIgnoreCase(clave)){
                System.out.println(c);
                return;
            }

        }

        System.out.println("Curso no encontrado.");

    }

    static void inscribir(){

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        for(Curso c : cursos){

            if(c.getClave().equalsIgnoreCase(clave)){

                if(c.getInscritos() < c.getCupoMaximo()){

                    c.inscribir();
                    historial.push("Se inscribió un estudiante en " + c.getNombre());
                    System.out.println("Inscripción realizada.");

                }else{

                    System.out.println("Curso lleno.");

                }

                return;

            }

        }

        System.out.println("Curso no encontrado.");

    }

    static void baja(){

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        for(Curso c : cursos){

            if(c.getClave().equalsIgnoreCase(clave)){

                if(c.getInscritos()>0){

                    c.baja();
                    historial.push("Se dio de baja un estudiante de " + c.getNombre());
                    System.out.println("Baja realizada.");

                }else{

                    System.out.println("No hay alumnos inscritos.");

                }

                return;

            }

        }

        System.out.println("Curso no encontrado.");

    }

    static void eliminarCurso(){

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        for(Curso c : cursos){

            if(c.getClave().equalsIgnoreCase(clave)){

                cursos.remove(c);

                historial.push("Se eliminó el curso " + c.getNombre());

                System.out.println("Curso eliminado.");

                return;

            }

        }

        System.out.println("Curso no encontrado.");

    }

    static void mostrarHistorial(){

        if(historial.isEmpty()){

            System.out.println("No hay historial.");
            return;

        }

        for(String h : historial){

            System.out.println(h);

        }

    }

    static void estadisticas(){

        int total = cursos.size();
        int inscritos = 0;

        for(Curso c : cursos){
            inscritos += c.getInscritos();

        }

        System.out.println("Cursos registrados: " + total);
        System.out.println("Total de estudiantes inscritos: " + inscritos);

    }

}