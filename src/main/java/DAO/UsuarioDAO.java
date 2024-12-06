package DAO;
import clases.Usuario;
import org.example.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

        public class UsuarioDAO {

            // Registrar un nuevo usuario en la base de datos
            public void registrarUsuario(Usuario usuario) throws SQLException {
                String sql = "INSERT INTO Usuario (dni, nombre, email, password, tipo, penalizacionHasta) VALUES (?, ?, ?, ?, ?, ?)";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, usuario.getDni());
                    stmt.setString(2, usuario.getNombre());
                    stmt.setString(3, usuario.getEmail());
                    stmt.setString(4, usuario.getPassword());
                    stmt.setString(5, usuario.getTipo());
                    stmt.setString(6, usuario.getPenalizacionHasta());
                    stmt.executeUpdate();
                }
            }

            // Listar todos
            public List<Usuario> listarUsuarios() throws SQLException {
                List<Usuario> usuarios = new ArrayList<>();
                String sql = "SELECT * FROM Usuario";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        usuarios.add(new Usuario(
                                rs.getInt("id"),
                                rs.getString("dni"),
                                rs.getString("nombre"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("tipo"),
                                rs.getString("penalizacionHasta")
                        ));
                    }
                }
                return usuarios;
            }

            // Actualizar
            public void actualizarUsuario(Usuario usuario) throws SQLException {
                String sql = "UPDATE Usuario SET nombre = ?, email = ?, password = ?, tipo = ?, penalizacionHasta = ? WHERE dni = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, usuario.getNombre());
                    stmt.setString(2, usuario.getEmail());
                    stmt.setString(3, usuario.getPassword());
                    stmt.setString(4, usuario.getTipo());
                    stmt.setString(5, usuario.getPenalizacionHasta());
                    stmt.setString(6, usuario.getDni());
                    stmt.executeUpdate();
                }
            }

            // Borrar  pofr dni
            public void borrarUsuario(String dni) throws SQLException {
                String sql = "DELETE FROM Usuario WHERE dni = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, dni);
                    stmt.executeUpdate();
                }
            }

            // Buscar por dnii
            public Usuario buscarUsuario(String dni) throws SQLException {
                String sql = "SELECT * FROM Usuario WHERE dni = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, dni);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        return new Usuario(
                                rs.getInt("id"),
                                rs.getString("dni"),
                                rs.getString("nombre"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("tipo"),
                                rs.getString("penalizacionHasta")
                        );
                    }
                }
                return null;
            }

            // Buscar un usuario por su ID
            public Usuario buscarUsuarioPorId(int id) throws SQLException {
                String sql = "SELECT * FROM Usuario WHERE id = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        return new Usuario(
                                rs.getInt("id"),
                                rs.getString("dni"),
                                rs.getString("nombre"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("tipo"),
                                rs.getString("penalizacionHasta")
                        );
                    }
                }
                return null;
            }
        }
