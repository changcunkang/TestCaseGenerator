package com.cams.blaze.response;

/**
 * 评分模型返回信息
 * @author YuHuaPeng
 *
 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class ScoreModelReturnInfo
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
  @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ScoredCharacteristic> scoredCharacteristic = new ArrayList<ScoredCharacteristic>();
  @Column
	private String associationID;
  @Column
	private String scoreModelName;
  @Column
	private String versionID;
  @Column
	private String scoreModelID;
  @Column
	private Double finalScore;
  @Column
	private Double rawScore;
  @Column
	private Double initialScore;
  @Column
	private Date scoreDateTime;
  @Column
	private Integer unexpectedCount;
  @Column
	private Integer characteristicCount;
 
  public List<ScoredCharacteristic> getScoredCharacteristic()
  {
    return this.scoredCharacteristic;
  }

  public void setScoredCharacteristic(List<ScoredCharacteristic> scoredCharacteristic) {
    this.scoredCharacteristic = scoredCharacteristic;
  }
  public String getAssociationID() {
    return this.associationID;
  }
  public void setAssociationID(String associationID) {
    this.associationID = associationID;
  }
  public String getScoreModelName() {
    return this.scoreModelName;
  }
  public void setScoreModelName(String scoreModelName) {
    this.scoreModelName = scoreModelName;
  }
  public Double getFinalScore() {
    return this.finalScore;
  }
  public void setFinalScore(Double finalScore) {
    this.finalScore = finalScore;
  }
  public Double getRawScore() {
    return this.rawScore;
  }
  public void setRawScore(Double rawScore) {
    this.rawScore = rawScore;
  }
  public Double getInitialScore() {
    return this.initialScore;
  }
  public void setInitialScore(Double initialScore) {
    this.initialScore = initialScore;
  }
  public Date getScoreDateTime() {
    return this.scoreDateTime;
  }
  public void setScoreDateTime(Date scoreDateTime) {
    this.scoreDateTime = scoreDateTime;
  }
  public Integer getUnexpectedCount() {
    return this.unexpectedCount;
  }
  public void setUnexpectedCount(Integer unexpectedCount) {
    this.unexpectedCount = unexpectedCount;
  }
  public Integer getCharacteristicCount() {
    return this.characteristicCount;
  }
  public void setCharacteristicCount(Integer characteristicCount) {
    this.characteristicCount = characteristicCount;
  }

public String getScoreModelID() {
	return scoreModelID;
}

public void setScoreModelID(String scoreModelID) {
	this.scoreModelID = scoreModelID;
}

public String getVersionID() {
	return versionID;
}

public void setVersionID(String versionID) {
	this.versionID = versionID;
}
}
