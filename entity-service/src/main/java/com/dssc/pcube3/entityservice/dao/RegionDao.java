package com.dssc.pcube3.entityservice.dao;

import com.dssc.pcube3.entityservice.entity.Region;
import com.dssc.pcube3.entityservice.wrapper.LinkDTO;
import com.dssc.pcube3.entityservice.wrapper.NodeDTO;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionDao extends Neo4jRepository<Region, Long>, Neo4jBaseDao {
    @Query("MATCH (n:REG)-[r:OpenRel]-(m) where (n.eid={eid}) return n,m.name as nodeName,m.summary as des")
    List<NodeDTO> getNodesList(String eid);

    @Query("MATCH (n:REG) where (n.eid={eid}) return n,n.name as nodeName,n.summary as des LIMIT 1")
    NodeDTO getTargetNode(String eid);

    @Query("MATCH (n:REG)-[r:OpenRel]-(m) where (n.eid={eid}) return n," +
            "n.name as source,r.name as value,m.name as target")
    List<LinkDTO> getLinksList(String eid);

    @Query("MATCH (n:REG)-[r:OpenRel]-(m) RETURN COUNT(*)  AS COUNT")
    Integer getLinksCount();

    @Query("MATCH (n:REG) RETURN count(*) as COUNT")
    Integer getNodesCount();
}
