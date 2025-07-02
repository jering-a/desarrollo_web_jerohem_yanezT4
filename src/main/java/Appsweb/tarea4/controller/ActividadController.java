package Appsweb.tarea4.controller;

import Appsweb.tarea4.model.Actividad;
import Appsweb.tarea4.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActividadController {
    
    @Autowired
    private ActividadService actividadService;
    
    @GetMapping("/actividades")
    public String mostrarActividades(Model model) {
        List<Actividad> actividades = actividadService.obtenerActividadesRealizadas();
        model.addAttribute("actividades", actividades);
        return "actividades";
    }
    
    @PostMapping("/api/actividades/{id}/evaluar")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> evaluarActividad(
            @PathVariable Long id, 
            @RequestParam Integer nota) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            boolean exito = actividadService.agregarNota(id, nota);
            
            if (exito) {
                Double nuevoPromedio = actividadService.calcularPromedio(id);
                response.put("success", true);
                response.put("promedio", nuevoPromedio);
                response.put("mensaje", "Nota agregada correctamente");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("mensaje", "Error al agregar la nota. Verifique que la nota esté entre 1 y 7.");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", "Error interno del servidor");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}