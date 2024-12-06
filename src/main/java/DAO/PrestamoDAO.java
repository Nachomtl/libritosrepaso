package DAO;
import clases.Prestamo;
import org.example.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

        public class PrestamoDAO {

            // registro
            public void registrarPrestamo(Prestamo prestamo) throws SQLException {
                String sql = "INSERT INTO Prestamo (usuario_id, ejemplar_id, fechaInicio) VALUES (?, ?, ?)";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, prestamo.getUsuarioId());
                    stmt.setInt(2, prestamo.getEjemplarId());
                    stmt.setString(3, prestamo.getFechaInicio());
                    stmt.executeUpdate();
                }
            }

            // todos
            public List<Prestamo> listarPrestamos() throws SQLException {
                List<Prestamo> prestamos = new ArrayList<>();
                String sql = "SELECT * FROM Prestamo";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        prestamos.add(new Prestamo(
                                rs.getInt("id"),
                                rs.getInt("usuario_id"),
                                rs.getInt("ejemplar_id"),
                                rs.getString("fechaInicio"),
                                rs.getString("fechaDevolucion")
                        ));
                    }
                }
                return prestamos;
            }

            //actualizarlos
            public void actualizarDevolucion(int id, String fechaDevolucion) throws SQLException {
                String sql = "UPDATE Prestamo SET fechaDevolucion = ? WHERE id = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, fechaDevolucion);
                    stmt.setInt(2, id);
                    stmt.executeUpdate();
                }
            }

            // Mborrar por id
            public void borrarPrestamo(int id) throws SQLException {
                String sql = "DELETE FROM Prestamo WHERE id = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }
            }

            // prestaos activos usiuario sin fechaa
            public int contarPrestamosActivos(int usuarioId) throws SQLException {
                String sql = "SELECT COUNT(*) AS activos FROM Prestamo WHERE usuario_id = ? AND fechaDevolucion IS NULL";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, usuarioId);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        return rs.getInt("activos");
                    }
                }
                return 0;
            }

            // busqueda de id
            public Prestamo buscarPrestamoPorId(int id) throws SQLException {
                String sql = "SELECT * FROM Prestamo WHERE id = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        return new Prestamo(
                                rs.getInt("id"),
                                rs.getInt("usuario_id"),
                                rs.getInt("ejemplar_id"),
                                rs.getString("fechaInicio"),
                                rs.getString("fechaDevolucion")
                        );
                    }
                }
                return null;
            }
        }
