/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Order {

    private int orderId;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSqFoot;
    private BigDecimal laborCostPersqFt;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal orderTotal;
    private LocalDate orderDate;
    

    //creating a loosely coupled relationship with the other two objects
//    private Product productObj;
//    private TaxData taxObj;
    public Order(String customerName, String state, BigDecimal taxRate, String productType, BigDecimal area, BigDecimal costPerSqFoot, BigDecimal laborCostPersqFt, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal orderTotal) {
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSqFoot = costPerSqFoot;
        this.laborCostPersqFt = laborCostPersqFt;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
    }

    public Order(int orderId, LocalDate orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public Order(String date) {
        this.orderDate = orderDate;
    }

    public Order(BigDecimal costPerSqFoot, BigDecimal laborCostPersqFt, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal orderTotal) {
        this.costPerSqFoot = costPerSqFoot;
        this.laborCostPersqFt = laborCostPersqFt;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.orderTotal = orderTotal;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public Order() {

    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqFoot() {
        return costPerSqFoot;
    }

    public void setCostPerSqFoot(BigDecimal costPerSqFoot) {
        this.costPerSqFoot = costPerSqFoot;
    }

    public BigDecimal getLaborCostPersqFt() {
        return laborCostPersqFt;
    }

    public void setLaborCostPersqFt(BigDecimal laborCostPersqFt) {
        this.laborCostPersqFt = laborCostPersqFt;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.orderId;
        hash = 13 * hash + Objects.hashCode(this.customerName);
        hash = 13 * hash + Objects.hashCode(this.state);
        hash = 13 * hash + Objects.hashCode(this.taxRate);
        hash = 13 * hash + Objects.hashCode(this.productType);
        hash = 13 * hash + Objects.hashCode(this.area);
        hash = 13 * hash + Objects.hashCode(this.costPerSqFoot);
        hash = 13 * hash + Objects.hashCode(this.laborCostPersqFt);
        hash = 13 * hash + Objects.hashCode(this.materialCost);
        hash = 13 * hash + Objects.hashCode(this.laborCost);
        hash = 13 * hash + Objects.hashCode(this.tax);
        hash = 13 * hash + Objects.hashCode(this.orderTotal);
        hash = 13 * hash + Objects.hashCode(this.orderDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFoot, other.costPerSqFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPersqFt, other.laborCostPersqFt)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.orderTotal, other.orderTotal)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        return true;
    }

}
