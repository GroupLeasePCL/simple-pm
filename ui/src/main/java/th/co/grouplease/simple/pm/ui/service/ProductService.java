package th.co.grouplease.simple.pm.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.co.grouplease.simple.pm.ui.model.Product;

import java.util.List;

@Service
public class ProductService {
    private final RestTemplate restTemplate;

    public ProductService(@Autowired RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts(long offset, int limit){
        var response = restTemplate.exchange(
                "http://localhost:8080/products?offset={offset}&limit={limit}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>(){},
                offset,
                limit);

        return response.getBody();
    }

    public int getProductCount(){
        return Math.toIntExact(restTemplate.getForObject("http://localhost:8080/products/count", Long.class));
    }

    public Product createProduct(Product product) {
        return restTemplate.postForObject(
                "http://localhost:8080/products",
                product,
                Product.class
        );
    }
}
