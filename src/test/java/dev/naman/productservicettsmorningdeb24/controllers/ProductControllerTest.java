package dev.naman.productservicettsmorningdeb24.controllers;

import dev.naman.productservicettsmorningdeb24.exceptions.ProductNotFoundException;
import dev.naman.productservicettsmorningdeb24.models.Product;
import dev.naman.productservicettsmorningdeb24.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    @Qualifier("fakeStoreProductService")
    private ProductService productService;

    @Test
    void createProduct() {
        productService.getProducts();
    }

    @Test
    void getProductDetailsNegative() throws ProductNotFoundException {
        when(productService.getSingleProduct(1000L))
                .thenThrow(ProductNotFoundException.class);

        assertThrows(
                ProductNotFoundException.class,
                () -> productController.getProductDetails(1000L)
        );
    }
    @Test
    void getProductDetailsPositive() throws ProductNotFoundException {
        Product product = new Product();
        product.setTitle("mouse");
        product.setDescription("accessory");
        product.setPrice(1000);

        when(productService.getSingleProduct(anyLong())).
                thenReturn(product);

        //when(productService.getSingleProduct(100L)).thenReturn(new Product());

        Product actualResponse = productController.getProductDetails(100L);

        assertEquals(product,
                actualResponse,
                "Product details from service doesn't matches with what we are getting from controller");
    }



    @Test
    void getAllProducts() throws ProductNotFoundException {
        //Call the mocked product service to get list of products
        Product p1 = new Product();
        p1.setTitle("iPhone");
        p1.setDescription("des1");
        p1.setPrice(100);

        Product p2 = new Product();
        p2.setTitle("Growing rick book");
        p2.setDescription("book");
        p2.setPrice(500);

        Product p3 = new Product();
        p3.setTitle("ipad");
        p3.setDescription("apple");
        p3.setPrice(75000);

        List<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

        when(
                productService.getProducts()
        ).thenReturn(productList);

        //Actual Response
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();
        List<Product> response = responseEntity.getBody();

        assertEquals(productList.size(), response.size());
        assertEquals(productList, response);
    }

    @Test
    void updateProduct() {
    }
}