package nl.dennisvdwielen.pojo;

/**
 * Created by Dennis on 14-5-2014 at 21:34)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.pojo
 */

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Methods are dynamically called. There for they are not used directly. That's why there is a warning suppressor in place.
 */
@SuppressWarnings("unused")
@Table(tableName = "Container", alias = "c")
public class Container {

    @PrimaryKey(fieldName = "equipmentNumber")
    private String equipmentNumber;

    @ForeignKey(tableName = "Ship", fieldName = "shipID")
    private Ship shipID;

    @ForeignKey(tableName = "Handling", fieldName = "handlingID")
    private Handling handlingID;

    @ForeignKey(tableName = "Packaginggroup", fieldName = "packagingID")
    private Packaginggroup packagingID;

    private Integer consignmentNumber;
    private Integer uno;
    private Double imo;
    private Double flashpoint;
    private Integer stowagePosition;
    private Integer quantityInContainer;
    private Integer weight;
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

    public Integer getConsignmentNumber() {
        return consignmentNumber;
    }

    public void setConsignmentNumber(Integer consignmentNumber) {
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
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
