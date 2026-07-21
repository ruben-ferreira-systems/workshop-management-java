package pt.rubenferreira.workshop.service;

import pt.rubenferreira.workshop.entity.Peca;
import pt.rubenferreira.workshop.repository.PecaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;

    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public List<Peca> listarTodas() {
        return pecaRepository.findAll();
    }

    public List<Peca> pesquisar(String termo) {
        if (termo == null || termo.isBlank()) return listarTodas();
        return pecaRepository.findByNomeContainingIgnoreCaseOrReferenciaContainingIgnoreCase(termo, termo);
    }

    public Peca buscarPorId(Integer id) {
        return pecaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Peça não encontrada: " + id));
    }

    public void guardar(Peca peca) {
        pecaRepository.save(peca);
    }

    public void eliminar(Integer id) {
        pecaRepository.deleteById(id);
    }
}