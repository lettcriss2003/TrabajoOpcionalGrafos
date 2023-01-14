/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author DEEPIN
 */
public class DetalleFactura {

    private Long id;
    private Factura factura;
    private Producto producto;
    private Integer cantidad;
    private Float pUnitario;
    private Float pTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getpUnitario() {
        return pUnitario;
    }

    public void setpUnitario(Float pUnitario) {
        this.pUnitario = pUnitario;
    }

    public Float getpTotal() {
        return pTotal;
    }

    public void setpTotal(Float pTotal) {
        this.pTotal = pTotal;
    }
    
    
}
