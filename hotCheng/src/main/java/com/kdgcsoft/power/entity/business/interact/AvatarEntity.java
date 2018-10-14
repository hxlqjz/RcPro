package com.kdgcsoft.power.entity.business.interact;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity
@Table(name="PORTAL_W_HEAD_PORTRAIT")
public class AvatarEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long headPortraitId;
	private Long userId;
	private byte[] headPortrait;
	
	private String headPortraitS;

	@Transient
	public String getHeadPortraitS() {
		return headPortraitS;
	}
	public void setHeadPortraitS(String headPortraitS) {
		this.headPortraitS = headPortraitS;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="PORTAL_W_HEAD_PORTRAIT")
	@TableGenerator(name="PORTAL_W_HEAD_PORTRAIT",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "PORTAL_W_HEAD_PORTRAIT",allocationSize = 1)
	@Column(name ="HEAD_PORTRAIT_ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getHeadPortraitId() {
		return headPortraitId;
	}
	public void setHeadPortraitId(Long headPortraitId) {
		this.headPortraitId = headPortraitId;
	}
	@Column(name="USER_ID")
		public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Lob 
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="HEAD_PORTRAIT")
	public byte[] getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(byte[] headPortrait) {
		this.headPortrait = headPortrait;
	}
}
