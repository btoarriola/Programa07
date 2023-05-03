/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.project07New;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author btoarriola
 */
public class DAOProducto implements IDAOGeneral<Producto, Long>{

    @Override
    public Producto create(Producto p) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction t = session.beginTransaction();
            session.save(p);
            t.commit();
        }
        return p;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        Producto producto = FindById(id);
        if (producto == null) {
            t.rollback();   //revertir los cambios realizados en la trans y volver al anterior.
            session.close();
            return false;
        } else {
            session.delete(producto);
            t.commit();
            session.close();
            return true;
        }
    }

    @Override
    public Producto update(Producto p, Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t= session.beginTransaction();
        Producto producto = FindById(id);
        if(producto == null){
            t.rollback();   //revertir los cambios realizados en la trans y volver al anterior.
            session.close();
            return null;
        }else{
            producto.setNombre(p.getNombre());
            producto.setPrecio(p.getPrecio());

            session.update(producto);
            t.commit();
            session.close();
            return producto;
        }
    }

    @Override
    public List<Producto> findAll() {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        List<Producto> lPro=session.createQuery("from Producto",Producto.class).list();
        t.commit();
        session.close();
        return lPro;
    }

    @Override
    public Producto FindById(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        Producto p= session.get(Producto.class, id);
        t.commit();
        session.close();
        return p;
    }
    
}
