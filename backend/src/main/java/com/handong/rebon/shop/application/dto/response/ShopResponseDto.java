package com.handong.rebon.shop.application.dto.response;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.response.category.ShopCategoryResponseDto;
import com.handong.rebon.shop.application.dto.response.image.ShopImageResponseDto;
import com.handong.rebon.shop.application.dto.response.menu.MenuResponseDto;
import com.handong.rebon.shop.application.dto.response.tag.ShopTagResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.category.ShopCategory;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.tag.ShopTag;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShopResponseDto {
    private Long id;
    private ShopCategoryResponseDto category;
    private String name;
    private double star;
    private boolean like;
    private List<ShopTagResponseDto> tags;
    private String phone;
    private List<ShopCategoryResponseDto> subCategories;
    private String businessHour;
    private List<MenuResponseDto> menus;
    private String address;
    private List<ShopImageResponseDto> images;

    public static ShopResponseDto from(Shop shop) {
        return of(shop, Collections.emptyList());
    }

    public static ShopResponseDto of(Shop shop, List<Menu> menus) {
        ShopContent shopContent = shop.getShopContent();
        return ShopResponseDto.builder()
                              .id(shop.getId())
                              .category(ShopCategoryResponseDto.from(shop.getCategory()))
                              .name(shop.getName())
                              .star(shop.getStar())
                              .tags(convertToShopTagResponseDto(shop.getShopTags()))
                              .phone(shopContent.getPhone())
                              .subCategories(convertToShopCategoryResponseDto(shop.getShopCategories()))
                              .businessHour(shopContent.businessHour())
                              .menus(convertToMenuResponseDto(menus))
                              .address(shop.getAddress())
                              .images(convertToShopImageResponseDto(shop.getShopImages()))
                              .build();
    }

    private static List<ShopImageResponseDto> convertToShopImageResponseDto(ShopImages shopImages) {
        return shopImages.getShopImages().stream()
                         .map(ShopImageResponseDto::from)
                         .collect(Collectors.toList());
    }

    private static List<ShopTagResponseDto> convertToShopTagResponseDto(List<ShopTag> shopTags) {
        return shopTags.stream()
                       .map(shopTag -> ShopTagResponseDto.from(shopTag.getTag()))
                       .collect(Collectors.toList());
    }

    private static List<ShopCategoryResponseDto> convertToShopCategoryResponseDto(List<ShopCategory> shopCategories) {
        return shopCategories.stream()
                             .map(shopCategory -> ShopCategoryResponseDto.from(shopCategory.getCategory()))
                             .collect(Collectors.toList());
    }

    private static List<MenuResponseDto> convertToMenuResponseDto(List<Menu> menus) {
        return menus.stream()
                    .map(MenuResponseDto::from)
                    .collect(Collectors.toList());
    }
}
