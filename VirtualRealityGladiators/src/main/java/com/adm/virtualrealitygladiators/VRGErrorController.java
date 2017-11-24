/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators;

import com.adm.virtualrealitygladiators.dao.VRGCategoryDao;
import java.text.MessageFormat;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Daniel McBride
 */

@Controller
public class VRGErrorController {
    private VRGCategoryDao categoryDao;
    
    @Inject
    public VRGErrorController(VRGCategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }    
    
    @RequestMapping(value = "/error")
    public String customError(HttpServletRequest request,
            HttpServletResponse response,
            Model model){
        // retrieve some useful information from the request
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String exceptionMessage = null;
        exceptionMessage = httpStatus.getReasonPhrase();
        
        String requestUri = (String) request.getAttribute("javax.servlet.error.request.uri");
        
        if(requestUri == null){
            requestUri = "Unknown";
        }
        // format the message for the view
        String message = MessageFormat.format("{0} returned for {1}: {2}",
                statusCode, requestUri, exceptionMessage);
        
        model.addAttribute("errorMessage", message);
        model.addAttribute("categories", categoryDao.getAllCategories());
        
        return "customError";
    }
}
