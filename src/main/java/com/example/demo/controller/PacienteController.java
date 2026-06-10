package com.example.demo.controller;

import com.example.demo.model.Paciente;
import com.example.demo.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteService service;

    public PacienteController(PacienteService service) { this.service = service; }

    @GetMapping
    public List<Paciente> listarTodos() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente crear(@Valid @RequestBody Paciente paciente) {
        return service.guardar(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @Valid @RequestBody Paciente pacienteDetalle) {
        return service.buscarPorId(id).map(paciente -> {
            paciente.setNombre(pacienteDetalle.getNombre());
            paciente.setEdad(pacienteDetalle.getEdad());
            paciente.setCorreo(pacienteDetalle.getCorreo());
            paciente.setTelefono(pacienteDetalle.getTelefono());
            return ResponseEntity.ok(service.guardar(paciente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}