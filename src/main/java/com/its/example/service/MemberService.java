package com.its.example.service;

import com.its.example.dto.MemberDTO;
import com.its.example.entity.MemberEntity;
import com.its.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(optionalMemberEntity.isPresent()){
            MemberEntity loginEntity= optionalMemberEntity.get();
            if(loginEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                return MemberDTO.toMemberDTO(loginEntity);
            }else {
                return null;
            }
            }else {
            return null;
        }
        }

    public String EmailCheck(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if(optionalMemberEntity.isEmpty()){
            return "ok";
        }else {
            return "no";
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList= memberRepository.findAll();
        List<MemberDTO> memberDTOList=new ArrayList<>();
        for (MemberEntity member: memberEntityList){
            MemberDTO memberDTO=MemberDTO.toMemberDTO(member);
            memberDTOList.add(memberDTO);
        }
        return  memberDTOList;
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity>optionalMemberEntity=memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateEntity(memberDTO));
    }
}

