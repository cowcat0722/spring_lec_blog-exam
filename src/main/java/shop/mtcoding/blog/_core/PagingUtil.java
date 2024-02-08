package shop.mtcoding.blog._core;

public class PagingUtil {
    // 마지막 페이지 인지
    public static boolean isLast(int currentPage, int totalCount){
        int totalPageCount = getTotalPageCount(totalCount);
        return currentPage == totalPageCount;
    }

    // 첫 번째 페이지 인지
    public static boolean isFirst(int currentPage){
        return currentPage == 1;
    }

    // 진짜 토탈페이지 3 -> 3개의 페이지
    public static int getTotalPageCount(int totalCount){
        int totalPageCount;
        if(totalCount%5 == 0){
            totalPageCount = totalCount/Constant.PAGING_COUNT;
        } else {
            totalPageCount = totalCount/Constant.PAGING_COUNT+1;
        }
        return totalPageCount;
    }
}
