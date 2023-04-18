package com.example.doannmcnpm.service;


import com.example.doannmcnpm.model.Merchandise;
import com.example.doannmcnpm.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchandiseService {
    @Autowired
    MerchandiseRepository merchandiseRepository;

    public void createMerchandise(Merchandise merchandise) {
        merchandiseRepository.save(merchandise);
    }

    public boolean findById(int merchandiseId)  {
        return merchandiseRepository.findById(merchandiseId).isPresent();
    }

    public void editMerchandise(int merchandiseId, Merchandise newMerchandise) {
        Merchandise merchandise= merchandiseRepository.getById(merchandiseId);

        merchandise.setName(newMerchandise.getName());
        merchandise.setPrice(newMerchandise.getPrice());
        merchandiseRepository.save(merchandise);
    }

    public List<Merchandise> getAllProducts() {
       return  merchandiseRepository.findAll();
    }
}
