package com.efub.cafetimes.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="store")
public class Store extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userId")
    private User owner;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String storeName;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String address;

    @Column(columnDefinition = "TEXT")
    private String businessInfo;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column
    private Boolean isConfirmed;

    @Column
    private Integer subscriptionCount;

    @Builder
    public Store(User owner, String storeName, String address, String businessInfo, String image, Boolean isConfirmed, Integer subscriptionCount) {
        this.owner = owner;
        this.storeName = storeName;
        this.address = address;
        this.businessInfo = businessInfo;
        this.image = image;
        this.isConfirmed = isConfirmed;
        this.subscriptionCount = subscriptionCount;
    }

    @PrePersist
    public void setDefaultValues(){
        this.isConfirmed = this.isConfirmed == null ? false : this.isConfirmed;
        this.subscriptionCount = this.subscriptionCount == null ? 0 : this.subscriptionCount;
    }
}
