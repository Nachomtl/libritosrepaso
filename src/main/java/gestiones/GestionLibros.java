package gestiones;
    import DAO.LibroDAO;
    import clases.Libro;

    import java.util.Scanner;

    public class GestionLibros {
        private final LibroDAO libroDAO;

        public GestionLibros(LibroDAO libroDAO) {
            this.libroDAO = libroDAO;
        }

        public void mostrarMenu(Scanner scanner) {
            System.out.println(" Gestion de Libros");
            System.out.println("1. Registrar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Actualizar Libro");
            System.out.println("4. Borrar Libro");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1 -> registrarLibro(scanner);
                    case 2 -> listarLibros();
                    case 3 -> actualizarLibro(scanner);
                    case 4 -> borrarLibro(scanner);
                    default -> System.out.println("error elija la opcion");
                }
            } catch (Exception e) {
                System.out.println("Error al gestionar libros: " + e.getMessage());
            }
        }

        private void registrarLibro(Scanner scanner) throws Exception {
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Titulo: ");
            String titulo = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            libroDAO.registrarLibro(new Libro(isbn, titulo, autor));
            System.out.println("Libro registrado exitosamente.");
        }

        private void listarLibros() throws Exception {
            libroDAO.listarLibros().forEach(libro ->
                    System.out.println(libro.getIsbn() + " - " + libro.getTitulo() + " - " + libro.getAutor()));
        }

        private void actualizarLibro(Scanner scanner) throws Exception {
            System.out.print("ISBN del libro a actualizar: ");
            String isbn = scanner.nextLine();
            System.out.print("Nuevo ttulo: ");
            String titulo = scanner.nextLine();
            System.out.print("Nuevo autor: ");
            String autor = scanner.nextLine();
            libroDAO.actualizarLibro(new Libro(isbn, titulo, autor));
            System.out.println("Libro actualizado exitosamente.");
        }

        private void borrarLibro(Scanner scanner) throws Exception {
            System.out.print("ISBN del libro a borrar: ");
            String isbn = scanner.nextLine();
            libroDAO.borrarLibro(isbn);
            System.out.println("Libro borrado exitosamente.");
        }
    }


