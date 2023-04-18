package com.example.doannmcnpm.controller;


import com.example.doannmcnpm.common.ApiResponse;
import com.example.doannmcnpm.model.Merchandise;
import com.example.doannmcnpm.service.MerchandiseService;
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
import java.util.Set;

@RestController
@RequestMapping("/merchandise")

@CrossOrigin
public class MerchandiseController {

    @Autowired
    MerchandiseService merchandiseService;





    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMerchandise(@RequestBody String merchandise)  {
        Gson gson = new Gson();
        Merchandise newMerchandise= gson.fromJson(merchandise, Merchandise.class);

        ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
        Validator val=valFac.getValidator();
        Set<ConstraintViolation<Merchandise>> validations=val.validate(newMerchandise);
        for(ConstraintViolation<Merchandise> validation : validations)	{
            System.out.println(validation.getMessage());
        }
        if(validations.size()==0)	{
            merchandiseService.createMerchandise(newMerchandise);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Merchandise has been added"), HttpStatus.CREATED);
        }

        return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Merchandise does not exists"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Merchandise>> getMerchandise() {
        List<Merchandise> merchandiseList = merchandiseService.getAllProducts();
        return new ResponseEntity<>(merchandiseList, HttpStatus.OK);
    }

    @PutMapping("/update/{merchandiseId}")
    public ResponseEntity<ApiResponse> updateMerchandise(@PathVariable("merchandiseId") int merchandiseId, @RequestBody String merchandise)  {
        Gson gson = new Gson();
        Merchandise newMerchandise= gson.fromJson(merchandise, Merchandise.class);
        if (!merchandiseService.findById(merchandiseId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Merchandise does not exists"), HttpStatus.NOT_FOUND);
        }

        merchandiseService.editMerchandise(merchandiseId,newMerchandise);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Merchandise has been updated"), HttpStatus.OK);

    }
}
