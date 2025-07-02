package Appsweb.tarea4.repository;

import Appsweb.tarea4.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    
    @Query("SELECT a FROM Actividad a WHERE a.fechaTermino < :fechaActual")
    List<Actividad> findActividadesRealizadas(LocalDateTime fechaActual);
}