package za.ac.nwu.ac.web.sb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.exception.MemberNotFoundException;
import za.ac.nwu.ac.repository.MemberRepository;

import java.util.List;

@RestController
class MemberController {

//    private final MemberRepository memberRepository;
//
//    MemberController(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//
//    @GetMapping("/members")
//    List<Member> all() {
//        return MemberRepository.findAll();
//    }
//
//    @PostMapping("/members")
//    Member newMember(@RequestBody Member newMember) {
//        return MemberRepository.save(newMember);
//    }
//
//    @GetMapping("/members/{id}")
//    Member one(@PathVariable Long id) {
//        return MemberRepository.findById(id)
//                .orElseThrow(() -> new MemberNotFoundException(id));
//    }
//
//    @PutMapping("/members/{id}")
//    Member replaceMember(@RequestBody Member newMember, @PathVariable Long id) {
//
//        return MemberRepository.findById(id)
//                .map(member -> {
//                    member.setName(newMember.getName());
//                    member.setSurname(newMember.getSurname());
//                    member.setMemberId(newMember.getMemberId());
//                    member.setEmail(newMember.getEmail());
//                    member.setMiles(newMember.getMiles());
//                    member.setGameBoard(newMember.getGameBoard());
//                    member.setGoals(newMember.getGoals());
//                    member.setRewards(newMember.getRewards());
//                    return MemberRepository.save(member);
//                })
//                .orElseGet(() -> {
//                    newMember.setMemberId(id);
//                    return MemberRepository.save(newMember);
//                });
//    }
//
//    @DeleteMapping("/mmebers/{id}")
//    void deleteMember(@PathVariable Long id) {
//        MemberRepository.deleteById(id);
//    }

    @GetMapping("/logIn")
    public String logInForm(@RequestParam(name="idnum",defaultValue = "000002") String idNum, Model model) {
        model.addAttribute("idNum", idNum);
        return "logIn";
    }

    @PostMapping("/logIn")
    public String logIn(@RequestParam(name="idnum",defaultValue = "000002") String idNum, Model model) {
        model.addAttribute("idNum", idNum);
        return "logInForm";
    }
}
