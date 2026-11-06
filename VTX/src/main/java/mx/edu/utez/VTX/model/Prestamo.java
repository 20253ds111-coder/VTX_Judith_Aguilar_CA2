package mx.edu.utez.VTX.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreSolicitante;

    @Column(nullable = false)
    private String fechaPrestamo;

    @Column(nullable = true)
    private String fechaDevolucion;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "videojuego_id", nullable = false)
    private VideoJuego videoJuego;

    public Prestamo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreSolicitante() { return nombreSolicitante; }
    public void setNombreSolicitante(String nombreSolicitante) { this.nombreSolicitante = nombreSolicitante; }

    public String getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(String fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public String getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(String fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public VideoJuego getVideoJuego() { return videoJuego; }
    public void setVideoJuego(VideoJuego videoJuego) { this.videoJuego = videoJuego; }
}
