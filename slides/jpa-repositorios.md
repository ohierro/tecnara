##  JPA: repositorios

```java
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Phone findByPhoneNumber(String phoneNumber);
}
```
* Constituyen el punto de 'enganche' con la BD.
* Nos proporcionan un CRUD sobre la entidad indicada
* Nos permiten definir nuevos métodos de búsqueda sin realizar implementación
* Nos permiten incluir queries personalizadas
