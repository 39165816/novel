package com.mike.novel.content.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mike.novel.content.dao.RecommondDao;
import com.mike.novel.content.service.HottestService;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.content.service.RecommondService;
import com.mike.novel.dto.HottestDo;
import com.mike.novel.dto.RecommondDo;
import com.mike.novel.dto.vo.HottestVo;
import com.mike.novel.util.ConfigConstants;
import com.mike.novel.util.NovelType;

public class RecommondServiceImpl implements RecommondService {

    private static Logger    logger = LoggerFactory.getLogger(RecommondServiceImpl.class);
    @Resource
    private RecommondDao     recommondDao;
    @Resource
    private ConfigConstants  configConstants;
    @Resource
    private HottestService   hottestService;
    @Resource
    private NovelCombServcie novelCombServcie;

    @Override
    public List<Integer> queryByType(int type) {
        Integer pid = configConstants.getPid("p" + type);
        if (pid == null) {
            return null;
        }

        return cll(pid);
    }

    private List<Integer> cll(Integer pid) {
        List<Integer> result = new ArrayList<Integer>();
        List<RecommondDo> recommonds = recommondDao.getByPid(pid);
        if (recommonds == null) {
            return result;
        }
        for (RecommondDo oneRec : recommonds) {
            result.add(oneRec.getNid());
        }

        return result;
    }

    @Override
    public List<Integer> queryByTypeDirect(int pid) {
        return cll(pid);
    }

    @Override
    public void deleteByTypeDirect(int pid) {
        recommondDao.deleteByTypeDirect(pid);
    }

    @Override
    public void save(int pid, List<Integer> nids) {
        for (Integer nid : nids) {
            RecommondDo recommondDo = new RecommondDo();
            recommondDo.setNid(nid);
            recommondDo.setPid(pid);
            recommondDao.save(recommondDo);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void random() {
        // 获取当前的热门数据
        java.util.Date now = new java.util.Date();
        Date statisticTime = new Date(now.getYear(), now.getMonth(), now.getDate());
        List<HottestDo> dos = hottestService.findByTime(statisticTime);
        if (dos == null || dos.size() == 0) {
            logger.warn("There is no data on  " + statisticTime.toString());
            return;
        }

        // 添加是否完结、本站点是否包含
        List<HottestVo> vos = novelCombServcie.getAddtionalInfo(dos);
        // 过滤并排序
        List<HottestVo> filted = new ArrayList<HottestVo>();
        for (HottestVo one : vos) {
            if (one.isIntroduced()) {
                filted.add(one);
            }
        }
        Collections.sort(filted);

        // 处理首页推荐
        processFirstPage(filted);
        // 处理全本推荐
        processQuan(filted);

        // 处理分类推荐
        processClass(filted);
    }

    private void processFirstPage(List<HottestVo> filted) {
        List<Integer> nids = getNeeds(filted, 6);
        // 保存
        save(201, nids);
        clear(201, 6);
    }

    private void processQuan(List<HottestVo> filted) {
        ArrayList<HottestVo> real = new ArrayList<HottestVo>();
        for (HottestVo one : filted) {
            if (one.isFinished()) {
                real.add(one);
            }
        }

        List<Integer> nids = getNeeds(real, 6);
        // 保存
        save(301, nids);
        clear(301, 6);
    }

    private void processClass(List<HottestVo> filted) {
        for (NovelType oneNovelType : NovelType.values()) {
            int type = oneNovelType.type;
            // 获取自己的数据
            ArrayList<HottestVo> real = new ArrayList<HottestVo>();
            for (HottestVo one : filted) {
                if (one.getType() == type) {
                    real.add(one);
                }
            }

            List<Integer> nids = getNeeds(real, 9);
            // 保存
            save(type + 100, nids);
            clear(type + 100, 9);
        }

    }

    private List<Integer> getNeeds(List<HottestVo> real, int size) {
        List<Integer> nids = new ArrayList<Integer>();
        if (real.size() > size) {
            Random random = new Random();
            while (nids.size() < size) {
                int nextInt = random.nextInt(real.size());
                if (!nids.contains(real.get(nextInt).getNid())) {
                    nids.add(real.get(nextInt).getNid());
                }
            }
        } else {
            for (HottestVo one : real) {
                nids.add(one.getNid());
            }
        }
        return nids;
    }

    private void clear(int pid, int size) {
        // 清楚多余的
        List<RecommondDo> recommonds = recommondDao.getByPid(pid);
        HashSet<Integer> keep = new HashSet<Integer>();
        List<Integer> ids = new ArrayList<Integer>();
        for (RecommondDo one : recommonds) {
            if (keep.size() < size && !keep.contains(one.getNid())) {
                keep.add(one.getNid());
            } else {
                ids.add(one.getId());
            }
        }
        if (ids.size() != 0) {
            recommondDao.deleteByIds(ids);
        }
    }
}
