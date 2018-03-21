package com.cams.blaze.response;

import javax.persistence.*;

/**
 * 
 * @author YuHuaPeng
 *
 */
@Entity
public class Message
{
  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE)
  @Column
  private Long id;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  @Column
	private Integer messageNumber;
  @Column
	private Integer severityCode;
  @Column
	private String description;
  @Column
	private String resolution;

  public Integer getMessageNumber()
  {
    return this.messageNumber;
  }
  public void setMessageNumber(Integer messageNumber) {
    this.messageNumber = messageNumber;
  }
  public Integer getSeverityCode() {
    return this.severityCode;
  }
  public void setSeverityCode(Integer severityCode) {
    this.severityCode = severityCode;
  }
  public String getDescription() {
    return this.description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getResolution() {
    return this.resolution;
  }
  public void setResolution(String resolution) {
    this.resolution = resolution;
  }
  @Column
  private Long parent_id;

  public Long getParent_id() {
    return parent_id;
  }

  public void setParent_id(Long parent_id) {
    this.parent_id = parent_id;
  }
}