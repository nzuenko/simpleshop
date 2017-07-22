package com.nz.simpleshop.web;

import com.nz.simpleshop.model.Client;
import com.nz.simpleshop.service.ClientService;
import com.nz.simpleshop.service.PurchaseService;
import com.nz.simpleshop.service.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/products/buy/{productId}", method = RequestMethod.POST)
    public @ResponseBody ResponseObject productsEdit(@PathVariable("productId") Long productId, @RequestParam("buyStock") Integer buyStock) {
        if (buyStock <= 0) {
            return new ResponseObject("Задано неверное количество для покупки!", false);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client principal = (Client)auth.getPrincipal();
        try {
            purchaseService.createPurchase(principal, productId, buyStock);
        } catch (ApiException e) {
            return new ResponseObject(e.getMessage(), false);
        }
        return new ResponseObject("OK", true);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ResponseObject register(@RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("passwordRepeat") String passwordRepeat) {
        if (!StringUtils.hasText(login)) {
            return new ResponseObject("Укажите логин", false);
        }
        if (!StringUtils.hasText(password)) {
            return new ResponseObject("Укажите пароль", false);
        }
        if (password.length() < 3) {
            return new ResponseObject("Длина пароля должна быть не меньше 3", false);
        }
        if (!password.equals(passwordRepeat)) {
            return new ResponseObject("Пароли не совпадают", false);
        }
        UserDetails userDetails = clientService.loadUserByUsername(login);
        if (userDetails != null) {
            return new ResponseObject("Такой пользователь уже существует", false);
        }

        clientService.createNewUser(login, password);
        return new ResponseObject("OK", true);
    }
}
