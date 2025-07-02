package Appsweb.tarea4.service;

import Appsweb.tarea4.model.Actividad;
import Appsweb.tarea4.model.Nota;
import Appsweb.tarea4.repository.ActividadRepository;
import Appsweb.tarea4.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {
    
    @Autowired
    private ActividadRepository actividadRepository;
    
    @Autowired
    private NotaRepository notaRepository;
    
    public List<Actividad> obtenerActividadesRealizadas() {
        return actividadRepository.findActividadesRealizadas(LocalDateTime.now());
    }
    
    public Optional<Actividad> obtenerActividadPorId(Long id) {
        return actividadRepository.findById(id);
    }
    
    @Transactional
    public boolean agregarNota(Long actividadId, Integer valorNota) {
        if (valorNota < 1 || valorNota > 7) {
            return false;
        }
        
        Optional<Actividad> actividadOpt = actividadRepository.findById(actividadId);
        if (actividadOpt.isPresent()) {
            Nota nota = new Nota(valorNota, actividadOpt.get());
            notaRepository.save(nota);
            return true;
        }
        return false;
    }
    
    public Double calcularPromedio(Long actividadId) {
        return notaRepository.calcularPromedioNotas(actividadId);
    }
}