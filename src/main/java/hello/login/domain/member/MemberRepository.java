package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L; // static 사용

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }


    // 데이터가 없을 수도 있기 때문에 Optional 사용
    public Optional<Member> findByLoginId(String loginId) {
        // List<Member> all = findAll();
        // for (Member m : all) {
        //     if (m.getLoginId().equals(loginId)) {
        //         return Optional.of(m);
        //     }
        // }
        // return Optional.empty();

        // stream + lambda 사용 (권장되는 방법)
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId)) // 이 조건에 만족하는 데이터만 남김
                .findFirst(); // 가장 먼저 나오는 데이터만 pick
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
