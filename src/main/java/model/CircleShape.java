package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="circle_shape")
public class CircleShape extends Shape{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="size")
    @NotNull
    private int size;

    public CircleShape(){

    }

    public CircleShape(int size) {
        this.size = size;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CircleShape{" +
                "id=" + id +
                ", size=" + size +
                '}';
    }
}
