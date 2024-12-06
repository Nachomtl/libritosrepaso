package clases;
    public class Prestamo {
        private int id;
        private int usuarioId;
        private int ejemplarId;
        private String fechaInicio;
        private String fechaDevolucion;

        public Prestamo(int id, int usuarioId, int ejemplarId, String fechaInicio, String fechaDevolucion) {
            this.id = id;
            this.usuarioId = usuarioId;
            this.ejemplarId = ejemplarId;
            this.fechaInicio = fechaInicio;
            this.fechaDevolucion = fechaDevolucion;
        }

        // Getters y setters
        public int getId() {
            return id; }

        public void setId(int id) {
            this.id = id;
        }

        public int getUsuarioId() {
            return usuarioId;
        }
        public void setUsuarioId(int usuarioId) {
            this.usuarioId = usuarioId;
        }

        public int getEjemplarId() {
            return ejemplarId;
        }
        public void setEjemplarId(int ejemplarId) {
            this.ejemplarId = ejemplarId;
        }

        public String getFechaInicio() {
            return fechaInicio;
        }
        public void setFechaInicio(String fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public String getFechaDevolucion() {
            return fechaDevolucion;
        }
        public void setFechaDevolucion(String fechaDevolucion) {
            this.fechaDevolucion = fechaDevolucion;
        }
    }


