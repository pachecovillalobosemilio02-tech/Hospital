package com.example.demo.service;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> listarTodos() { return repository.findAll(); }
    public Optional<Paciente> buscarPorId(Long id) { return repository.findById(id); }
    public Paciente guardar(Paciente paciente) { return repository.save(paciente); }
    public void eliminar(Long id) { repository.deleteById(id); }
}