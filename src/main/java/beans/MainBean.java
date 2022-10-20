package beans;

import dao.CoordinateDao;
import model.CoordinateData;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class MainBean {

    @EJB
    CoordinateDao coordinateDao = new CoordinateDao();
    private CoordinateData newData;
    private List<CoordinateData> dataList = new ArrayList<CoordinateData>();
    private Integer counter = 0;

    public void addData() {
        newData.setStatus(false);
        dataList.add(newData);
        System.out.println(dataList.size());
        newData = new CoordinateData();
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
        return dataList;
    }

    public void setDataList(List<CoordinateData> dataList) {
        this.dataList = dataList;
    }

    public void clear() {
        dataList.clear();
    }

    public void createData() throws SQLException {
        coordinateDao.createCoordinateData(this.newData);
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

    public String getAllElements(){
        StringBuilder str = new StringBuilder("");
        for(CoordinateData data:dataList){
            str.append(data.toString()).append("|");
        }
        return str.toString();
    }
}