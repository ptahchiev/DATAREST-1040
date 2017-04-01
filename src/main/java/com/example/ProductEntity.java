package com.example;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Entity
public class ProductEntity {

    @Id
    private Long id;

    @NotNull
    @Column(name = "approval_status")
    @Enumerated(EnumType.STRING)
    private ProductApprovalStatus approvalStatus;

    @ElementCollection(targetClass = LocalizedValue.class)
    @CollectionTable(name = ("product_title_lv"), joinColumns = { @JoinColumn(name = ("product_id")) }, indexes = {
                    @Index(name = ("idx_product_title_lv"), columnList = ("product_id")) }, foreignKey = @ForeignKey(name = (("fk_product_title_lv"))))
    @MapKeyColumn(name = "locale")
    private Map<Locale, LocalizedValue> title = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ProductApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Map<Locale, LocalizedValue> getTitle() {
        return title;
    }

    public void setTitle(Map<Locale, LocalizedValue> title) {
        this.title = title;
    }
}
