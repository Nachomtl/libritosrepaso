package gestiones;
import DAO.UsuarioDAO;
import clases.Usuario;
import java.util.Scanner;

        public class GestionUsuario {
            private final UsuarioDAO usuarioDAO;

            public GestionUsuario(UsuarioDAO usuarioDAO) {
                this.usuarioDAO = usuarioDAO;
            }

            public void mostrarMenu(Scanner scanner) {
                System.out.println("Gestion de Usuarios");
                System.out.println("1. Registrar Usuario");
                System.out.println("2. Listar Usuarios");
                System.out.println("3. Actualizar Usuario");
                System.out.println("4. Borrar Usuario");
                System.out.print("elija una opcion: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                try {
                    switch (opcion) {
                        case 1 -> registrarUsuario(scanner);
                        case 2 -> listarUsuarios();
                        case 3 -> actualizarUsuario(scanner);
                        case 4 -> borrarUsuario(scanner);
                        default -> System.out.println("Opción mala ");
                    }
                } catch (Exception e) {
                    System.out.println("Error al gestionar usuarios: " + e.getMessage());
                }
            }

            private void registrarUsuario(Scanner scanner) throws Exception {
                System.out.print("DNI: ");
                String dni = scanner.nextLine();
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("la contraseña: ");
                String password = scanner.nextLine();
                System.out.print("Tipo (normal, administrador): ");
                String tipo = scanner.nextLine();

                // Registrar el usuario en la base de datos
                usuarioDAO.registrarUsuario(new Usuario(0, dni, nombre, email, password, tipo, null));
                System.out.println("Usuario registrado exitosamente.");
            }

            private void listarUsuarios() throws Exception {
                // Listar todos los usuarios desde la base de datos
                usuarioDAO.listarUsuarios().forEach(usuario ->
                        System.out.println("DNI: " + usuario.getDni() +
                                ", Nombre: " + usuario.getNombre() +
                                ", Tipo: " + usuario.getTipo()));
            }

            private void actualizarUsuario(Scanner scanner) throws Exception {
                System.out.print("DNI del usuario a actualizar: ");
                String dni = scanner.nextLine();

                // Pedir nuevos datos para actualizar ek usuario
                System.out.print("Nuevo nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Nuevo email: ");
                String email = scanner.nextLine();
                System.out.print("Nuevo password: ");
                String password = scanner.nextLine();
                System.out.print("Nuevo tipo (normal, administrador): ");
                String tipo = scanner.nextLine();

                // Actualizar usuario
                usuarioDAO.actualizarUsuario(new Usuario(0, dni, nombre, email, password, tipo, null));
                System.out.println("Usuario actualizado exitosamente.");
            }

            private void borrarUsuario(Scanner scanner) throws Exception {
                System.out.print("DNI del usuario a borrar: ");
                String dni = scanner.nextLine();

                // Eliminar el usuario
                usuarioDAO.borrarUsuario(dni);
                System.out.println("Usuario borrado exitosamente.");
            }
        }
