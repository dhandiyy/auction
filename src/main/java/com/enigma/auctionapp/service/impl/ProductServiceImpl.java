package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.Offer;
import com.enigma.auctionapp.model.entity.Product;
import com.enigma.auctionapp.model.request.ProductRequest;
import com.enigma.auctionapp.model.response.ProductResponse;
import com.enigma.auctionapp.repository.ProductRepository;
import com.enigma.auctionapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    @Override
    public ProductResponse update(ProductRequest productRequest) {
        Product currentProduct = getById(productRequest.getId());
        if (currentProduct != null){
            Product product = Product.builder()
                    .id(currentProduct.getId())
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .condition(productRequest.getCondition())
                    .actualPrice(productRequest.getActualPrice())
                    .build();
            productRepository.saveAndFlush(product);

            return getProductResponse(product);
        }
        return null;
    }

    @Override
    public void create(Product product) {
        productRepository.createAndFlush(product);
    }

    @Override
    public void updateForSetOffer(Product product){
        productRepository.updateForSetOffer(product);
    }

    @Override
    public Page<ProductResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductResponse> productResponseList = new ArrayList<>();

        for(Product product : productPage){
            productResponseList.add(
                    getProductResponse(product));

        }
        return new PageImpl<>(productResponseList,pageable, productPage.getTotalElements());

    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Product not found with id: " + id));
    }


    private static ProductResponse getProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .condition(product.getCondition())
                .actualPrice(product.getActualPrice())
                .description(product.getDescription())
                .build();
    }
}
