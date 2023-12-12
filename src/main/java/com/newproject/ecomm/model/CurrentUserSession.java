package com.newproject.ecomm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class CurrentUserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private Integer userId;

    private String uniqueId;

    private LocalDateTime time;

    public CurrentUserSession(Integer userId, String uniqueId, LocalDateTime time) {
        this.userId = userId;
        this.uniqueId = uniqueId;
        this.time = time;
    }
}
