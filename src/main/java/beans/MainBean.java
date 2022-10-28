package beans;

import DBworkers.DBWorker;
import model.CircleShape;
import model.CoordinateData;
import model.Shape;
import model.SquareShape;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class MainBean {

    private CoordinateData newData;
    private Shape shape;
    private String shape_name;

    public String getShape_name() {
        return shape_name;
    }

    public void setShape_name(String shape_name) {
        this.newData.setShape(shape_name);
        this.shape_name = shape_name;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    private List<CoordinateData> dataList = new ArrayList<CoordinateData>();
    private Integer counter = 0;

    public void addData() {
        newData.setStatus(false);
        dataList.add(newData);
        newData = new CoordinateData();
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public CoordinateData getNewData() {
        if(this.newData == null){
            this.newData = new CoordinateData();
        }
        return newData;
    }

    public void setNewData(CoordinateData newData) {
        this.newData = newData;
    }

    public List<CoordinateData> getDataList() {
        return DBWorker.getAllElements();
    }

    public void setDataList(List<CoordinateData> dataList) {
        this.dataList = dataList;
    }

    public void clear() {
        dataList.clear();
    }

    public void createData() throws SQLException {
        DBWorker.addElement(this.newData,this.shape);
        addData();
    }
    public Integer getCounter(){
        if(counter>dataList.size())
        {
            counter = 0;
        }
        return counter++;
    }

    public CoordinateData get(){
        if(dataList.size()==0){
            return null;
        }else{
            if(counter==dataList.size())
            {
                counter=0;
            }
            counter = counter + 1;
            return dataList.get(counter-1);
        }
    }
    public Shape getShape() {
        if(this.shape == null){
            this.shape=new Shape();
        }
        return shape;
    }
    public String getAllElements(){
        StringBuilder str = new StringBuilder("");
        for(CoordinateData data:dataList){
            str.append(data.toString()).append("|");
        }
        return str.toString();
    }
}