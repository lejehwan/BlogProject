package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder// 빌더 패턴
// ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
@Entity// User 클래스가 MySQL에 테이블이 생성된다.
//@DynamicInsert // insert 시에 널인 부분을 제외시켜 줌
public class User {

    @Id// Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // DB는 RoleType이라는 것이 없다.
    @Enumerated(EnumType.STRING)
//    @ColumnDefault("'user'")
    private RoleType role;// Eunm을 사용하는 것이 좋음(ADMIN,USER)

    @CreationTimestamp// 시간이 자동 입력됨
    private Timestamp createDate;
}
