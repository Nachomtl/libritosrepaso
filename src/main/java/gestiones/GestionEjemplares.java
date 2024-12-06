package gestiones;
    import DAO.EjemplarDAO;
    import clases.Ejemplar;

    import java.util.Scanner;

    public class GestionEjemplares {
        private final EjemplarDAO ejemplarDAO;

        public GestionEjemplares(EjemplarDAO ejemplarDAO) {
            this.ejemplarDAO = ejemplarDAO;
        }

        public void mostrarMenu(Scanner scanner) {
            System.out.println("Gestion de Ejemplares");
            System.out.println("1. Registrar Ejemplar");
            System.out.println("2. Listar Ejemplares");
            System.out.println("3. Actualizar Estado de Ejemplar");
            System.out.println("4. Borrar Ejemplar");
            System.out.print("Seleccione una opcionn: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1 -> registrarEjemplar(scanner);
                    case 2 -> listarEjemplares();
                    case 3 -> actualizarEstado(scanner);
                    case 4 -> borrarEjemplar(scanner);
                    default -> System.out.println("oprcion ewrroneea ");
                }
            } catch (Exception e) {
                System.out.println("Error al gestionar ejemplares: " + e.getMessage());
            }
        }

        private void registrarEjemplar(Scanner scanner) throws Exception {
            System.out.print("ISBN del libro: ");
            String isbn = scanner.nextLine();
            System.out.print("Estado (Disponible, Prestado, Dañado): ");
            String estado = scanner.nextLine();
            ejemplarDAO.registrarEjemplar(new Ejemplar(0, isbn, estado));
            System.out.println("Ejemplar registrado exitosamente.");
        }

        private void listarEjemplares() throws Exception {
            ejemplarDAO.listarEjemplares().forEach(ejemplar ->
                    System.out.println("ID: " + ejemplar.getId() + ", ISBN: " + ejemplar.getIsbn() + ", Estado: " + ejemplar.getEstado()));
        }

        private void actualizarEstado(Scanner scanner) throws Exception {
            System.out.print("ID del ejemplasera actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nuevo estado (Disponible, Prestado, Dañado): ");
            String estado = scanner.nextLine();
            ejemplarDAO.actualizarEstadoEjemplar(id, estado);
            System.out.println("Estado del ejemplar actualizado exitosamente.");
        }

        private void borrarEjemplar(Scanner scanner) throws Exception {
            System.out.print("ID del ejemplar a borrar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            ejemplarDAO.borrarEjemplar(id);
            System.out.println("Ejemplar borrado exitosamente.");
        }
    }

