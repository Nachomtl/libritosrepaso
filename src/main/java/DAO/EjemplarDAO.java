package DAO;
import clases.Ejemplar;
import org.example.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

        public class EjemplarDAO {

            //registrar ejemplar
            public void registrarEjemplar(Ejemplar ejemplar) throws SQLException {
                String sql = "INSERT INTO Ejemplar (isbn, estado) VALUES (?, ?)";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, ejemplar.getIsbn());
                    stmt.setString(2, ejemplar.getEstado());
                    stmt.executeUpdate();
                }
            }

            // listar todos
            public List<Ejemplar> listarEjemplares() throws SQLException {
                List<Ejemplar> ejemplares = new ArrayList<>();
                String sql = "SELECT * FROM Ejemplar";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        ejemplares.add(new Ejemplar(
                                rs.getInt("id"),
                                rs.getString("isbn"),
                                rs.getString("estado")
                        ));
                    }
                }
                return ejemplares;
            }

            //buscar port id
            public Ejemplar buscarEjemplarPorId(int id) throws SQLException {
                String sql = "SELECT * FROM Ejemplar WHERE id = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        return new Ejemplar(
                                rs.getInt("id"),
                                rs.getString("isbn"),
                                rs.getString("estado")
                        );
                    }
                }
                return null; // Si no se encuentra el ejemplar
            }

            // para actualizar
            public void actualizarEstadoEjemplar(int id, String estado) throws SQLException {
                String sql = "UPDATE Ejemplar SET estado = ? WHERE id = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, estado);
                    stmt.setInt(2, id);
                    stmt.executeUpdate();
                }
            }

            //borrar
            public void borrarEjemplar(int id) throws SQLException {
                String sql = "DELETE FROM Ejemplar WHERE id = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }
            }
        }
