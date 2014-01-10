package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.NovelPageDo;

public interface NovelPageService {

	NovelPageDo queryByCid(long cid);

	List<NovelPageDo> findByCids(List<Long> cid);

}
