package com.dssc.pcube3.entityservice.dao;

import com.dssc.pcube3.entityservice.entity.Person;
import com.dssc.pcube3.entityservice.wrapper.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SixSearchDao extends Neo4jRepository<Person, Long> {


    @Query("MATCH (s:PER)-[r1]->(n1:PER) where (s.eid={startEid} and n1.eid={endEid}) return n1," +
            "s.name as startNodeName,s.summary as startNodeDes," +
            "r1.name as firstLinkName," +
            "n1.name as firstNodeName,n1.summary as firstNodeDes LIMIT 2")
    List<SearchDTO> getFirstRelations(String startEid, String endEid);


    @Query("MATCH (s:PER)-[r1]->(n1:PER)-[r2]->(n2:PER) where (s.eid={startEid} and n2.eid={endEid}) return n1," +
            "s.name as startNodeName,s.summary as startNodeDes," +
            "r1.name as firstLinkName," +
            "n1.name as firstNodeName,n1.summary as firstNodeDes," +
            "r2.name as secondLinkName," +
            "n2.name as secondNodeName,n2.summary as secondNodeDes LIMIT 2")
    List<SearchDTO> getSecondRelations(String startEid, String endEid);

    @Query("MATCH (s:PER)-[r1]->(n1:PER)-[r2]->(n2:PER)-[r3]->(n3:PER)" +
            "where (s.eid={startEid} and n3.eid={endEid}) return n1," +
            "s.name as startNodeName,s.summary as startNodeDes," +
            "r1.name as firstLinkName," +
            "n1.name as firstNodeName,n1.summary as firstNodeDes," +
            "r2.name as secondLinkName," +
            "n2.name as secondNodeName,n2.summary as secondNodeDes," +
            "r3.name as thirdLinkName," +
            "n3.name as thirdNodeName,n3.summary as thirdNodeDes LIMIT 2")
    List<SearchDTO> getThirdRelations(String startEid, String endEid);

    @Query("MATCH (s:PER)-[r1]->(n1:PER)-[r2]->(n2:PER)-[r3]->(n3:PER)-[r4]->(n4:PER)" +
            "where (s.eid={startEid} and n4.eid={endEid}) return n1," +
            "s.name as startNodeName,s.summary as startNodeDes," +
            "r1.name as firstLinkName," +
            "n1.name as firstNodeName,n1.summary as firstNodeDes," +
            "r2.name as secondLinkName," +
            "n2.name as secondNodeName,n2.summary as secondNodeDes," +
            "r3.name as thirdLinkName," +
            "n3.name as thirdNodeName,n3.summary as thirdNodeDes," +
            "r4.name as fourthLinkName," +
            "n4.name as fourthNodeName,n4.summary as fourthNodeDes LIMIT 2")
    List<SearchDTO> getFourthRelations(String startEid, String endEid);

    @Query("MATCH (s:PER)-[r1]->(n1:PER)-[r2]->(n2:PER)-[r3]->(n3:PER)-[r4]->(n4:PER)-[r5]->(n5:PER)" +
            "where (s.eid={startEid} and n5.eid={endEid}) return n1," +
            "s.name as startNodeName,s.summary as startNodeDes," +
            "r1.name as firstLinkName," +
            "n1.name as firstNodeName,n1.summary as firstNodeDes," +
            "r2.name as secondLinkName," +
            "n2.name as secondNodeName,n2.summary as secondNodeDes," +
            "r3.name as thirdLinkName," +
            "n3.name as thirdNodeName,n3.summary as thirdNodeDes," +
            "r4.name as fourthLinkName," +
            "n4.name as fourthNodeName,n4.summary as fourthNodeDes," +
            "r5.name as fifthLinkName," +
            "n5.name as fifthNodeName,n5.summary as fifthNodeDes LIMIT 2")
    List<SearchDTO> getFifthRelations(String startEid, String endEid);

}
