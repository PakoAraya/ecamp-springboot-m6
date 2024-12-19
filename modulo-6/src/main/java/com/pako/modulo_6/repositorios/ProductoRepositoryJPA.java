package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Con la implementacion de esta clase JPA, no seria necesario seguir usando ProductoRepository.
//Se recomienda a futuro eliminar esa clase.

public interface ProductoRepositoryJPA extends JpaRepository<Producto, Integer> {

  //Ejemplos de metodos de query desarrollado por nosotros
  List<Producto> findByNombre(String nombre);

  List<Producto> findByPrecioGreaterThan(double precio);

  List<Producto> findByNombreAndPrecio(String nombre, Double precio);

  List<Producto> findAllOrderByPrecioDesc();


  //Tambien podemos trabajar con SQL normal
  @Query("SELECT p FROM Producto p WHERE p.nombre=:nombre")
  List<Producto> buscarByNombre(@Param("nombre") String nombre);

  //Existe otra forma de Query Nativo
  @Query(value = "SELECT p FROM producto WHERE p.nombre =:nombre", nativeQuery = true)
  List<Producto> buscarByNombreNativo(@Param("nombre") String nombre);
}


