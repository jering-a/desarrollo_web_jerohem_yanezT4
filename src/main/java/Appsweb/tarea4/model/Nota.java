package Appsweb.tarea4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nota")
public class Nota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nota", nullable = false)
    private Integer valor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;
    
    // Constructores
    public Nota() {}
    
    public Nota(Integer valor, Actividad actividad) {
        this.valor = valor;
        this.actividad = actividad;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getValor() { return valor; }
    public void setValor(Integer valor) { this.valor = valor; }
    
    public Actividad getActividad() { return actividad; }
    public void setActividad(Actividad actividad) { this.actividad = actividad; }
}