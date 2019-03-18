package me.lindanpeng.qunawan.web.service;

import com.alibaba.druid.util.StringUtils;
import me.lindanpeng.qunawan.core.dao.ScenicDao;
import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.entity.ScenicImg;
import me.lindanpeng.qunawan.core.entity.ScenicIntro;
import me.lindanpeng.qunawan.core.es.model.EsScenic;
import me.lindanpeng.qunawan.core.es.repository.EsScenicRepository;
import me.lindanpeng.qunawan.core.util.PageHelper;
import me.lindanpeng.qunawan.web.vo.ScenicDetailVo;
import me.lindanpeng.qunawan.web.vo.ScenicPreviewVo;
import me.lindanpeng.qunawan.web.vo.ScenicRankVo;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightUtils;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScenicService extends AbstractService {
    private final static Logger logger= LoggerFactory.getLogger(ScenicService.class);
    @Autowired
    EsScenicRepository esScenicRepository;

    public PageHelper.PageResult<ScenicPreviewVo> listHotScenicPreview(Integer provinceId, Integer cityId, int currentPage,int pageSize) {
        PageHelper.PageQuery pageQuery = PageHelper.getPageQuery(currentPage, pageSize);
        List<Scenic> scenics = scenicDao.listOrderByScore(provinceId, cityId, pageQuery.getStart(), pageQuery.getLimit());
        List<ScenicPreviewVo> scenicPreviewVos = new ArrayList<>(scenics.size());
        for (Scenic s : scenics) {
            scenicPreviewVos.add(ScenicPreviewVo.fromScenic(s));
        }
        int count = scenicDao.countByCondition(provinceId, cityId);
        PageHelper.PageResult<ScenicPreviewVo> pageResult = PageHelper.getPageResult(scenicPreviewVos, count, 8);
        return pageResult;
    }

    public PageHelper.PageResult<ScenicRankVo> listHotScenicRank(String keyWord, Integer provinceId, Integer cityId, int currentPage, int pageSize) {


        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.matchAllQuery());
        if (provinceId != null) {
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("provinceId", provinceId));
        }
        if (cityId != null) {
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("cityId", cityId));
        }

        if (!StringUtils.isEmpty(keyWord)) {
            logger.info(keyWord);
            boolQueryBuilder = boolQueryBuilder.should(QueryBuilders.matchQuery("name", keyWord)).should(QueryBuilders.matchQuery("description", keyWord));

        }
        //评分越高，搜索得分越高
        ScoreFunctionBuilder<?> scoreFunctionBuilder = ScoreFunctionBuilders.fieldValueFactorFunction("avgScore").modifier(FieldValueFactorFunction.Modifier.LN1P).factor(10f);
        FunctionScoreQueryBuilder functionScoreQueryBuilder=QueryBuilders.functionScoreQuery(boolQueryBuilder,scoreFunctionBuilder).boostMode(CombineFunction.SUM);

//        //设置高亮显示
//        HighlightBuilder highlightBuilder = new HighlightBuilder().field("name").field("description");
//        highlightBuilder.preTags("<h4>");
//        highlightBuilder.postTags("</h4>");
        // 分页参数，es分页从0开始
        Pageable pageable = new PageRequest(currentPage - 1, pageSize);
        SortBuilder sortBuilder = SortBuilders.fieldSort("avgScore").order(SortOrder.DESC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(functionScoreQueryBuilder).withSort(SortBuilders.scoreSort().order(SortOrder.DESC)).withSort(sortBuilder).build();
        Page<EsScenic> searchPageResults = esScenicRepository.search(searchQuery);
        List<EsScenic> list = searchPageResults.getContent();
        List<ScenicRankVo> scenicRankVos = new ArrayList<>(list.size());
        for (EsScenic esScenic : list) {
            ScenicIntro scenicIntro = new ScenicIntro();
            scenicIntro.setDescription(esScenic.getDescription());
            Scenic scenic = scenicDao.findById(esScenic.getId());
            ScenicRankVo scenicRankVo = ScenicRankVo.fromScenic(scenic, scenicIntro);
            scenicRankVos.add(scenicRankVo);
        }
        int count = (int) searchPageResults.getTotalElements();
        PageHelper.PageResult<ScenicRankVo> result = PageHelper.getPageResult(scenicRankVos, count, currentPage);
        return result;
    }
    public ScenicDetailVo getScenicDetail(long scenicId) {
        Scenic scenic = scenicDao.findById(scenicId);
        ScenicIntro scenicIntro = scenicIntroDao.findByScenicId(scenicId);
        List<ScenicImg> scenicImgs = scenicImgDao.findByScenicId(scenicId);
        ScenicDetailVo scenicDetailVo = ScenicDetailVo.fromScenicAndScenicInfoAndScenicImgs(scenic, scenicIntro, scenicImgs);
        return scenicDetailVo;
    }
}
