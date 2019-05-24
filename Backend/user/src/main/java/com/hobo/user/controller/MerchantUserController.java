package com.hobo.user.controller;


import com.hobo.user.model.MerchantUserDTO;
import com.hobo.user.service.MerchantUserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MerchantUserController {

    @Autowired
    private MerchantUserService merchantUserService;

    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");

        response.remove(data,"password");

        return response;
    }

    @GetMapping("/merchantuser")
    public JSONObject getUser (@RequestParam Integer id) {
        MerchantUserDTO merchantUserDTO = merchantUserService.getUser(id);
        JSONObject response = getJSONResponse(merchantUserDTO);
        response.replace("message", "success", "fetching successful");
        return response;
    }

    @PostMapping(value="/merchantuser", consumes = {"application/json"})
    public JSONObject saveUser(@RequestBody MerchantUserDTO merchantUserDTO) {
        MerchantUserDTO result = merchantUserService.saveUser(merchantUserDTO);
        JSONObject response = getJSONResponse(result);
        response.replace("message", "success", "adding successful");
        return response;
    }

    @PutMapping(value="/merchantuser", consumes = {"application/json"})
    public JSONObject updateUser(@RequestBody MerchantUserDTO merchantUserDTO) {
        MerchantUserDTO result = merchantUserService.putUser(merchantUserDTO);
        JSONObject response = getJSONResponse(result);
        response.replace("message", "success", "updating successful");
        return response;
    }

    @DeleteMapping("/merchantuser")
    public JSONObject updateUser(@RequestParam Integer id) {
        MerchantUserDTO merchantUserDTO = merchantUserService.deleteUser(id);
        JSONObject response = getJSONResponse(merchantUserDTO);
        response.replace("message", "success", "deleting successful");
        return response;
    }

    @PostMapping(value="/merchantlogin", consumes = {"application/json"})
    public JSONObject loginCheck(@RequestBody MerchantLogin data) {
        MerchantUserDTO merchantUserDTO= merchantUserService.loginCheck(data.getEmailId(),data.getPassword());
        JSONObject response = getJSONResponse(merchantUserDTO);
        if(merchantUserDTO== null)
            response.replace("message", "success", "verification fail");
        else
            response.replace("message", "success", "verification successful");
        return response;
    }


}

class MerchantLogin {
    private String emailId;
    private String password;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}