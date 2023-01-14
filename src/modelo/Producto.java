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
public class Producto {
    private Integer Id;
    private String nombre;
    private Marca marca;
    private Integer existencia;
    private Float pCompra;
    private Float pVenta;
    private String descripcion;
    private String estado;
    private Float descuento;
    private Boolean tieneIva;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Float getpCompra() {
        return pCompra;
    }

    public void setpCompra(Float pCompra) {
        this.pCompra = pCompra;
    }

    public Float getpVenta() {
        return pVenta;
    }

    public void setpVenta(Float pVenta) {
        this.pVenta = pVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Boolean getTieneIva() {
        return tieneIva;
    }

    public void setTieneIva(Boolean tieneIva) {
        this.tieneIva = tieneIva;
    }
    
    
}
