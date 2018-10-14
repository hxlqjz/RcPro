package com.kdgcsoft.power.entity.fw.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "COM_C_ATT")
public class ComCAtt implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long attId;
	//
	private String tblName;
	//
	private String tblCol;
	//
	private Long tblKey;
	//
	private String fileName;
	//
	private String fileTyp;
	//
	private String filePath;
	//
	private Double fileSize;
	//
	private String fileVer;
	//
	private String memo;
	private String createBy;
	private Date createDate;
	private String modifyBy;
	private Date modifyDate;
	


	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="ComCAtt")
	@TableGenerator(name="ComCAtt",pkColumnValue = "COM_C_ATT", table = "TB_GENERATOR", pkColumnName = "gen_key", valueColumnName = "gen_value",  allocationSize = 10)
	@Column(name="ATT_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getAttId() {
		return attId;
	}
	public void setAttId(Long attId) {
		this.attId = attId;
	}

	@Column(name = "TBL_NAME", length = 64)
	public String getTblName() {
		return tblName;
	}
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}

	@Column(name = "TBL_COL", length = 64)
	public String getTblCol() {
		return tblCol;
	}
	public void setTblCol(String tblCol) {
		this.tblCol = tblCol;
	}

	@Column(name = "TBL_KEY", precision = 10, scale = 0)
	public Long getTblKey() {
		return tblKey;
	}
	public void setTblKey(Long tblKey) {
		this.tblKey = tblKey;
	}

	@Column(name = "FILE_NAME", length = 256)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_TYP", length = 256)
	public String getFileTyp() {
		return fileTyp;
	}
	public void setFileTyp(String fileTyp) {
		this.fileTyp = fileTyp;
	}

	@Column(name = "FILE_PATH", length = 1024)
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "FILE_SIZE", precision = 15, scale = 4)
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "FILE_VER", length = 32)
	public String getFileVer() {
		return fileVer;
	}
	public void setFileVer(String fileVer) {
		this.fileVer = fileVer;
	}

	@Column(name = "MEMO", length = 512)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "CREATE_BY", length = 32)
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "MODIFY_BY", length = 32)
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFY_DATE", length = 7)
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
