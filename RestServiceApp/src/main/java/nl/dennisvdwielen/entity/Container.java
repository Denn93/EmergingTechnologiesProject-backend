package nl.dennisvdwielen.entity;

/**
 * Created by Dennis on 14-5-2014 at 21:34)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.entity
 */

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * This class is a custom entity which is used for reflection purposes and overall use with the database
 */
@SuppressWarnings("unused")
@Table(tableName = "container", alias = "c")
public class Container {

    @PrimaryKey(fieldName = "equipmentnumber")
    private String equipmentNumber;

    @ForeignKey(tableName = "ship", fieldName = "shipid")
    private Ship shipID;

    @ForeignKey(tableName = "handling", fieldName = "handlingid")
    private Handling handlingID;

    @ForeignKey(tableName = "packaginggroup", fieldName = "packagingid")
    private Packaginggroup packagingID;

    private Long consignmentNumber;
    private Integer uno;
    private Double imo;
    private Double flashpoint;
    private Integer stowagePosition;
    private Integer quantityInContainer;
    private Long weight;
    private String portOfDischarge;
    private String terminal;

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Ship getShip() {
        return shipID;
    }

    public void setShipID(Ship shipID) {
        this.shipID = shipID;
    }

    public Handling getHandling() {
        return handlingID;
    }

    public void setHandlingID(Handling handlingID) {
        this.handlingID = handlingID;
    }

    public Packaginggroup getPackaging() {
        return packagingID;
    }

    public void setPackagingID(Packaginggroup packagingID) {
        this.packagingID = packagingID;
    }

    public Long getConsignmentNumber() {
        return consignmentNumber;
    }

    public void setConsignmentNumber(Long consignmentNumber) {
        this.consignmentNumber = consignmentNumber;
    }

    public Integer getUno() {
        return uno;
    }

    public void setUno(Integer uno) {
        this.uno = uno;
    }

    public Double getImo() {
        return imo;
    }

    public void setImo(Double imo) {
        this.imo = imo;
    }

    public Double getFlashpoint() {
        return flashpoint;
    }

    public void setFlashpoint(Double flashpoint) {
        this.flashpoint = flashpoint;
    }

    public Integer getStowagePosition() {
        return stowagePosition;
    }

    public void setStowagePosition(Integer stowagePosition) {
        this.stowagePosition = stowagePosition;
    }

    public Integer getQuantityInContainer() {
        return quantityInContainer;
    }

    public void setQuantityInContainer(Integer quantityInContainer) {
        this.quantityInContainer = quantityInContainer;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
