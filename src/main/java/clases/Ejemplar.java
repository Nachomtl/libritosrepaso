package clases;
    public class Ejemplar {
        private int id;
        private String isbn;
        private String estado;

        public Ejemplar(int id, String isbn, String estado) {
            this.id = id;
            this.isbn = isbn;
            this.estado = estado;
        }

        // Getters y setters
        public int getId() {
            return id; }
        public void setId(int id) {
            this.id = id; }

        public String getIsbn() {
            return isbn; }
        public void setIsbn(String isbn) {
            this.isbn = isbn; }

        public String getEstado() {
            return estado; }
        public void setEstado(String estado) {
            this.estado = estado; }
    }


