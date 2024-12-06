package clases;
    public class Libro {
        private String isbn;
        private String titulo;
        private String autor;

        public Libro(String isbn, String titulo, String autor) {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autor = autor;
        }

        // Getters y setters
        public String getIsbn() {
            return isbn; }
        public void setIsbn(String isbn) {
            this.isbn = isbn; }

        public String getTitulo() {
            return titulo; }
        public void setTitulo(String titulo) {
            this.titulo = titulo; }

        public String getAutor() {
            return autor; }
        public void setAutor(String autor) {
            this.autor = autor; }
    }


