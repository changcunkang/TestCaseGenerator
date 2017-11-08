package com.cams.blaze.response;
/**
 * 
 * @author YuHuaPeng
 *
 */
public class Message
{
  private Integer messageNumber;
  private Integer severityCode;
  private String description;
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