package io.nebulas.explorer.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Page Iterator
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
 */
@Data
@ToString
public class PageIterator<T> implements Serializable {
    private static final int MAX_PAGE_SIZE = 1000;

    private int page;
    private int pageSize;
    private int totalPage;
    private long totalCount;
    private List<T> data;

    public static <T> PageIterator<T> create(int page, int pageSize, long totalCount) {
        if (page < 0) {
            page = 0;
        }
        PageIterator<T> instance = new PageIterator<>();
        instance.setPage(page);
        instance.setPageSize(pageSize);
        instance.setTotalCount(totalCount);
        instance.setTotalPage((int) Math.ceil((double) totalCount / (double) pageSize));
        return instance;
    }

    @JSONField(serialize = false)
    public int getOffset() {
        return page * pageSize;
    }

    private void setPage(int page) {
        this.page = page < 0 ? 0 : page;
    }

    private void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else if (pageSize > MAX_PAGE_SIZE) {
            this.pageSize = MAX_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

}

