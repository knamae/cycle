package jp.gr.java_conf.hungrywalker.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.gr.java_conf.hungrywalker.entity.MemberEntity;
import jp.gr.java_conf.hungrywalker.repository.MemberRepository;

@Service
public class UserInfoServiceImpl implements UserDetailsService
{
    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException
    {
        if (StringUtils.isEmpty(mailAddress))
        {
            throw new UsernameNotFoundException("");
        }

        MemberEntity memberEntity = this.memberRepository.findByMailAddress(mailAddress);
        if (memberEntity == null)
        {
            throw new UsernameNotFoundException("");
        }

        return memberEntity;
    }
}
