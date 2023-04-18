package com.example.doannmcnpm.controller;


import com.example.doannmcnpm.common.ApiResponse;
import com.example.doannmcnpm.dto.SoccerFieldDto;
import com.example.doannmcnpm.model.Category;
import com.example.doannmcnpm.model.Merchandise;
import com.example.doannmcnpm.model.SoccerFieldService;
import com.example.doannmcnpm.model.YardLocation;
import com.example.doannmcnpm.repository.YardLocationRepository;
import com.example.doannmcnpm.service.SoccerFieldServiceService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/service")
@CrossOrigin
public class SoccerFieldServiceController {

    @Autowired
    SoccerFieldServiceService soccerFieldServiceService;

    @Autowired
    YardLocationRepository yardLocationRepository;

    @GetMapping("/list")
    public List<SoccerFieldService> listSoccerFieldService()    {
        return soccerFieldServiceService.listSoccerFieldService();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addSoccerFieldService(@RequestBody String soccerService) {
        Gson gson = new Gson();
        SoccerFieldService newSoccerFieldService= gson.fromJson(soccerService, SoccerFieldService.class);

        Optional<YardLocation> optionalYardLocation= yardLocationRepository.findById(newSoccerFieldService.getYardLocation().getId());
        if(!optionalYardLocation.isPresent())   {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "YardLocation does not exists"), HttpStatus.NOT_FOUND);
        }

        ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
        Validator val=valFac.getValidator();
        Set<ConstraintViolation<SoccerFieldService>> validations=val.validate(newSoccerFieldService);
        for(ConstraintViolation<SoccerFieldService> validation : validations)	{
            System.out.println(validation.getMessage());
        }

        if(validations.size()==0)	{
            soccerFieldServiceService.createCategory(newSoccerFieldService);
            return new  ResponseEntity<>(new ApiResponse(true, "A new soccerFieldService created"), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(new ApiResponse(false,"A new soccerFieldService is not created"),HttpStatus.BAD_REQUEST );
    }


    @PutMapping("/update/{soccerServiceId}")
    public ResponseEntity<ApiResponse> updateMerchandise(@PathVariable("soccerServiceId") int soccerServiceId, @RequestBody String soccerFieldService) throws Exception {
        Gson gson = new Gson();
        SoccerFieldService newSoccerFieldService= gson.fromJson(soccerFieldService, SoccerFieldService.class);
        if (!soccerFieldServiceService.findById(soccerServiceId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Soccer Service does not exists"), HttpStatus.NOT_FOUND);
        }

        soccerFieldServiceService.editMerchandise(soccerServiceId,newSoccerFieldService);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, " Soccer Service  has been updated"), HttpStatus.OK);

    }
}
