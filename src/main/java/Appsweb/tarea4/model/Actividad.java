package Appsweb.tarea4.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "actividad")
public class Actividad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "dia_hora_inicio")
    private LocalDateTime fechaInicio;
    
    @Column(name = "dia_hora_termino")
    private LocalDateTime fechaTermino;
    
    @Column(name = "sector")
    private String sector;
    
    @Column(name = "nombrer")
    private String nombreOrganizador;
    
    @Column(name = "tema")
    private String tema;
    
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Nota> notas;
    
    // Constructores
    public Actividad() {}
    
    public Actividad(LocalDateTime fechaInicio, LocalDateTime fechaTermino, String sector, 
                    String nombreOrganizador, String tema) {
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.sector = sector;
        this.nombreOrganizador = nombreOrganizador;
        this.tema = tema;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public LocalDateTime getFechaTermino() { return fechaTermino; }
    public void setFechaTermino(LocalDateTime fechaTermino) { this.fechaTermino = fechaTermino; }
    
    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
    
    public String getNombreOrganizador() { return nombreOrganizador; }
    public void setNombreOrganizador(String nombreOrganizador) { this.nombreOrganizador = nombreOrganizador; }
    
    public String getTema() { return tema; }
    public void setTema(String tema) { this.tema = tema; }
    
    public List<Nota> getNotas() { return notas; }
    public void setNotas(List<Nota> notas) { this.notas = notas; }
    
    // Método para calcular promedio de notas
    public Double getPromedio() {
        if (notas == null || notas.isEmpty()) {
            return null;
        }
        return notas.stream()
                   .mapToInt(Nota::getValor)
                   .average()
                   .orElse(0.0);
    }
}