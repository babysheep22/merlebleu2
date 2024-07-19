package com.example.merlebleu2.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.example.merlebleu2.constant.ItemSellStatus;
import com.example.merlebleu2.dto.ItemSearchDto;
import com.example.merlebleu2.entity.Item;
import com.example.merlebleu2.entity.QItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import com.example.merlebleu2.dto.MainItemDto;
import com.example.merlebleu2.entity.QItemImg;
import com.example.merlebleu2.dto.QMainItemDto;


public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {

        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QItem.item.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if (StringUtils.equals("itemNm", searchBy)) {
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createdBy", searchBy)) {
            return QItem.item.createdBy.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("category1" , searchBy)){
            return QItem.item.category1.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("category2" , searchBy)){
            return QItem.item.category2.like("%" + searchQuery + "%");
        }
        return null;
    }

    private BooleanExpression category1Eq(String category1) {
        return StringUtils.isEmpty(category1) ? null : QItem.item.category1.eq(category1);
    }

    private BooleanExpression category2Eq(String category2) {
        return StringUtils.isEmpty(category2) ? null : QItem.item.category2.eq(category2);
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        //queryFactory를 통한 쿼리 직접 작성
        QueryResults<Item> results = queryFactory
                .selectFrom(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(),
                                itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> content = results.getResults();
        long total = results.getTotal();
        //조회한 데이터를 Page 클래스의 구현체인 PageImpl 객체로 변환
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression itemNmLike(String searchQuery) { //null이 아니면 상품명에 해당 검색어가 포함되는 상품 조회 조건 반환
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%" + searchQuery + "%");
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        List<MainItemDto> content = queryFactory
                .select(
                        new QMainItemDto( //반환할 값
                                item.id,
                                item.itemNm,
                                item.category1,
                                item.category2,
                                itemImg.imgUrl,
                                item.discount,
                                item.sellprice,
                                item.listprice)
                )
                .from(itemImg)
                .join(itemImg.item, item) //imemimg와 item을 내부 조인
                .where(itemImg.repimgYn.eq("Y")) //상품 이미지의 경우 대표 상품 이미지만 불러옵니다.
                .where(category1Eq(itemSearchDto.getCategory1()))
                .where(category2Eq(itemSearchDto.getCategory2()))// 추가된 부분
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(Wildcard.count)
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(itemSearchDto.getSearchQuery()),
                        category1Eq(itemSearchDto.getCategory1()), // 수정된 부분: category1 필터 추가
                        category2Eq(itemSearchDto.getCategory2())) // 수정된 부분: category2 필터 추가
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
