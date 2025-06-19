package fr.eseo.twic.alidouya.server.service;

import fr.eseo.twic.alidouya.server.dto.ProductDTO;
import fr.eseo.twic.alidouya.server.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<ProductDTO> getAll() {
        return repo.findAll().stream()
                .map(p -> new ProductDTO(p.getProductNo(), p.getProductName()))
                .toList();
    }
}
