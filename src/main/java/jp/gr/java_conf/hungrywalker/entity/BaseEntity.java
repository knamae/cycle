package jp.gr.java_conf.hungrywalker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * エンティティの共通部分
 *
 * @author knamae
 */
@MappedSuperclass
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 3737496906442618130L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    Long memberId;

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return this.memberId;
    }

    @CreatedBy
    private Long createMemberId;

    public Long getCreateMemberId()
    {
        return this.createMemberId;
    }

    public void setCreateMemberId(Long createMemberId)
    {
        this.createMemberId = createMemberId;
    }

    @CreatedDate
    private Date createDate;

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    @LastModifiedBy
    private Long modifyMemberId;

    public Long getModifyMemberId()
    {
        return this.modifyMemberId;
    }

    public void setModifyMemberId(Long modifyMemberId)
    {
        this.modifyMemberId = modifyMemberId;
    }

    @LastModifiedDate
    private Date modifyDate;

    public Date getModifyDate()
    {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate)
    {
        this.modifyDate = modifyDate;
    }
}
