package com.dssc.pcube3.entityservice.dao;

import org.springframework.data.neo4j.repository.query.Query;

public interface Neo4jBaseDao {

    @Query("MATCH P=()-->() RETURN COUNT(*)  AS COUNT")
    Integer getLinksCount();

    @Query("MATCH (n) RETURN count(*) as COUNT")
    Integer getNodesCount();
}
