package com.nz.simpleshop.web;

import com.nz.simpleshop.domain.ProductDTO;
import com.nz.simpleshop.domain.PurchaseDTO;
import com.nz.simpleshop.model.Client;
import com.nz.simpleshop.model.Product;
import com.nz.simpleshop.model.Purchase;
import com.nz.simpleshop.service.ProductService;
import com.nz.simpleshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClientViewController {

    private static final int AMOUNT_PER_PAGE = 20;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model) {
        return "redirect:/products";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {
        model.addAttribute("page", "login");
        model.addAttribute("title", "Вход");
        return "/client/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {
        model.addAttribute("page", "register");
        model.addAttribute("title", "Регистрация");
        return "/client/index";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProducts(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<Product> productsList = productService.listProductsPaged(page, AMOUNT_PER_PAGE);
        List<ProductDTO> productsDTOList = productsList.stream().map(ProductDTO::new).collect(Collectors.toList());

        long total = productService.countProducts();
        long pages = (long) Math.ceil((double)total / AMOUNT_PER_PAGE);

        model.addAttribute("products", productsDTOList);
        model.addAttribute("page", "products");
        model.addAttribute("pageIndex", page);
        model.addAttribute("totalPages", pages);
        model.addAttribute("title", "Товары");
        return "/client/index";
    }

    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public String getPurchases(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client principal = (Client)auth.getPrincipal();
        List<Purchase> purchaseList = purchaseService.getPurchasesForClient(principal, page, AMOUNT_PER_PAGE);
        List<PurchaseDTO> purchaseDTOList = purchaseList.stream().map(PurchaseDTO::new).collect(Collectors.toList());

        long total = purchaseService.countPurchases();
        long pages = (long) Math.ceil((double)total / AMOUNT_PER_PAGE);

        model.addAttribute("purchases", purchaseDTOList);
        model.addAttribute("page", "purchases");
        model.addAttribute("pageIndex", page);
        model.addAttribute("totalPages", pages);
        model.addAttribute("title", "Покупки");
        return "/client/index";
    }

}
