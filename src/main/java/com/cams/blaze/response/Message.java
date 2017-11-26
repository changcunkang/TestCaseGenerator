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
  @GeneratedValue(strategy= GenerationType.IDENTITY)
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
}