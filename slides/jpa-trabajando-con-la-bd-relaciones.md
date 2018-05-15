##  JPA: trabajando con la BD (relaciones)

* @OneToOne: Relación 1 a 1 entre objectos. En una de las entidades se incluirá el PK de la otra

* @OneToMany: 
    * Relación 1 a N entre entidades. La entidad con la anotación tendrá una `List` de objectos de la entidad destino
    * Si no queremos que se genere una tabla intermedia, necesitaremos especificar el parámetro `mappedBy=owner`

