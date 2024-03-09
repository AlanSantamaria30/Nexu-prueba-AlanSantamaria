package com.example.nexus.nexus.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.nexus.Entity.Brand;
import com.example.nexus.nexus.Entity.Model;
import com.example.nexus.nexus.Response.BrandResponse;
import com.example.nexus.nexus.Service.BrandService;
import com.example.nexus.nexus.Service.ModelService;

@RestController
@RequestMapping("/brandsController")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ModelService modelService;

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllBrandsWithAveragePrice() {
        List<Brand> brands = brandService.getAllBrandsWithAveragePrice();
        List<BrandResponse> brandResponses = new ArrayList<>();
        for (Brand brand : brands) {
            BrandResponse brandResponse = new BrandResponse();
            brandResponse.setId(brand.getId());
            brandResponse.setName(brand.getName());
            brandResponse.setAveragePrice(brand.getAveragePrice());
            brandResponses.add(brandResponse);
        }
        return new ResponseEntity<>(brandResponses, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @PostMapping("/brands")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand) {
        try {
            Brand createdBrand = brandService.createBrand(brand);
            return new ResponseEntity<>(createdBrand, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/models")
    public ResponseEntity<?> createModel(@RequestBody Model model) {
        try {
            Model createdModel = modelService.createModel(model);
            return new ResponseEntity<>(createdModel, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

     @GetMapping("/brands/{brandId}/models")
    public ResponseEntity<List<Model>> getAllModelsByBrand(@PathVariable Long brandId) {
        Brand brand = brandService.getBrandById(brandId);
        if (brand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Model> models = modelService.getAllModelsByBrand(brand);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @PutMapping("/models/{modelId}")
    public ResponseEntity<?> updateModelAveragePrice(@PathVariable Long modelId, @RequestParam Long newAveragePrice) {
        try {
            Model updatedModel = modelService.updateModelAveragePrice(modelId, newAveragePrice);
            return new ResponseEntity<>(updatedModel, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/modelsRanges")
    public ResponseEntity<List<Model>> getAllModels(@RequestParam(required = false) Long menor,
                                                     @RequestParam(required = false) Long mayor) {
        List<Model> models;
        models = modelService.getAllModelsBetweenAveragePrices(menor, mayor);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
}
