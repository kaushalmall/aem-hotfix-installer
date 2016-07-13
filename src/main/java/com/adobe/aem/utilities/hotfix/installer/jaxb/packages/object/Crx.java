
package com.adobe.aem.utilities.hotfix.installer.jaxb.packages.object;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="param">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="response">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="data">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="packages">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="package" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="downloadName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                                                 &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="lastModified" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="lastUnpacked" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="lastUnpackedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="status">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="user" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="workspace" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request",
    "response"
})
@XmlRootElement(name = "crx")
public class Crx {

    @XmlElement(required = true)
    protected Crx.Request request;
    @XmlElement(required = true)
    protected Crx.Response response;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "user")
    protected String user;
    @XmlAttribute(name = "workspace")
    protected String workspace;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link Crx.Request }
     *     
     */
    public Crx.Request getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link Crx.Request }
     *     
     */
    public void setRequest(Crx.Request value) {
        this.request = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link Crx.Response }
     *     
     */
    public Crx.Response getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link Crx.Response }
     *     
     */
    public void setResponse(Crx.Response value) {
        this.response = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the workspace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkspace() {
        return workspace;
    }

    /**
     * Sets the value of the workspace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkspace(String value) {
        this.workspace = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="param">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "param"
    })
    public static class Request {

        @XmlElement(required = true)
        protected Crx.Request.Param param;

        /**
         * Gets the value of the param property.
         * 
         * @return
         *     possible object is
         *     {@link Crx.Request.Param }
         *     
         */
        public Crx.Request.Param getParam() {
            return param;
        }

        /**
         * Sets the value of the param property.
         * 
         * @param value
         *     allowed object is
         *     {@link Crx.Request.Param }
         *     
         */
        public void setParam(Crx.Request.Param value) {
            this.param = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Param {

            @XmlAttribute(name = "name")
            protected String name;
            @XmlAttribute(name = "value")
            protected String value;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="data">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="packages">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="package" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="downloadName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                                       &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="lastModified" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="lastUnpacked" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="lastUnpackedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="status">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}int" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "data",
        "status"
    })
    public static class Response {

        @XmlElement(required = true)
        protected Crx.Response.Data data;
        @XmlElement(required = true)
        protected Crx.Response.Status status;

        /**
         * Gets the value of the data property.
         * 
         * @return
         *     possible object is
         *     {@link Crx.Response.Data }
         *     
         */
        public Crx.Response.Data getData() {
            return data;
        }

        /**
         * Sets the value of the data property.
         * 
         * @param value
         *     allowed object is
         *     {@link Crx.Response.Data }
         *     
         */
        public void setData(Crx.Response.Data value) {
            this.data = value;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link Crx.Response.Status }
         *     
         */
        public Crx.Response.Status getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link Crx.Response.Status }
         *     
         */
        public void setStatus(Crx.Response.Status value) {
            this.status = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="packages">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="package" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="downloadName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *                             &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="lastModified" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="lastUnpacked" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="lastUnpackedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "packages"
        })
        public static class Data {

            @XmlElement(required = true)
            protected Crx.Response.Data.Packages packages;

            /**
             * Gets the value of the packages property.
             * 
             * @return
             *     possible object is
             *     {@link Crx.Response.Data.Packages }
             *     
             */
            public Crx.Response.Data.Packages getPackages() {
                return packages;
            }

            /**
             * Sets the value of the packages property.
             * 
             * @param value
             *     allowed object is
             *     {@link Crx.Response.Data.Packages }
             *     
             */
            public void setPackages(Crx.Response.Data.Packages value) {
                this.packages = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="package" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="downloadName" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
             *                   &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="lastModified" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="lastUnpacked" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="lastUnpackedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "_package"
            })
            public static class Packages {

                @XmlElement(name = "package", required = true)
                protected List<Crx.Response.Data.Packages.Package> _package;

                /**
                 * Gets the value of the package property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the package property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getPackage().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Crx.Response.Data.Packages.Package }
                 * 
                 * 
                 */
                public List<Crx.Response.Data.Packages.Package> getPackage() {
                    if (_package == null) {
                        _package = new ArrayList<Crx.Response.Data.Packages.Package>();
                    }
                    return this._package;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="downloadName" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
                 *         &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="lastModified" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="lastUnpacked" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="lastUnpackedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "group",
                    "name",
                    "version",
                    "downloadName",
                    "size",
                    "created",
                    "createdBy",
                    "lastModified",
                    "lastModifiedBy",
                    "lastUnpacked",
                    "lastUnpackedBy"
                })
                public static class Package {

                    @XmlElement(required = true)
                    protected String group;
                    @XmlElement(required = true)
                    protected String name;
                    @XmlElement(required = true)
                    protected String version;
                    @XmlElement(required = true)
                    protected String downloadName;
                    protected int size;
                    @XmlElement(required = true)
                    protected String created;
                    @XmlElement(required = true)
                    protected String createdBy;
                    @XmlElement(required = true)
                    protected String lastModified;
                    @XmlElement(required = true)
                    protected String lastModifiedBy;
                    @XmlElement(required = true)
                    protected String lastUnpacked;
                    @XmlElement(required = true)
                    protected String lastUnpackedBy;

                    /**
                     * Gets the value of the group property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getGroup() {
                        return group;
                    }

                    /**
                     * Sets the value of the group property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setGroup(String value) {
                        this.group = value;
                    }

                    /**
                     * Gets the value of the name property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getName() {
                        return name;
                    }

                    /**
                     * Sets the value of the name property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setName(String value) {
                        this.name = value;
                    }

                    /**
                     * Gets the value of the version property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getVersion() {
                        return version;
                    }

                    /**
                     * Sets the value of the version property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setVersion(String value) {
                        this.version = value;
                    }

                    /**
                     * Gets the value of the downloadName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDownloadName() {
                        return downloadName;
                    }

                    /**
                     * Sets the value of the downloadName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDownloadName(String value) {
                        this.downloadName = value;
                    }

                    /**
                     * Gets the value of the size property.
                     * 
                     */
                    public int getSize() {
                        return size;
                    }

                    /**
                     * Sets the value of the size property.
                     * 
                     */
                    public void setSize(int value) {
                        this.size = value;
                    }

                    /**
                     * Gets the value of the created property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCreated() {
                        return created;
                    }

                    /**
                     * Sets the value of the created property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCreated(String value) {
                        this.created = value;
                    }

                    /**
                     * Gets the value of the createdBy property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCreatedBy() {
                        return createdBy;
                    }

                    /**
                     * Sets the value of the createdBy property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCreatedBy(String value) {
                        this.createdBy = value;
                    }

                    /**
                     * Gets the value of the lastModified property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLastModified() {
                        return lastModified;
                    }

                    /**
                     * Sets the value of the lastModified property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLastModified(String value) {
                        this.lastModified = value;
                    }

                    /**
                     * Gets the value of the lastModifiedBy property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLastModifiedBy() {
                        return lastModifiedBy;
                    }

                    /**
                     * Sets the value of the lastModifiedBy property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLastModifiedBy(String value) {
                        this.lastModifiedBy = value;
                    }

                    /**
                     * Gets the value of the lastUnpacked property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLastUnpacked() {
                        return lastUnpacked;
                    }

                    /**
                     * Sets the value of the lastUnpacked property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLastUnpacked(String value) {
                        this.lastUnpacked = value;
                    }

                    /**
                     * Gets the value of the lastUnpackedBy property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLastUnpackedBy() {
                        return lastUnpackedBy;
                    }

                    /**
                     * Sets the value of the lastUnpackedBy property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLastUnpackedBy(String value) {
                        this.lastUnpackedBy = value;
                    }

                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}int" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Status {

            @XmlAttribute(name = "code")
            protected Integer code;

            /**
             * Gets the value of the code property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getCode() {
                return code;
            }

            /**
             * Sets the value of the code property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setCode(Integer value) {
                this.code = value;
            }

        }

    }

}
