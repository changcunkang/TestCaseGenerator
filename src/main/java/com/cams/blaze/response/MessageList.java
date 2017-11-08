package com.cams.blaze.response;
/**
 * 
 * @author YuHuaPeng
 *
 */
import java.util.ArrayList;
import java.util.List;


public class MessageList
{
  private List<Message> message = new ArrayList<Message>();
  private Integer statusCode;
  private String statusDescription; 

  public List<Message> getMessage()
  {
    return this.message;
  }

  public void setMessage(List<Message> message) {
    this.message = message;
  }

  public Integer getStatusCode() {
    return this.statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatusDescription() {
    return this.statusDescription;
  }

  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }
}