/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import controlador.utiles.Utilidades;
import modelo.Cuenta;
import modelo.enums.TipoIdentificacion;

/**
 *
 * @author DEEPIN
 */
public class CuentaDao extends AdaptadorDao<Cuenta> {

    private Cuenta cuenta;
    private String clave = "XABC345";

    public CuentaDao() {
        super(Cuenta.class);
    }

    public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta();
        }
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public boolean guardar() throws Exception {
        this.cuenta.setId(generarId());
        this.cuenta.setClave(Utilidades.encriptarClave(this.cuenta.getClave(), clave));
        guardar(this.cuenta);
        return true;
    }

    public boolean modificar(Integer pos) throws Exception {
        modificar(this.cuenta, pos);
        return true;
    }

    private Integer generarId() {
        return listar().getSize() + 1;
    }

    public void crearCuenta() {
        if (listar().getSize() == 0){
            try {
                PersonaDao pd = new PersonaDao();
                pd.getPersona().setApellido("Administrador");
                pd.getPersona().setNombre("Administrador");
                pd.getPersona().setCorreo("admin@imperium.com");
                pd.getPersona().setIndetifiacion("1111111111");
                pd.getPersona().setTipoIdentificacion(TipoIdentificacion.CEDULA);
                pd.getPersona().setId_rol(3);
                pd.guardar();
                this.getCuenta().setUsuario(pd.getPersona().getCorreo());
                this.getCuenta().setEstado(Boolean.TRUE);
                this.getCuenta().setId_persona(pd.getPersona().getId());
                this.getCuenta().setClave("admin1234");
                this.guardar();
                pd.setPersona(null);
                this.setCuenta(null);
                pd.getPersona().setApellido("Final");
                pd.getPersona().setNombre("Consumidor");
                pd.getPersona().setCorreo("consumidor@imperium.com");
                pd.getPersona().setIndetifiacion("9999999999");
                pd.getPersona().setDireccion("Planeta Tierra");
                pd.getPersona().setTipoIdentificacion(TipoIdentificacion.CEDULA);
                pd.getPersona().setId_rol(4);
                pd.guardar();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
