package com.efub.cafetimes.repository;

import com.efub.cafetimes.constant.SubscriptionSellStatus;
import com.efub.cafetimes.domain.Subscription;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
//import com.efub.cafetimes.domain.QItem;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class SubscriptionRepositoryTest {

    @Autowired //빈 주입
    SubscriptionRepository subscriptionRepository;

    @Test
    @DisplayName("구독권 저장 테스트")
    public void createSubscriptionTest(){
        Subscription subscription = new Subscription();

        subscription.setMenu("테스트 구독권");
        subscription.setPrice(100000);
        subscription.setSubscriptionDetail("테스트 구독권 상세 설명");
        subscription.setCurrentAmount(33);
        subscription.setTotalAmount(50);
        subscription.setTerm(2);
        subscription.setSubscriptionSellStatus(SubscriptionSellStatus.SELL);
        subscription.setStockNumber(100);
        subscription.setCreatedAt(LocalDateTime.now());
        subscription.setUpdateTime(LocalDateTime.now());
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        System.out.println(savedSubscription.toString());

    }

    public void createSubscriptionList(){
        for(int i=1; i<=10; i++){
            Subscription subscription = new Subscription();
            subscription.setMenu("테스트 구독권"+i);
            subscription.setPrice(100000+i);
            subscription.setCurrentAmount(33);
            subscription.setTotalAmount(50);
            subscription.setTerm(2);
            subscription.setSubscriptionDetail("테스트 구독권 상세 설명"+i);
            subscription.setSubscriptionSellStatus(SubscriptionSellStatus.SELL);
            subscription.setStockNumber(100);
            subscription.setCreatedAt(LocalDateTime.now());
            subscription.setUpdateTime(LocalDateTime.now());
            Subscription savedSubscription = subscriptionRepository.save(subscription);
        }
    }

    @Test
    @DisplayName("구독권 조회 테스트")
    public void findByMenuTest(){
        this.createSubscriptionList();
        List<Subscription> subscriptionList = subscriptionRepository.findByMenu("테스트 구독권1");
        for(Subscription subscription : subscriptionList){
            System.out.println(subscription.toString());
        }
    }

    @Test
    @DisplayName("구독권명, 구독권상세설명 or 테스트")
    public void findByMenuOrSubscriptionDetailTest(){
        this.createSubscriptionList();
        List<Subscription> subscriptionList = subscriptionRepository.findByMenuOrSubscriptionDetail("테스트 구독권1", "테스트 구독권 상세 설명5");
        for(Subscription subscription : subscriptionList){
            System.out.println(subscription.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest(){
        this.createSubscriptionList();
        List<Subscription> subscriptionList = subscriptionRepository.findByPriceLessThan(100005);
        for(Subscription subscription : subscriptionList){
            System.out.println(subscription.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createSubscriptionList();
        List<Subscription> subscriptionList = subscriptionRepository.findByPriceLessThanOrderByPriceDesc(100005);
        for(Subscription subscription : subscriptionList){
            System.out.println(subscription.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 구독권 조회 테스트")
    public void findBySubscriptionDetailTest(){
        this.createSubscriptionList();
        List<Subscription> subscriptionList = subscriptionRepository.findBySubscriptionDetail("테스트 구독권 상세 설명");
        for(Subscription subscription : subscriptionList){
            System.out.println(subscription.toString());
        }
    }

    @Test
    @DisplayName("@nativeQuery 속성을 이용한 구독권 조회 테스트")
    public void findBySubscriptionDetailByNative(){
        this.createSubscriptionList();
        List<Subscription> subscriptionList = subscriptionRepository.findBySubscriptionDetailByNative("테스트 구독권 상세 설명");
        for(Subscription subscription : subscriptionList){
            System.out.println(subscription.toString());
        }
    }


}