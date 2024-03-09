package com.example.nexus.nexus.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.nexus.Entity.Brand;
import com.example.nexus.nexus.Entity.Model;
import com.example.nexus.nexus.Repository.ModelRepository;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandService brandService;

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model createModel(Model model) {
        // Verificar si la identificación de la marca existe
        Brand brand = brandService.getBrandById(model.getBrand().getId());
        if (brand == null) {
            throw new IllegalArgumentException("La identificación de la marca no existe.");
        }

        // Verificar si el nombre del modelo ya existe para esa marca
        if (modelRepository.existsByNameAndBrand(model.getName(), brand)) {
            throw new IllegalArgumentException("El nombre del modelo ya existe para esta marca.");
        }

        // Verificar si el precio medio es opcional y si es inferior a 100.000
        if (model.getAveragePrice() != null && model.getAveragePrice() <= 100000) {
            throw new IllegalArgumentException("El precio medio debe ser superior a 100.000.");
        }

        return modelRepository.save(model);
    }

    public List<Model> getAllModelsByBrand(Brand brand) {
        return modelRepository.findByBrand(brand);
    }

    public Model updateModelAveragePrice(Long modelId, Long newAveragePrice) {
        // Obtener el modelo por su ID
        Model model = modelRepository.findById(modelId)
                                    .orElseThrow(() -> new IllegalArgumentException("El modelo no existe"));

        // Verificar si el nuevo precio promedio es mayor que 100,000
        if (newAveragePrice <= 100000) {
            throw new IllegalArgumentException("El precio promedio debe ser mayor que 100,000");
        }

        // Actualizar el precio promedio del modelo
        model.setAveragePrice(newAveragePrice);

        // Guardar el modelo actualizado en la base de datos
        return modelRepository.save(model);
    }

    public List<Model> getAllModelsBetweenAveragePrices(Long menor, Long mayor) {
        return modelRepository.findByAveragePriceBetween(menor, mayor);
    }
}
