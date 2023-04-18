package com.example.doannmcnpm.service;


import com.example.doannmcnpm.model.Category;
import com.example.doannmcnpm.model.SoccerField;
import com.example.doannmcnpm.model.SoccerFieldService;
import com.example.doannmcnpm.model.YardLocation;
import com.example.doannmcnpm.repository.SoccerFieldServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoccerFieldServiceService {

    @Autowired
    SoccerFieldServiceRepository soccerFieldServiceRepository;

    public List<SoccerFieldService> listSoccerFieldService() {
        return soccerFieldServiceRepository.findAll();
    }

    public void createCategory(SoccerFieldService newSoccerFieldService) {

        SoccerFieldService service= new SoccerFieldService();
        service.setNameService(newSoccerFieldService.getNameService());
        service.setPrice(newSoccerFieldService.getPrice());
        service.setYardLocation(newSoccerFieldService.getYardLocation());
        soccerFieldServiceRepository.save(service);

    }

    public boolean findById(int soccerServiceId) {

        return soccerFieldServiceRepository.findById(soccerServiceId).isPresent();
    }

    public void editMerchandise(int soccerServiceId, SoccerFieldService newSoccerFieldService) throws Exception {
        Optional<SoccerFieldService> soccerFieldService=soccerFieldServiceRepository.findById(soccerServiceId);
        if(!soccerFieldService.isPresent())    {
            throw new Exception(" soccerFieldService is not present");
        }
        SoccerFieldService service=soccerFieldService.get();
        service.setNameService(newSoccerFieldService.getNameService());
        service.setPrice(newSoccerFieldService.getPrice());
        service.setYardLocation(newSoccerFieldService.getYardLocation());
        soccerFieldServiceRepository.save(service);

    }
}
