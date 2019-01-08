/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jiangxin
 */
@Embeddable
public class HouravgPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    @Basic(optional = false)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public HouravgPK() {
    }

    public HouravgPK(long id, Date createTime) {
        this.id = id;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (createTime != null ? createTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HouravgPK)) {
            return false;
        }
        HouravgPK other = (HouravgPK) object;
        if (this.id != other.id) {
            return false;
        }
        if ((this.createTime == null && other.createTime != null) || (this.createTime != null && !this.createTime.equals(other.createTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.HouravgPK[ id=" + id + ", createTime=" + createTime + " ]";
    }
    
}
