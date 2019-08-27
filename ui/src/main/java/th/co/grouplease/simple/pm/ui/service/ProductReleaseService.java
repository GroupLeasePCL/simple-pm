package th.co.grouplease.simple.pm.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.co.grouplease.simple.pm.ui.model.ProductRelease;

import java.util.List;

@Service
public class ProductReleaseService {
    private final RestTemplate restTemplate;

    public ProductReleaseService(@Autowired RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<ProductRelease> getProductReleases(Long productId, long offset, int limit){
        var response = restTemplate.exchange(
                "http://localhost:8080/products/{productId}/releases?offset={offset}&limit={limit}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductRelease>>(){},
                productId,
                offset,
                limit);

        return response.getBody();
    }

    public int getProductReleaseCount(Long productId){
        return Math.toIntExact(restTemplate.getForObject(
                "http://localhost:8080/products/{productId}/releases/count",
                Long.class,
                productId));
    }
}
