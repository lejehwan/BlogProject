package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob// 대용량 데이터
    private String contents;// 섬머노트 라이브러리 <html> 태그가 섞여서 디자인 됨.

    private int count;// 조회수

//    FetchType.EAGER : 조인해서 가져옴
//    FetchType.LAZY : 필요에 의해서 셀렉트로 가져옴

    @ManyToOne(fetch = FetchType.EAGER)// Many = Board, User = One -> 한명의 유저는 여러개의 게시글을 작성 할 수 있다.
    @JoinColumn(name = "userId")
    private User user;// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)// 하나의 게시글에는 여러개의 답글이 있다.
    // mappedBy : 연관관계의 주인이 아니다 (난 FK가 아니다) > DB에 컬럼 생성 X
    // FK가 필요없음 왜냐하면 board에서 가져옴
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
