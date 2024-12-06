package clases;

import java.sql.SQLException;

public class Usuario {
        private int id;
        private String dni;
        private String nombre;
        private String email;
        private String password;
        private String tipo; // normal o administrador
        private String penalizacionHasta;

        public Usuario(int id, String dni, String nombre, String email, String password, String tipo, String penalizacionHasta) {
            this.id = id;
            this.dni = dni;
            this.nombre = nombre;
            this.email = email;
            this.password = password;
            this.tipo = tipo;
            this.penalizacionHasta = penalizacionHasta;
        }

        // Getters y setters
        public int getId() {
            return id; }
        public void setId(int id) {
            this.id = id; }

        public String getDni() {
            return dni; }
        public void setDni(String dni) {
            this.dni = dni; }

        public String getNombre() {
            return nombre; }
        public void setNombre(String nombre) {
            this.nombre = nombre; }

        public String getEmail() {
            return email; }
        public void setEmail(String email) {
            this.email = email; }

        public String getPassword() {
            return password; }
        public void setPassword(String password) {
            this.password = password; }

        public String getTipo() {
            return tipo; }
        public void setTipo(String tipo) {
            this.tipo = tipo; }

        public String getPenalizacionHasta() {
            return penalizacionHasta;
        }
        public void setPenalizacionHasta(String penalizacionHasta) {
            this.penalizacionHasta = penalizacionHasta;
        }

    }

