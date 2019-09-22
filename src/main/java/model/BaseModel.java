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
  protected Integer id;

  @Column(name = "created")
  protected Date created;

  @Column(name = "updated", insertable = false, updatable = false)
  protected Date updated;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}

