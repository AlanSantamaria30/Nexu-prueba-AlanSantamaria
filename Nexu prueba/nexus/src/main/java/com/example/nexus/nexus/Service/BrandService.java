package com.example.nexus.nexus.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.nexus.Entity.Brand;
import com.example.nexus.nexus.Entity.Model;
import com.example.nexus.nexus.Repository.BrandRepository;
import com.example.nexus.nexus.Repository.ModelRepository;

import jakarta.transaction.Transactional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        return brandOptional.orElse(null);
    }

    public Brand createBrand(Brand brand) {
        if (brandRepository.existsByName(brand.getName())) {
            throw new IllegalArgumentException("El nombre de la marca ya existe.");
        }
        return brandRepository.save(brand);
    }

    public List<Brand> getAllBrandsWithAveragePrice() {
        List<Brand> brands = brandRepository.findAll();
        for (Brand brand : brands) {
            Long totalAveragePrice = 0L;
            List<Model> models = modelRepository.findByBrand(brand);
            for (Model model : models) {
                totalAveragePrice += model.getAveragePrice();
            }
            if (!models.isEmpty()) {
                Long averagePrice = totalAveragePrice / models.size();
                brand.setAveragePrice(averagePrice);
            }
        }
        return brands;
    }

    @Transactional
    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }
}
