package com.connectJPA.demo.service;


import com.connectJPA.demo.dto.request.DishCreationRequest;
import com.connectJPA.demo.dto.request.DishUpdateRequest;
import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.ProductResponse;
import com.connectJPA.demo.entity.Dish;
import com.connectJPA.demo.entity.OrderDetail;
import com.connectJPA.demo.entity.Orders;
import com.connectJPA.demo.entity.Product;
import com.connectJPA.demo.exception.AppException;
import com.connectJPA.demo.exception.ErrorCode;
import com.connectJPA.demo.mapper.DishMapper;
import com.connectJPA.demo.mapper.ProductMapper;
import com.connectJPA.demo.repository.DishRepository;
import com.connectJPA.demo.repository.OrderRepository;
import com.connectJPA.demo.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuService {
    DishRepository dishRepository;
    DishMapper dishMapper;
    ProductRepository productRepository;

    @Override
    public List<DishResponse> filterProducts(String type, String description, BigDecimal minPrice, BigDecimal maxPrice, Double minRating, Double maxRating) {
        log.info("Filtering dishes...");
        return dishRepository.findByFilters(type, description, minPrice, maxPrice, minRating, maxRating)
                .stream()
                .map(dishMapper::toDishResponse)
                .collect(Collectors.toList());
    }

    public DishResponse getDishById(String id){
        return dishMapper.toDishResponse(dishRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED)));
    }

    @Override
    public DishResponse createProduct(DishCreationRequest request) {
        if (dishRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.DISH_EXISTED);
        }
        Dish dish = dishMapper.toDish(request);
        return dishMapper.toDishResponse(dishRepository.save(dish));
    }

    @Override
    public List<DishResponse> getAllProducts() {
        return dishRepository.findAll().stream()
                .map(dishMapper::toDishResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DishResponse getProductByName(String name) {
        return dishMapper.toDishResponse(dishRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED)));
    }

    @Override
    public List<DishResponse> searchProductsByDescription(String keyword) {
        return dishRepository.findByDescriptionContaining(keyword)
                .map(dishes -> dishes.stream()
                        .map(dishMapper::toDishResponse)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED));
    }

    @Override
    public List<DishResponse> searchProducts(String keyword) {
        return dishRepository.findByNameContaining(keyword)
                .map(dishes -> dishes.stream()
                        .map(dishMapper::toDishResponse)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED));
    }

    @Override
    public List<DishResponse> getProductsByType(String type) {
        return dishRepository.findByType(type)
                .map(dishes -> dishes.stream()
                        .map(dishMapper::toDishResponse)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED));
    }

    @Override
    public List<DishResponse> getProductsByPrice(BigDecimal priceMin, BigDecimal priceMax) {
        return dishRepository.findByPriceBetween(priceMin, priceMax)
                .map(dishes -> dishes.stream()
                        .map(dishMapper::toDishResponse)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED));
    }

    @Override
    public List<DishResponse> getProductsByRating(double ratingMin, double ratingMax) {
        return dishRepository.findByRatingBetween(ratingMin, ratingMax)
                .map(dishes -> dishes.stream()
                        .map(dishMapper::toDishResponse)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED));
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public DishResponse updateProduct(String dishId, DishUpdateRequest request) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED));
        dishMapper.updateDish(dish, request);
        return dishMapper.toDishResponse(dishRepository.save(dish));
    }


    @Override
    public ProductResponse getProductById(String dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_EXISTED));
        return dishMapper.toDishResponse(dish);
    }
}
