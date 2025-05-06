package com.connectJPA.repository.elasticsearch;

import com.connectJPA.entity.elasticsearch.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserSearchRepository extends ElasticsearchRepository<UserDocument, String> {
    List<UserDocument> findByUsernameContainingIgnoreCase(String username);
}
