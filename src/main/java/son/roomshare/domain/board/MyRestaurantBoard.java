package son.roomshare.domain.board;

import son.roomshare.domain.member.Member;
import son.roomshare.domain.restaurant.Restaurant_basic;

import javax.persistence.*;

@Entity
public class MyRestaurantBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "restaurant_id")
    private Restaurant_basic restaurant;


    private String review;
    private Long stars;



}
