package com.graphql;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "appl_application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name="app_generator", sequenceName = "app_seq", allocationSize=50)
    Integer id;
    String applicationNumber;
    String status;
    String createDate; //yyyyMMdd
    Integer sessionId;

}
