package cn.com.tj.byhy.util;

public class Pager {
	private int pageNow;//当前页数
	private int pageSize=8;//每页显示的记录
	private int totalPage;//总共所需的页数
	private int totalSize;//总共查到的记录
	private boolean hasFirst;//是否有首页
	private  boolean hasPre;//是否有前一页
	private boolean hasNext;//是否有下一页
	private boolean hasLast;//是否有最后一页

	//利用构造函数为变量赋值
	public Pager(int pageNow,int totalSize){
		//利用构造函数为变量赋值
		this.pageNow=pageNow;
		this.totalSize=totalSize;
	}

	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//一共多少页的算法
	public int getTotalPage() {
		totalPage = getTotalSize()/getPageSize();
		if(totalSize%pageSize!=0){
			totalPage++;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	//如果当前是第一页就没有首页页
	public boolean isHasFirst() {
		if(pageNow==1)
			return false;
		else
			return true;
	}
	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}
	//如果有首页就有前一页，因为有首页表明 其不是第一页  
	public boolean isHasPre() {
		if(this.isHasFirst())
			return true;
		else
			return false;
	}
	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	//如果有尾页就有下一页
	public boolean isHasNext() {
		if(isHasLast())
			return true;
		else
			return false;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	//如果不是最后一页就有尾页
	public boolean isHasLast() {
		if(pageNow==this.getTotalPage())
			return false;
		else 
			return true;
	}
	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}
}
