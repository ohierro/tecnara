##  JPA: trabajando con la BD

```java
@Entity
public class Phone {
    @Id
    @Setter @Getter
    @GeneratedValue
    private Long id;

    @Column
    @Setter @Getter
    private String type;

    @Column
    @Setter @Getter
    private String phoneNumber;

    @Column
    @Setter @Getter
    private String areaCode;

    @Setter @Getter
    @ManyToOne
    private Employee owner;
}
```
