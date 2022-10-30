package com.graphql;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sess_session")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "session_generator", sequenceName = "ses_seq", allocationSize = 50)
    Integer id;
    String uuid;
    String modificationDate;
}
