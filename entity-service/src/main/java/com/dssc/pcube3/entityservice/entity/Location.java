package com.dssc.pcube3.entityservice.entity;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Value
@AllArgsConstructor
@Node("LOC")
public class Location {
    @Id
    private Long id;
    @Property(name = "eid")
    private String eid;
    @Property(name = "name")
    private String name;
    @Property(name = "name_cht")
    private String nameCht;
    @Property(name = "party")
    private String party;
    @Property(name = "summary")
    private String summary;

}
