package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Con la implementación de esta clase JPA, no sería necesario seguir usando ProductoRepository.
// Se recomienda a futuro eliminar esa clase.

public interface ProductoRepositoryJPA extends JpaRepository<Producto, Integer> {

  // Ejemplos de métodos de query desarrollado por nosotros
  // Las consultas generadas automáticamente por Spring Data JPA, como findByNombre y similares, son muy útiles
  List<Producto> findByNombre(String nombre);

  // Buscar productos cuyo precio sea mayor que el valor proporcionado
  List<Producto> findByPrecioGreaterThan(double precio);

  // Buscar productos por nombre y precio
  List<Producto> findByNombreAndPrecio(String nombre, Double precio);

  // Obtener todos los productos ordenados por precio de forma descendente
  List<Producto> findAllByOrderByPrecioDesc();


  // También podemos trabajar con SQL normal
  // Usamos JPQL para hacer la consulta basada en el nombre de la entidad y sus atributos
  @Query("SELECT p FROM Producto p WHERE p.nombre=:nombre")
  List<Producto> buscarByNombre(@Param("nombre") String nombre);

  // Existe otra forma de Query Nativo
  // En este caso, usamos SQL nativo, y la tabla debe llamarse 'producto' (en minúsculas) si sigue la convención de nombres en la base de datos
  @Query(value = "SELECT p FROM producto p WHERE p.nombre =:nombre", nativeQuery = true)
  List<Producto> buscarByNombreNativo(@Param("nombre") String nombre);
}
