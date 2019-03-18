package me.lindanpeng.qunawan.core.util;

import java.util.List;

public class PageHelper {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 50;

    public static class PageResult<T> {
        private int currentPage;
        private int pageSize;
        private int pageTotal;
        private int recordTotal;
        private int nextPage;
        private int prevPage;
        private int fistPage;
        private int lastPage;
        private List<T> content;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
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

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getRecordTotal() {
            return recordTotal;
        }

        public void setRecordTotal(int recordTotal) {
            this.recordTotal = recordTotal;
        }

        public List<T> getContent() {
            return content;
        }

        public void setContent(List<T> content) {
            this.content = content;
        }
    }

    public static class PageQuery {
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

    public static <T> PageResult<T> getPageResult(List<T> list, int count,int currentPage) {
        return getPageResult(list, count, currentPage,DEFAULT_PAGE_SIZE);
    }

    public static <T> PageResult<T> getPageResult(List<T> list, int count, int currentPage, int pageSize) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.recordTotal = count;
        pageResult.pageSize = pageSize;
        pageResult.pageTotal = pageResult.recordTotal % pageResult.pageSize > 0 ? pageResult.recordTotal / pageResult.pageSize + 1 : pageResult.recordTotal / pageResult.pageSize;
        pageResult.lastPage = pageResult.pageTotal;
        if (currentPage > pageResult.pageTotal)
            pageResult.currentPage = pageResult.pageTotal;
        else if (currentPage <= 0)
            pageResult.currentPage = 1;
        else
            pageResult.currentPage=currentPage;
        if (pageResult.currentPage > pageResult.fistPage) {
            pageResult.prevPage = pageResult.currentPage - 1;
        } else {
            pageResult.prevPage = pageResult.fistPage;
        }
        if (pageResult.currentPage < pageResult.lastPage) {
            pageResult.nextPage = pageResult.currentPage + 1;
        } else {
            pageResult.nextPage = pageResult.lastPage;
        }

        pageResult.setContent(list);
        return pageResult;
    }

    public static PageQuery getPageQuery(int currentPage, int pageSize) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setStart(currentPage > 0 ? (currentPage - 1) * pageSize : 0);
        pageQuery.setLimit(pageSize);
        return pageQuery;
    }
}
