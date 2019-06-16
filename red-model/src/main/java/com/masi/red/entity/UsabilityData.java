package com.masi.red.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "USABILITY_DATA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsabilityData {

    @Id
    @GeneratedValue(generator = "optimized-sequence")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "IP", nullable = false)
    private String ip;

    @Column(name = "BROWSER", nullable = false)
    private String browser;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "M_ID", nullable = false)
    private int measurementNumber;

    @Column(name = "SAVETIME", nullable = false)
    private OffsetDateTime creationTime;

    @Column(name = "RES_W", nullable = false)
    private int resolutionWidth;

    @Column(name = "RES_H", nullable = false)
    private int resolutionHeight;

    @Column(name = "MC", nullable = false)
    private int mouseClicksNumber;

    @Column(name = "TIME", nullable = false)
    private Duration timeElapsed;

    @Column(name = "DIST", nullable = false)
    private double distanceTravelled;

    @Column(name = "FAIL", nullable = false)
    private int failed = 0;

    @Column(name = "ERROR")
    private int errorType;

    @PrePersist
    private void initializeCreationTime() {
        creationTime = OffsetDateTime.now();
    }
}
