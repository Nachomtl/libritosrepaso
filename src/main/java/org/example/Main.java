package org.example;
        import DAO.EjemplarDAO;
        import DAO.LibroDAO;
        import DAO.PrestamoDAO;
        import DAO.UsuarioDAO;
        import gestiones.GestionEjemplares;
        import gestiones.GestionLibros;
        import gestiones.GestionPrestamo;
        import gestiones.GestionUsuario;

        import java.util.Scanner;

        public class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Instanciar los DAOs
                LibroDAO libroDAO = new LibroDAO();
                EjemplarDAO ejemplarDAO = new EjemplarDAO();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                PrestamoDAO prestamoDAO = new PrestamoDAO();

                // Instanciar las clases de gestión
                GestionLibros gestionLibros = new GestionLibros(libroDAO);
                GestionEjemplares gestionEjemplares = new GestionEjemplares(ejemplarDAO);
                GestionUsuario gestionUsuarios = new GestionUsuario(usuarioDAO);
                GestionPrestamo gestionPrestamos = new GestionPrestamo(prestamoDAO);

                int opcion;
                do {
                    System.out.println(" Menu ");
                    System.out.println("1. Gestionar Libros");
                    System.out.println("2. Gestionar Ejemplares");
                    System.out.println("3. Gestionar Usuarios");
                    System.out.println("4. Gestionar Préstamos");
                    System.out.println("5. Salir");
                    System.out.print("Seleccione una opción adecuada a suas necesidades: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 1 -> gestionLibros.mostrarMenu(scanner);
                        case 2 -> gestionEjemplares.mostrarMenu(scanner);
                        case 3 -> gestionUsuarios.mostrarMenu(scanner);
                        case 4 -> gestionPrestamos.mostrarMenu(scanner);
                        case 5 -> System.out.println("saliendo");
                        default -> System.out.println("error, Intente nuevamente.");
                    }
                } while (opcion != 5);

                scanner.close();
            }
        }
