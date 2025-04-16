package com.hr.hrsystem.audit;

import com.hr.hrsystem.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class Auditable {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by", updatable = false)
    private String lastModifiedBy;

    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

}
