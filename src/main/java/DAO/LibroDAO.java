package DAO;
import clases.Libro;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class LibroDAO {
        public void registrarLibro(Libro libro) throws SQLException {
            String sql = "INSERT INTO Libro (isbn, titulo, autor) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, libro.getIsbn());
                stmt.setString(2, libro.getTitulo());
                stmt.setString(3, libro.getAutor());
                stmt.executeUpdate();
            }
        }

        public List<Libro> listarLibros() throws SQLException {
            List<Libro> libros = new ArrayList<>();
            String sql = "SELECT * FROM Libro";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    libros.add(new Libro(rs.getString("isbn"), rs.getString("titulo"), rs.getString("autor")));
                }
            }
            return libros;
        }

        public void actualizarLibro(Libro libro) throws SQLException {
            String sql = "UPDATE Libro SET titulo = ?, autor = ? WHERE isbn = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, libro.getTitulo());
                stmt.setString(2, libro.getAutor());
                stmt.setString(3, libro.getIsbn());
                stmt.executeUpdate();
            }
        }

        public void borrarLibro(String isbn) throws SQLException {
            String sql = "DELETE FROM Libro WHERE isbn = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, isbn);
                stmt.executeUpdate();
            }
        }
    }


