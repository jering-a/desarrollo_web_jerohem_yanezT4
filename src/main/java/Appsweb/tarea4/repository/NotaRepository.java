package Appsweb.tarea4.repository;

import Appsweb.tarea4.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    
    @Query("SELECT AVG(n.valor) FROM Nota n WHERE n.actividad.id = :actividadId")
    Double calcularPromedioNotas(@Param("actividadId") Long actividadId);
}