package Infnet.Assessment.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_entries", schema = "audit")
public class AuditEntry {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id")
    private Organizacao organizacao;

    private String action;
    
    @Column(name = "entity_schema")
    private String entitySchema;
    
    @Column(name = "entity_name")
    private String entityName;
    
    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "occurred_at")
    private OffsetDateTime occurredAt;

    @Column(columnDefinition = "inet")
    private String ip;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(columnDefinition = "jsonb")
    private String diff;

    @Column(columnDefinition = "jsonb")
    private String metadata;

    private Boolean success;

}