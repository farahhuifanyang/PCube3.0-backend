package com.dssc.pcube3.entityservice.wrapper;

import lombok.Value;

@Value
public class SearchDTO {
    String startNodeName;
    String startNodeDes;
    String startNodeSymbol;

    String firstLinkName;
    String firstLinkDes;

    String firstNodeName;
    String firstNodeDes;
    String firstNodeSymbol;

    String secondLinkName;
    String secondLinkDes;

    String secondNodeName;
    String secondNodeDes;
    String secondNodeSymbol;

    String thirdLinkName;
    String thirdLinkDes;

    String thirdNodeName;
    String thirdNodeDes;
    String thirdNodeSymbol;

    String fourthLinkName;
    String fourthLinkDes;

    String fourthNodeName;
    String fourthNodeDes;
    String fourthNodeSymbol;

    String fifthLinkName;
    String fifthLinkDes;

    String fifthNodeName;
    String fifthNodeDes;
    String fifthNodeSymbol;

    LinkDTO firstLink = new LinkDTO();
    LinkDTO secondLink = new LinkDTO();
    LinkDTO thirdLink = new LinkDTO();
    LinkDTO fourthLink = new LinkDTO();
    LinkDTO fifthLink = new LinkDTO();

    NodeDTO startNode = new NodeDTO();
    NodeDTO firstNode = new NodeDTO();
    NodeDTO secondNode = new NodeDTO();
    NodeDTO thirdNode = new NodeDTO();
    NodeDTO fourthNode = new NodeDTO();
    NodeDTO fifthNode = new NodeDTO();

    public LinkDTO getFirstLink() {
        firstLink.setSource(startNodeName);
        firstLink.setValue(firstLinkName);
        firstLink.setTarget(firstNodeName);
        firstLink.setDes("");
        return firstLink;
    }

    public LinkDTO getSecondLink() {
        secondLink.setSource(firstNodeName);
        secondLink.setValue(secondLinkName);
        secondLink.setTarget(secondNodeName);
        secondLink.setDes("");
        return secondLink;
    }

    public LinkDTO getThirdLink() {
        thirdLink.setSource(secondNodeName);
        thirdLink.setValue(thirdLinkName);
        thirdLink.setTarget(thirdNodeName);
        thirdLink.setDes("");
        return thirdLink;
    }

    public LinkDTO getFourthLink() {
        fourthLink.setSource(thirdNodeName);
        fourthLink.setValue(fourthLinkName);
        fourthLink.setTarget(fourthNodeName);
        fourthLink.setDes("");
        return fourthLink;
    }

    public LinkDTO getFifthLink() {
        fifthLink.setSource(fourthNodeName);
        fifthLink.setValue(fifthLinkName);
        fifthLink.setTarget(fifthNodeName);
        fifthLink.setDes("");
        return fifthLink;
    }

    public NodeDTO getStartNode() {
        startNode.setNodeName(startNodeName);
        startNode.setDes(startNodeDes);
        startNode.setSymbol("");
        return startNode;
    }

    public NodeDTO getFirstNode() {
        firstNode.setNodeName(firstNodeName);
        firstNode.setDes(firstNodeDes);
        firstNode.setSymbol("");
        return firstNode;
    }

    public NodeDTO getSecondNode() {
        secondNode.setNodeName(secondNodeName);
        secondNode.setDes(secondNodeDes);
        secondNode.setSymbol("");
        return secondNode;
    }

    public NodeDTO getThirdNode() {
        thirdNode.setNodeName(thirdNodeName);
        thirdNode.setDes(thirdNodeDes);
        thirdNode.setSymbol("");
        return thirdNode;
    }

    public NodeDTO getFourthNode() {
        fourthNode.setNodeName(fourthNodeName);
        fourthNode.setDes(fourthNodeDes);
        fourthNode.setSymbol("");
        return fourthNode;
    }

    public NodeDTO getFifthNode() {
        fifthNode.setNodeName(fifthNodeName);
        fifthNode.setDes(fifthNodeDes);
        fifthNode.setSymbol("");
        return fifthNode;
    }
}


