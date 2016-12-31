package jp.gr.java_conf.hungrywalker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.gr.java_conf.hungrywalker.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>
{
    public MemberEntity findByMailAddress(String mailAddress);
}
