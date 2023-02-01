package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     * @return null인 경우 로그인 실패
     */
    public Member login(String loginId, String password) {
        // Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
        // Member member = findMemberOptional.get();
        // if (member.getPassword().equals(password)) {
        //     return member;
        // } else {
        //     return null;
        // }

        // Stream + lambda
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password)) // password가 같으면 회원 반환
                .orElse(null); // 아닌 경우(password가 다른 경우) null 반환

    }
}
