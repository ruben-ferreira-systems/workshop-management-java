package pt.rubenferreira.workshop.service;

import pt.rubenferreira.workshop.entity.Veiculo;
import pt.rubenferreira.workshop.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> filtrar(String matricula, String marca, String cliente) {
        if (matricula != null && !matricula.isBlank()) {
            return veiculoRepository.findByMatriculaContainingIgnoreCase(matricula);
        }
        if (marca != null && !marca.isBlank()) {
            return veiculoRepository.findByMarcaContainingIgnoreCase(marca);
        }
        if (cliente != null && !cliente.isBlank()) {
            return veiculoRepository.findByClienteNomeContainingIgnoreCase(cliente);
        }
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorId(Integer id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    public void guardar(Veiculo veiculo) {
        veiculoRepository.save(veiculo);
    }

    public void apagar(Integer id) {
        veiculoRepository.deleteById(id);
    }
}