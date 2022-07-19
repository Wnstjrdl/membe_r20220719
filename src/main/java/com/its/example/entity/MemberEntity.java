package com.its.example.entity;

import com.its.example.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(length = 30,unique = true)
    private String memberEmail;
    @Column(length = 30,nullable = false)
    private String memberPassword;
    @Column(length = 20,nullable = false)
    private String memberName;
    @Column(length = 30)
    private String memberMobile;
    @Column(length = 500)
    private String memberProfile;

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.PERSIST,orphanRemoval = false,fetch =FetchType.LAZY)
    List<BoardEntity> boardEntityList=new ArrayList<>();




    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity= new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberProfile(memberDTO.getMemberProfile());

        return memberEntity;
    }

}
