package com.cams.blaze.response;
/**
 * 
 * @author YuHuaPeng
 *
 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MessageList
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
  @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)@JoinColumn(name="parent_id")
	private List<Message> message = new ArrayList<Message>();
  @Column
	private Integer statusCode;
  @Column
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