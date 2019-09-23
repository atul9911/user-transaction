package model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseModel {

  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Integer id;

  @Column(name = "created")
  private Date created;

  @Column(name = "updated", insertable = false, updatable = false)
  private Date updated;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}

