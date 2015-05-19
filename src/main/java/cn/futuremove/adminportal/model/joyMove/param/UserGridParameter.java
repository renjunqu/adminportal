package cn.futuremove.adminportal.model.joyMove.param;

/**
 * Created by figoxu on 15/4/21.
 */
public class UserGridParameter {

    private Integer pageNo;
    private Integer pageSize;
    private String query;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
