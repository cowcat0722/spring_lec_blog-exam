package shop.mtcoding.blog._core;

public class PagingUtil {
    // 마지막 페이지 인지
    public static boolean isLast(int currentPage, int totalCount){
        int totalPageCount = getTotalPageIndexCount(totalCount);
        return currentPage == totalPageCount;
    }

    // 첫 번째 페이지 인지
    public static boolean isFirst(int currentPage){
        return currentPage == 0;
    }

    // 전체 페이지 2 -> 0,1,2 totalPageCount (PAGING_COUNT = 5, 총 게시물 = totalCount)
    public static int getTotalPageIndexCount(int totalCount){
        int totalPageCount = (totalCount-1)/Constant.PAGING_COUNT;
        return totalPageCount;
    }

    public static int getTotalPageCount(int totalCount){
        int totalPageCount = (totalCount)/Constant.PAGING_COUNT;
        return totalPageCount;
    }
}
