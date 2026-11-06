package mx.edu.utez.VTX.model;

import jakarta.persistence.*;

@Entity
@Table(name = "videojuegos")
public class VideoJuego {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private String plataforma;

        @Column(nullable = false)
        private Boolean disponible;

        public VideoJuego() {}

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getPlataforma() { return plataforma; }
        public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

        public Boolean getDisponible() { return disponible; }
        public void setDisponible(Boolean disponible) { this.disponible = disponible; }
}
