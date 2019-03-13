package me.lindanpeng.qunawan.core.util;

import java.util.List;

public class PageHelper {
   public static class PageResult<T>{
        private int startPage;
        private int pageSize;
        private int total;
        private int nextPage;
        private int prevPage;
        private int fistPage;
        private int lastPage;
        private List<T> pageData;

        public int getStartPage() {
            return startPage;
        }

        public void setStartPage(int startPage) {
            this.startPage = startPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getPrevPage() {
            return prevPage;
        }

        public void setPrevPage(int prevPage) {
            this.prevPage = prevPage;
        }

        public int getFistPage() {
            return fistPage;
        }

        public void setFistPage(int fistPage) {
            this.fistPage = fistPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<T> getPageData() {
            return pageData;
        }

        public void setPageData(List<T> pageData) {
            this.pageData = pageData;
        }
    }
   public static class PageQuery{
        private int start;
        private int limit;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }
    }
    public static <T> PageResult<T> getPageResult(List<T> list){
        return null;
    }
    public static PageQuery getPageQuery(int page,int pageSize){
        PageQuery pageQuery=new PageQuery();
        pageQuery.setStart(page>0?(page-1)*pageSize:0);
        pageQuery.setLimit(pageSize);
        return pageQuery;
    }
}
