package com.hobo.merchant.controller;

import com.hobo.merchant.entity.JoinedTable;
import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import java.util.List;

@RestController
@RequestMapping(path = "/merchant")
public class MerchantController{

    @Autowired
    private MerchantService merchantService;

    @PostMapping(consumes = {"application/json"})
    public JSONObject create(@RequestBody MerchantDTO merchantDTO){
        try {
            MerchantDTO merchantDTO1=merchantService.createMerchant(merchantDTO);
            JSONObject response=getJSONResponse(merchantDTO1);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping()
    public JSONObject read(@RequestParam Integer id){
        try {
            MerchantDTO merchantDTO = merchantService.readMerchantById(id);
            JSONObject response = getJSONResponse(merchantDTO);
            response.replace("message", "success", "Merchant read successful");
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping()
    public JSONObject update(@RequestBody MerchantDTO merchantDTO){
        try {
            MerchantDTO merchantDTO1=merchantService.updateMerchant(merchantDTO);
            JSONObject response=getJSONResponse(merchantDTO1);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping()
    public JSONObject delete(@RequestParam Integer id){
        try {
            MerchantDTO merchantDTO=merchantService.deleteMerchantById(id);
            JSONObject response=getJSONResponse(merchantDTO);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/topproductmerchant")
    public JSONObject getTopMerchant(){
        try {
            List<JoinedTable> topMerchant=merchantService.getTopMerchant();
            JSONObject response=getJSONResponse(topMerchant);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");
        return response;
    }




}
