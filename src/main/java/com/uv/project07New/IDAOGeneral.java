/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.project07New;

import java.util.List;

/**
 *
 * @author btoarriola
 */
// t el tipo de dato del pojo y ID el tipo de dato del id
public interface IDAOGeneral <T, ID> {
    public T create(T p);
    public boolean delete(ID id);
    public T update(T p, ID id);
    
    public List<T> findAll();
    public T FindById(ID id);
    
}
