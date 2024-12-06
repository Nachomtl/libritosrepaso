package gestiones;

    import DAO.EjemplarDAO;
    import DAO.PrestamoDAO;
    import DAO.UsuarioDAO;
    import clases.Ejemplar;
    import clases.Prestamo;
    import clases.Usuario;
        import java.util.Scanner;

        public class GestionPrestamo {
            private PrestamoDAO prestamoDAO;
            private UsuarioDAO usuarioDAO;
            private EjemplarDAO ejemplarDAO;

            public GestionPrestamo(PrestamoDAO prestamoDAO) {
                this.prestamoDAO = prestamoDAO;
                this.usuarioDAO = usuarioDAO;
                this.ejemplarDAO = ejemplarDAO;
            }

            public void mostrarMenu(Scanner scanner) {
                System.out.println("Gestión de Pre´stamos");
                System.out.println("1. Registrar Préstamo");
                System.out.println("2. Devolver Ejemplar");
                System.out.print("Seleccione una opcion: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (opcion) {
                        case 1 -> registrarPrestamo(scanner);
                        case 2 -> devolverEjemplar(scanner);
                        default -> System.out.println("Opción mala .");
                    }
                } catch (Exception e) {
                    System.out.println("Error al gestionar prestamos: " + e.getMessage());
                }
            }

            private void registrarPrestamo(Scanner scanner) throws Exception {
                System.out.print("DNI del usuario: ");
                String dni = scanner.nextLine();
                Usuario usuario = usuarioDAO.buscarUsuario(dni);

                if (usuario == null) {
                    System.out.println("Usuario no encontrado.");
                    return;
                }

                // Validar penalización activa
                if (usuario.getPenalizacionHasta() != null && java.time.LocalDate.now().isBefore(java.time.LocalDate.parse(usuario.getPenalizacionHasta()))) {
                    System.out.println("El usuario tiene una sancion activa hasta: " + usuario.getPenalizacionHasta());
                    return;
                }

                // Validar préstamos activos
                int prestamosActivos = prestamoDAO.contarPrestamosActivos(usuario.getId());
                if (prestamosActivos >= 3) {
                    System.out.println("ya tiene mas de 3 el usuario.");
                    return;
                }

                System.out.print("ID del ejemplar: ");
                int ejemplarId = scanner.nextInt();
                scanner.nextLine();

                // Validar estado del ejemplar
                Ejemplar ejemplar = ejemplarDAO.buscarEjemplarPorId(ejemplarId);
                if (ejemplar == null || !ejemplar.getEstado().equals("Disponible")) {
                    System.out.println("El ejemplar no estadisponible.");
                    return;
                }

             //registro de prestamo
                String fechaInicio = java.time.LocalDate.now().toString();
                prestamoDAO.registrarPrestamo(new Prestamo(0, usuario.getId(), ejemplarId, fechaInicio, null));
                ejemplarDAO.actualizarEstadoEjemplar(ejemplarId, "Prestado");
                System.out.println("Prestamo registrado exitosamente.");
            }

            private void devolverEjemplar(Scanner scanner) throws Exception {
                System.out.print("ID del prestamo: ");
                int prestamoId = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                Prestamo prestamo = prestamoDAO.buscarPrestamoPorId(prestamoId);
                if (prestamo == null) {
                    System.out.println("Prestamo no encontrado.");
                    return;
                }

                // Actualizar devolución
                String fechaDevolucion = java.time.LocalDate.now().toString();
                prestamoDAO.actualizarDevolucion(prestamoId, fechaDevolucion);
                ejemplarDAO.actualizarEstadoEjemplar(prestamo.getEjemplarId(), "Disponible");

                // Validar devolución fuera de plazo
                java.time.LocalDate fechaLimite = java.time.LocalDate.parse(prestamo.getFechaInicio()).plusDays(15);
                if (java.time.LocalDate.now().isAfter(fechaLimite)) {
                    Usuario usuario = usuarioDAO.buscarUsuarioPorId(prestamo.getUsuarioId());
                    if (usuario != null) {
                        String nuevaPenalizacion = java.time.LocalDate.now().plusDays(15).toString();
                        usuario.setPenalizacionHasta(nuevaPenalizacion);
                        usuarioDAO.actualizarUsuario(usuario);
                        System.out.println("El usuario tiene una sancion hasta: " + nuevaPenalizacion);
                    }
                }

                System.out.println("Ejemplar devuelto exitosamente.");
            }
        }
