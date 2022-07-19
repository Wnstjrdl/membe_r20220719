package com.its.example.service;

import com.its.example.dto.MemberDTO;
import com.its.example.entity.MemberEntity;
import com.its.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor

public class MemberService {
    private final MemberRepository memberRepository;
    public Long save(MemberDTO memberDTO) throws IOException {
        MultipartFile memberFile=memberDTO.getMemberFile();
        String memberProfile=memberFile.getOriginalFilename();
        memberProfile= System.currentTimeMillis()+"_"+memberProfile;
        String savePath= "D:\\springboot_img\\"+memberProfile;

        if(!memberFile.isEmpty()){
            memberFile.transferTo(new File(savePath));
        }
        memberDTO.setMemberProfile(memberProfile);
        Long savedId= memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId();
            return  savedId;
    }
}
