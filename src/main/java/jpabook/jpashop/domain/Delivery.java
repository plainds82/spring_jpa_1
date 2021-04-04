package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    // 무조건 String 으로 해야됨. ordinal로 하면 int 형으로 되는데
    // 타입이 하나 새로 생성되면 장애 발생함
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;  // READY, COMP
}
