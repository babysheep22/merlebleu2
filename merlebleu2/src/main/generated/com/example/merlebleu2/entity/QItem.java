package com.example.merlebleu2.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 1071916294L;

    public static final QItem item = new QItem("item");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath category1 = createString("category1");

    public final StringPath category2 = createString("category2");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemNm = createString("itemNm");

    public final EnumPath<com.example.merlebleu2.constant.ItemSellStatus> itemSellStatus = createEnum("itemSellStatus", com.example.merlebleu2.constant.ItemSellStatus.class);

    public final NumberPath<Integer> listprice = createNumber("listprice", Integer.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final NumberPath<Integer> sellprice = createNumber("sellprice", Integer.class);

    public final NumberPath<Integer> stockNumber = createNumber("stockNumber", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

