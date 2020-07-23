package com.songfeifan.samples.sbt.dozer;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.songfeifan.samples.sbt.model.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeanMapperService {

    @Autowired
    private Mapper mapper;

    public <T> T map(Object src, Class<T> destClass) {
        if (src == null) {
            return null;
        }
        return mapper.map(src, destClass);
    }

    public void map(Object src, Object dest) {
        if (src == null || dest == null) {
            return;
        }
        mapper.map(src, dest);
    }

    /**
     * 拷贝列表
     */
    public <T> List<T> mapList(List<?> srcList, Class<T> destClass) {
        if (srcList == null) {
            return null;
        }

        List<T> destList = new ArrayList<>();
        for (Object s: srcList) {
            destList.add(map(s, destClass));
        }

        return destList;
    }

    public <T> PageVO<T> mapPage(PageInfo<?> pageInfo, Class<T> destClass) {
        PageVO<T> p = new PageVO<>();
        p.setPageNum(pageInfo.getPageNum());
        p.setPageSize(pageInfo.getPageSize());
        p.setTotal(pageInfo.getTotal());
        p.setList(mapList(pageInfo.getList(), destClass));
        return p;
    }
}
