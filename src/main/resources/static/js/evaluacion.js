document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('modal-evaluacion');
    const selectNota = document.getElementById('select-nota');
    const btnConfirmar = document.getElementById('btn-confirmar-nota');
    const btnCancelar = document.getElementById('btn-cancelar');
    const mensajeError = document.getElementById('mensaje-error');
    
    let actividadIdActual = null;
    
    // Event listeners para botones evaluar
    document.querySelectorAll('.btn-evaluar').forEach(button => {
        button.addEventListener('click', function() {
            actividadIdActual = this.getAttribute('data-id');
            abrirModal();
        });
    });
    
    // Event listener para confirmar nota
    btnConfirmar.addEventListener('click', function() {
        const nota = selectNota.value;
        
        if (!nota || nota < 1 || nota > 7) {
            mostrarError('Debe seleccionar una nota válida entre 1 y 7');
            return;
        }
        
        enviarNota(actividadIdActual, parseInt(nota));
    });
    
    // Event listener para cancelar
    btnCancelar.addEventListener('click', cerrarModal);
    
    // Cerrar modal al hacer clic fuera
    window.addEventListener('click', function(event) {
        if (event.target === modal) {
            cerrarModal();
        }
    });
    
    function abrirModal() {
        modal.style.display = 'block';
        selectNota.value = '';
        mensajeError.textContent = '';
    }
    
    function cerrarModal() {
        modal.style.display = 'none';
        actividadIdActual = null;
    }
    
    function mostrarError(mensaje) {
        mensajeError.textContent = mensaje;
        mensajeError.className = 'error-message';
    }
    
    function mostrarExito(mensaje) {
        mensajeError.textContent = mensaje;
        mensajeError.className = 'success-message';
    }
    
    function enviarNota(actividadId, nota) {
        // Crear FormData para enviar la nota
        const formData = new FormData();
        formData.append('nota', nota);
        
        // Realizar petición AJAX con fetch
        fetch(`/api/actividades/${actividadId}/evaluar`, {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Actualizar la nota en la tabla
                actualizarNotaEnTabla(actividadId, data.promedio);
                mostrarExito(data.mensaje);
                
                // Cerrar modal después de 1.5 segundos
                setTimeout(() => {
                    cerrarModal();
                }, 1500);
            } else {
                mostrarError(data.mensaje);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarError('Error de conexión. Intente nuevamente.');
        });
    }
    
    function actualizarNotaEnTabla(actividadId, nuevoPromedio) {
        const celdaNota = document.querySelector(`[data-id="${actividadId}"] span`);
        if (celdaNota) {
            if (nuevoPromedio !== null) {
                celdaNota.textContent = nuevoPromedio.toFixed(1);
            } else {
                celdaNota.textContent = '-';
            }
        }
    }
});