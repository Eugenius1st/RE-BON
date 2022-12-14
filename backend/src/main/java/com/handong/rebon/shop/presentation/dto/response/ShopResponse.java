package com.handong.rebon.shop.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.application.dto.response.category.ShopCategoryResponseDto;
import com.handong.rebon.shop.application.dto.response.image.ShopImageResponseDto;
import com.handong.rebon.shop.application.dto.response.menu.MenuResponseDto;
import com.handong.rebon.shop.application.dto.response.tag.ShopTagResponseDto;
import com.handong.rebon.shop.presentation.dto.response.category.ShopCategoryResponse;
import com.handong.rebon.shop.presentation.dto.response.image.ShopImageResponse;
import com.handong.rebon.shop.presentation.dto.response.menu.MenuResponse;
import com.handong.rebon.shop.presentation.dto.response.tag.ShopTagResponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopResponse {
    private Long id;
    private ShopCategoryResponse category;
    private String name;
    private double star;
    private boolean like;
    private List<ShopTagResponse> tags;
    private String phone;
    private List<ShopCategoryResponse> subCategories;
    private String businessHour;
    private List<MenuResponse> menus;
    private String address;
    private String longitude;
    private String latitude;
    private List<ShopImageResponse> images;

    @Builder
    public ShopResponse(
            Long id,
            ShopCategoryResponse category,
            String name,
            double star,
            boolean like,
            List<ShopTagResponse> tags,
            String phone,
            List<ShopCategoryResponse> subCategories,
            String businessHour,
            List<MenuResponse> menus,
            String address,
            String longitude,
            String latitude,
            List<ShopImageResponse> images
    ) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.star = star;
        this.like = like;
        this.tags = tags;
        this.phone = phone;
        this.subCategories = subCategories;
        this.businessHour = businessHour;
        this.menus = menus;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.images = images;
    }

    public static ShopResponse from(ShopResponseDto dto) {
        return ShopResponse.builder()
                           .id(dto.getId())
                           .category(ShopCategoryResponse.from(dto.getCategory()))
                           .name(dto.getName())
                           .star(dto.getStar())
                           .like(dto.isLike())
                           .tags(convertToShopTagResponse(dto.getTags()))
                           .phone(dto.getPhone())
                           .subCategories(convertToShopCategoryResponse(dto.getSubCategories()))
                           .businessHour(dto.getBusinessHour())
                           .menus(convertToMenuResponse(dto.getMenus()))
                           .address(dto.getAddress())
                           .images(convertToImageResponse(dto.getImages()))
                           .build();
    }

    private static List<ShopTagResponse> convertToShopTagResponse(List<ShopTagResponseDto> tags) {
        return tags.stream()
                   .map(ShopTagResponse::from)
                   .collect(Collectors.toList());
    }

    private static List<ShopCategoryResponse> convertToShopCategoryResponse(List<ShopCategoryResponseDto> subCategories) {
        return subCategories.stream()
                            .map(ShopCategoryResponse::from)
                            .collect(Collectors.toList());
    }

    private static List<MenuResponse> convertToMenuResponse(List<MenuResponseDto> menus) {
        return menus.stream()
                    .map(MenuResponse::from)
                    .collect(Collectors.toList());
    }

    private static List<ShopImageResponse> convertToImageResponse(List<ShopImageResponseDto> images) {
        return images.stream()
                     .map(ShopImageResponse::from)
                     .collect(Collectors.toList());
    }
}
