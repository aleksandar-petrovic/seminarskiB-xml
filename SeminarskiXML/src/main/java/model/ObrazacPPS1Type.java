//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.18 at 09:15:18 PM CEST 
//


package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObrazacPPS1Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObrazacPPS1Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OsnovneInformacije" type="{}OsnovneInformacijeType"/>
 *         &lt;element name="Deo1" type="{}Deo1Type"/>
 *         &lt;element name="Deo2OpisFizickiPrenosivihSredstavaPlacanja" type="{}Deo2OpisFizickiPrenosivihSredstavaPlacanjaType" minOccurs="0"/>
 *         &lt;element name="Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja" type="{}Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType" minOccurs="0"/>
 *         &lt;element name="Deo4PodaciOPrevozu" type="{}Deo4PodaciOPrevozuType" minOccurs="0"/>
 *         &lt;element name="Zakljucak" type="{}ZakljucakType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObrazacPPS1Type", propOrder = {
    "osnovneInformacije",
    "deo1",
    "deo2OpisFizickiPrenosivihSredstavaPlacanja",
    "deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja",
    "deo4PodaciOPrevozu",
    "zakljucak"
})
@XmlSeeAlso({
    ObrazacPPS1 .class
})
public class ObrazacPPS1Type {

    @XmlElement(name = "OsnovneInformacije", required = true)
    protected OsnovneInformacijeType osnovneInformacije;
    @XmlElement(name = "Deo1", required = true)
    protected Deo1Type deo1;
    @XmlElement(name = "Deo2OpisFizickiPrenosivihSredstavaPlacanja")
    protected Deo2OpisFizickiPrenosivihSredstavaPlacanjaType deo2OpisFizickiPrenosivihSredstavaPlacanja;
    @XmlElement(name = "Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja")
    protected Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja;
    @XmlElement(name = "Deo4PodaciOPrevozu")
    protected Deo4PodaciOPrevozuType deo4PodaciOPrevozu;
    @XmlElement(name = "Zakljucak")
    protected ZakljucakType zakljucak;

    /**
     * Gets the value of the osnovneInformacije property.
     * 
     * @return
     *     possible object is
     *     {@link OsnovneInformacijeType }
     *     
     */
    public OsnovneInformacijeType getOsnovneInformacije() {
        return osnovneInformacije;
    }

    /**
     * Sets the value of the osnovneInformacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link OsnovneInformacijeType }
     *     
     */
    public void setOsnovneInformacije(OsnovneInformacijeType value) {
        this.osnovneInformacije = value;
    }

    /**
     * Gets the value of the deo1 property.
     * 
     * @return
     *     possible object is
     *     {@link Deo1Type }
     *     
     */
    public Deo1Type getDeo1() {
        return deo1;
    }

    /**
     * Sets the value of the deo1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Deo1Type }
     *     
     */
    public void setDeo1(Deo1Type value) {
        this.deo1 = value;
    }

    /**
     * Gets the value of the deo2OpisFizickiPrenosivihSredstavaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link Deo2OpisFizickiPrenosivihSredstavaPlacanjaType }
     *     
     */
    public Deo2OpisFizickiPrenosivihSredstavaPlacanjaType getDeo2OpisFizickiPrenosivihSredstavaPlacanja() {
        return deo2OpisFizickiPrenosivihSredstavaPlacanja;
    }

    /**
     * Sets the value of the deo2OpisFizickiPrenosivihSredstavaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Deo2OpisFizickiPrenosivihSredstavaPlacanjaType }
     *     
     */
    public void setDeo2OpisFizickiPrenosivihSredstavaPlacanja(Deo2OpisFizickiPrenosivihSredstavaPlacanjaType value) {
        this.deo2OpisFizickiPrenosivihSredstavaPlacanja = value;
    }

    /**
     * Gets the value of the deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType }
     *     
     */
    public Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType getDeo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja() {
        return deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja;
    }

    /**
     * Sets the value of the deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType }
     *     
     */
    public void setDeo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja(Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType value) {
        this.deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja = value;
    }

    /**
     * Gets the value of the deo4PodaciOPrevozu property.
     * 
     * @return
     *     possible object is
     *     {@link Deo4PodaciOPrevozuType }
     *     
     */
    public Deo4PodaciOPrevozuType getDeo4PodaciOPrevozu() {
        return deo4PodaciOPrevozu;
    }

    /**
     * Sets the value of the deo4PodaciOPrevozu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Deo4PodaciOPrevozuType }
     *     
     */
    public void setDeo4PodaciOPrevozu(Deo4PodaciOPrevozuType value) {
        this.deo4PodaciOPrevozu = value;
    }

    /**
     * Gets the value of the zakljucak property.
     * 
     * @return
     *     possible object is
     *     {@link ZakljucakType }
     *     
     */
    public ZakljucakType getZakljucak() {
        return zakljucak;
    }

    /**
     * Sets the value of the zakljucak property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZakljucakType }
     *     
     */
    public void setZakljucak(ZakljucakType value) {
        this.zakljucak = value;
    }

}
