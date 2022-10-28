package model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "dots")
public class CoordinateData implements Serializable {

    //    @GenericGenerator(name="seq" , strategy="increment")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "x")
    @NotNull
    private Double x;

    @Column(name = "y")
    @NotNull
    private Double y;

    @Column(name = "r")
    @NotNull
    private Integer r;

    @NotNull
    @Column(name = "status")
    private boolean status;
    @NotNull
    @Column(name = "shape")
    private String shape;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "circle_shape_id")
    private CircleShape circleShape;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "square_shape_id")
    private SquareShape squareShape;


    public CoordinateData() {

    }

    public CoordinateData(Double x, Double y, Integer r, boolean status) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(id + " " + x + " " + y + " " + r + " " + status + " " + shape);
        if(shape.equals("circle")){
            stringBuilder.append(" ").append(circleShape.getSize());
        }else{
            stringBuilder.append(" ").append(squareShape.getSize());
        }
        return stringBuilder.toString();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = AreaCheck.check(x, y, r);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Shape getCircleShape() {
        return circleShape;
    }

    public void setCircleShape(CircleShape circleShape) {
        this.shape = "circle";
        this.circleShape = circleShape;
    }

    public Shape getSquareShape() {
        return squareShape;
    }

    public void setSquareShape(SquareShape squareShape) {
        this.shape = "square";
        this.squareShape = squareShape;
    }
}
