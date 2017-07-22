package com.nz.simpleshop.web;

import com.nz.simpleshop.domain.ProductDTO;
import com.nz.simpleshop.model.Product;
import com.nz.simpleshop.service.ProductService;
import com.nz.simpleshop.service.PurchaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final static Logger logger = LogManager.getLogger(PurchaseService.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public @ResponseBody ResponseObject productsAdd(ProductDTO product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Ошибка добавления товара: " + "Неверно задано поле: " + bindingResult.getFieldError().getField());
            return new ResponseObject("Неверно задано поле: " + bindingResult.getFieldError().getField(), false);
        }
        if (!StringUtils.hasText(product.getName())) {
            logger.error("Ошибка добавления товара: " + "Укажите имя товара {}", product.getName());
            return new ResponseObject("Укажите имя товара", false);
        }
        if (!StringUtils.hasText(product.getDescription())) {
            logger.error("Ошибка добавления товара: " + "Укажите описание товара {}", product.getDescription());
            return new ResponseObject("Укажите описание товара", false);
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            logger.error("Ошибка добавления товара: " + "Неверно задана цена {}", product.getPrice());
            return new ResponseObject("Неверно задана цена", false);
        }
        if (product.getStock() == null || product.getStock() < 0) {
            logger.error("Ошибка добавления товара: " + "Неверно задано кол-во товара {}", product.getStock());
            return new ResponseObject("Неверно задано кол-во товара", false);
        }
        productService.addProduct(new Product(product));

        return new ResponseObject("OK", true);
    }

    @RequestMapping(value = "/products/edit/{productId}", method = RequestMethod.POST)
    public @ResponseBody ResponseObject productsEdit(@RequestParam("action") String action, @PathVariable("productId") Long productId, ProductDTO product, BindingResult bindingResult) {
        if (action.equals("save")) {
            if (bindingResult.hasErrors()) {
                logger.error("Ошибка редактирования товара: " + "Неверно задано поле: " + bindingResult.getFieldError().getField());
                return new ResponseObject("Неверно задано поле: " + bindingResult.getFieldError().getField(), false);
            }
            if (!StringUtils.hasText(product.getName())) {
                logger.error("Ошибка редактирования товара: " + "Укажите имя товара {}", product.getName());
                return new ResponseObject("Укажите имя товара", false);
            }
            if (!StringUtils.hasText(product.getDescription())) {
                logger.error("Ошибка редактирования товара: " + "Укажите описание товара {}",product.getDescription());
                return new ResponseObject("Укажите описание товара", false);
            }
            if (product.getPrice() == null || product.getPrice() < 0) {
                logger.error("Ошибка редактирования товара: " + "Неверно задана цена {}",product.getPrice());
                return new ResponseObject("Неверно задана цена", false);
            }
            if (StringUtils.isEmpty(product.getStock())) {
                logger.error("Ошибка редактирования товара: " + "Укажите кол-во товара {}",product.getStock());
                return new ResponseObject("Укажите кол-во товара", false);
            }
        }
        Product dbProduct = productService.getProductById(productId);
        if (dbProduct == null) {
            logger.error("Ошибка редактирования товара: " + "Такого товара не существует {}",product.getId());
            return new ResponseObject("Такого товара не существует", true);
        } else {
            dbProduct.setName(product.getName());
            dbProduct.setDescription(product.getName());
            dbProduct.setPrice(product.getPrice());
            dbProduct.setStock(product.getStock());
            if (action.equals("save")) {
                productService.editProduct(dbProduct);
            } else if (action.equals("delete")) {
                productService.deleteProduct(dbProduct);
            }
        }
        return new ResponseObject("OK", true);
    }

}
