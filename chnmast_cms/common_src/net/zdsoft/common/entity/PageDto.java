package net.zdsoft.common.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PageDto implements Serializable {
    private static final long serialVersionUID = 5883636778638787974L;
    private int totalPage = 1; // 总页数
    private int totalNum = 0; // 总记录数
    private int rowNum = 20; // 每页行数
    private int currentPage = 1; // 当前页码

    private int selectRows = 0;// 当前查询获得的数据行数
    private boolean resetRowNum = false;

    private String functionName; // 方法名字

    /**
     * 得到从数据库查询时记录的起始行号，注意从1开始计算
     *
     * @return
     */
    public int getStartIndex() {
        if (getCurrentPage() <= 0) {
            return 1;
        }

        return getRowNum() * (getCurrentPage() - 1) + 1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage == 0) {
            this.currentPage = 1;
        }
        else {
            this.currentPage = currentPage;
        }
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
        this.resetRowNum = true;
    }

    public int getTotalPage() {
        if (totalNum != 0 || rowNum != 0) {
            totalPage = totalNum / rowNum;
            if (totalNum % rowNum != 0) {
                totalPage++;
            }
        }
        return totalPage;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getSelectRows() {
        return selectRows;
    }

    public void setSelectRows(int selectRows) {
        this.selectRows = selectRows;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public boolean isResetRowNum() {
        return resetRowNum;
    }

    public void setResetRowNum(boolean resetRowNum) {
        this.resetRowNum = resetRowNum;
    }

}
