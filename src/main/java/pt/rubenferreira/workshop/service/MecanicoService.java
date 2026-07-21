package pt.rubenferreira.workshop.service;

import pt.rubenferreira.workshop.entity.Mecanico;
import pt.rubenferreira.workshop.repository.MecanicoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MecanicoService {

    private final MecanicoRepository mecanicoRepository;

    public MecanicoService(MecanicoRepository mecanicoRepository) {
        this.mecanicoRepository = mecanicoRepository;
    }

    public List<Mecanico> listarTodos() {
        return mecanicoRepository.findAll();
    }

    public List<Mecanico> filtrar(String nome, String nif, String especialidade) {
        if (nome != null && !nome.isBlank()) {
            return mecanicoRepository.findByNomeContainingIgnoreCase(nome);
        }
        if (nif != null && !nif.isBlank()) {
            return mecanicoRepository.findByNifContaining(nif);
        }
        if (especialidade != null && !especialidade.isBlank()) {
            return mecanicoRepository.findByEspecialidadeContainingIgnoreCase(especialidade);
        }
        return mecanicoRepository.findAll();
    }

    public Mecanico buscarPorId(Integer id) {
        return mecanicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mecânico não encontrado"));
    }

    public void guardar(Mecanico mecanico) {
        mecanicoRepository.save(mecanico);
    }

    public void apagar(Integer id) {
        mecanicoRepository.deleteById(id);
    }
}