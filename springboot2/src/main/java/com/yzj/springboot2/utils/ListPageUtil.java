package com.yzj.springboot2.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/** 
 * @author svs: 
 * @version 创建时间：2016-12-16 下午02:28:23 
 * 类说明 
 */
public class ListPageUtil<T> {
    /**原集合*/
    private List<T> data;
 
    /** 上一页 */
    private int lastPage;

    /** 当前页 */
    private int nowPage;

    /** 下一页 */
    private int nextPage;
//    
    /** 每页条数 */
    private int pageSize;

    /** 总页数 */
    private int totalPage;

    /** 总数据条数 */
    private int totalCount;
    
    @SuppressWarnings("unchecked")
	public ListPageUtil(List<T> list, int nowPage, int pageSize) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("data must be not empty!");
        }
 
        this.data =list;
        this.pageSize = pageSize;
        /*this.totalPage = data.size()/pageSize;
        if(data.size()%pageSize!=0){
            this.totalPage++;
        }*/
        
        this.nowPage = nowPage;
        this.totalCount = list.size();
        this.totalPage = (totalCount + pageSize - 1) / pageSize;
        this.lastPage = nowPage-1>1? nowPage-1:1;
        this.nextPage = nowPage>=totalPage? totalPage: nowPage + 1;
        
    }
 
    /**
     * 得到分页后的数据
     *
     * @param pageNum 页码
     * @return 分页后结果
     */
    public List<T> getPagedList() {
        int fromIndex = (nowPage - 1) * pageSize;
        if (fromIndex >= data.size()) {
            return Collections.emptyList();//空数组
        }
        if(fromIndex<0){
            return Collections.emptyList();//空数组
        }
        int toIndex = nowPage * pageSize;
        if (toIndex >= data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public List<T> getData() {
        return data;
    }
    public int getLastPage() {
        return lastPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }
}