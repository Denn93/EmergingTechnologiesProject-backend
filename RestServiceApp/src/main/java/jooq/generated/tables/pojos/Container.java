/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Container implements java.io.Serializable {

	private static final long serialVersionUID = 294172378;

	private java.lang.String     equipmentnumber;
	private java.lang.Integer    shipid;
	private java.lang.Integer    handlingid;
	private java.lang.Integer    packagingid;
	private java.lang.Long       consignmentnumber;
	private java.lang.Integer    uno;
	private java.math.BigDecimal ino;
	private java.math.BigDecimal flashpoint;
	private java.lang.Integer    stowageposition;
	private java.lang.Integer    quantityincontainer;
	private java.lang.Integer    weight;
	private java.lang.String     portofdischarge;
	private java.lang.String     terminal;

	public Container() {}

	public Container(
		java.lang.String     equipmentnumber,
		java.lang.Integer    shipid,
		java.lang.Integer    handlingid,
		java.lang.Integer    packagingid,
		java.lang.Long       consignmentnumber,
		java.lang.Integer    uno,
		java.math.BigDecimal ino,
		java.math.BigDecimal flashpoint,
		java.lang.Integer    stowageposition,
		java.lang.Integer    quantityincontainer,
		java.lang.Integer    weight,
		java.lang.String     portofdischarge,
		java.lang.String     terminal
	) {
		this.equipmentnumber = equipmentnumber;
		this.shipid = shipid;
		this.handlingid = handlingid;
		this.packagingid = packagingid;
		this.consignmentnumber = consignmentnumber;
		this.uno = uno;
		this.ino = ino;
		this.flashpoint = flashpoint;
		this.stowageposition = stowageposition;
		this.quantityincontainer = quantityincontainer;
		this.weight = weight;
		this.portofdischarge = portofdischarge;
		this.terminal = terminal;
	}

	public java.lang.String getEquipmentnumber() {
		return this.equipmentnumber;
	}

	public void setEquipmentnumber(java.lang.String equipmentnumber) {
		this.equipmentnumber = equipmentnumber;
	}

	public java.lang.Integer getShipid() {
		return this.shipid;
	}

	public void setShipid(java.lang.Integer shipid) {
		this.shipid = shipid;
	}

	public java.lang.Integer getHandlingid() {
		return this.handlingid;
	}

	public void setHandlingid(java.lang.Integer handlingid) {
		this.handlingid = handlingid;
	}

	public java.lang.Integer getPackagingid() {
		return this.packagingid;
	}

	public void setPackagingid(java.lang.Integer packagingid) {
		this.packagingid = packagingid;
	}

	public java.lang.Long getConsignmentnumber() {
		return this.consignmentnumber;
	}

	public void setConsignmentnumber(java.lang.Long consignmentnumber) {
		this.consignmentnumber = consignmentnumber;
	}

	public java.lang.Integer getUno() {
		return this.uno;
	}

	public void setUno(java.lang.Integer uno) {
		this.uno = uno;
	}

	public java.math.BigDecimal getIno() {
		return this.ino;
	}

	public void setIno(java.math.BigDecimal ino) {
		this.ino = ino;
	}

	public java.math.BigDecimal getFlashpoint() {
		return this.flashpoint;
	}

	public void setFlashpoint(java.math.BigDecimal flashpoint) {
		this.flashpoint = flashpoint;
	}

	public java.lang.Integer getStowageposition() {
		return this.stowageposition;
	}

	public void setStowageposition(java.lang.Integer stowageposition) {
		this.stowageposition = stowageposition;
	}

	public java.lang.Integer getQuantityincontainer() {
		return this.quantityincontainer;
	}

	public void setQuantityincontainer(java.lang.Integer quantityincontainer) {
		this.quantityincontainer = quantityincontainer;
	}

	public java.lang.Integer getWeight() {
		return this.weight;
	}

	public void setWeight(java.lang.Integer weight) {
		this.weight = weight;
	}

	public java.lang.String getPortofdischarge() {
		return this.portofdischarge;
	}

	public void setPortofdischarge(java.lang.String portofdischarge) {
		this.portofdischarge = portofdischarge;
	}

	public java.lang.String getTerminal() {
		return this.terminal;
	}

	public void setTerminal(java.lang.String terminal) {
		this.terminal = terminal;
	}
}
