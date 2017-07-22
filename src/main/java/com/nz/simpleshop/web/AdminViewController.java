package com.nz.simpleshop.web;

import com.nz.simpleshop.domain.ProductDTO;
import com.nz.simpleshop.domain.PurchaseDTO;
import com.nz.simpleshop.model.Product;
import com.nz.simpleshop.model.Purchase;
import com.nz.simpleshop.service.ProductService;
import com.nz.simpleshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/admin")
public class AdminViewController {

    private static final int AMOUNT_PER_PAGE = 20;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/admin/purchases";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("page", "login");
        model.addAttribute("title", "Вход");
        return "/admin/index";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<Product> productsList = productService.listProductsPaged(page, AMOUNT_PER_PAGE);
        List<ProductDTO> productsDTOList = productsList.stream().map(ProductDTO::new).collect(Collectors.toList());

        long total = productService.countProducts();
        long pages = (long) Math.ceil((double)total / AMOUNT_PER_PAGE);

        model.addAttribute("products", productsDTOList);
        model.addAttribute("page", "products");
        model.addAttribute("pageIndex", page);
        model.addAttribute("totalPages", pages);
        model.addAttribute("title", "Товары");
        return "/admin/index";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String productsAdd(Model model) {
        model.addAttribute("page", "products-add");
        model.addAttribute("title", "Добавить товар");

        return "/admin/index";
    }

    @RequestMapping(value = "/products/edit/{productId}", method = RequestMethod.GET)
    public String productsEdit(Model model, @PathVariable("productId") Long productId) {
        model.addAttribute("page", "products-edit");
        model.addAttribute("product", productService.getProductById(productId));
        model.addAttribute("title", "Редактировать товар");
        return "/admin/index";
    }

    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public String purchases(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<Purchase> purchaseList = purchaseService.getPurchasesPaged(page, AMOUNT_PER_PAGE);
        List<PurchaseDTO> purchaseDTOList = purchaseList.stream().map(PurchaseDTO::new).collect(Collectors.toList());

        long total = purchaseService.countPurchases();
        long pages = (long) Math.ceil((double)total / AMOUNT_PER_PAGE);

        model.addAttribute("purchases", purchaseDTOList);
        model.addAttribute("page", "purchases");
        model.addAttribute("pageIndex", page);
        model.addAttribute("totalPages", pages);
        model.addAttribute("title", "Товары");
        return "/admin/index";
    }

}
