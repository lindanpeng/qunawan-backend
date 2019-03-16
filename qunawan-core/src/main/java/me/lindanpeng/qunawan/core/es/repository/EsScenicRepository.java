package me.lindanpeng.qunawan.core.es.repository;

import me.lindanpeng.qunawan.core.es.model.EsScenic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsScenicRepository extends ElasticsearchRepository<EsScenic,Long> {

}
