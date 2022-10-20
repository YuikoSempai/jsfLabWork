package model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CoordinateData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false)
    private Long id;
    Double x = 1.0;
    Double y = 1.0;
    Integer r = 3;
    boolean status;

    @Override
    public String toString() {
        return x + " " + y + " " + r + " " + status;
    }

    public void setStatus(boolean status) {
        this.status = AreaCheck.check(x,y,r);
    }

    public boolean isStatus() {
        return status;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Integer getR() {
        return r;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
