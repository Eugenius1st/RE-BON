package com.handong.rebon.shop.domain.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@DiscriminatorValue("L")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@OnDelete(action = OnDeleteAction.CASCADE)
public class Lodging extends Shop {

    @Builder
    public Lodging(
            Long id,
            Category category,
            ShopContent shopContent,
            ShopImages shopImages,
            String address,
            ShopScore shopScore,
            Long naverId,
            boolean deleted
    ) {
        super(id, category, shopContent, shopImages, address, shopScore, naverId, deleted);
    }
}
