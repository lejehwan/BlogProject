package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

// html이 아닌 data를 리턴해줌
@RestController
public class DummyControllerTest {

    @Autowired// 의존성 주입(DI)
    private UserRepository userRepository;

    // save 함수는
    // id를 전달하지 않으면 insert를 해주고
    // id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    // id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 함
    //email, password

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);
        } catch (Exception e){
            return "삭제에 실패";
        }
        return "삭제되었습니다.";
    }

    @Transactional// 함수 종료시 자동 commit
    @PutMapping("/dummy/user{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){// json 데이터를 요청 => Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 받아줌)

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패햇습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);

        // 더티 체킹
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 페이징
    // 한 페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 없습니다.");
            }
        });
        // 요청 -> 웹 브라우저
        // user 객체 = 자바 오브젝트
        // 변환 (웹 브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
        // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작용
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져줌
        return user;
    }

//    // 위를 람다식으로 변환
//    @GetMapping("/dummy/user/{id}")
//    public User detail(@PathVariable int id){
//        User user = userRepository.findById(id).orElseThrow(()-> {
//           return new IllegalArgumentException("해당 사용자가 없습니다.");
//        });
//        return user;
//    }

    @PostMapping("/dummy/join")
    public String join(User user){
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
