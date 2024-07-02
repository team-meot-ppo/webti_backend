package org.meotppo.webti.domain.entity.jpa.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "store_file_name")
    private String storeFileName;

    @Column(name = "url")
    private String url;

    @Builder
    public Image(String originalFileName, String storeFileName, String url) {
        this.originalFileName = originalFileName;
        this.storeFileName = storeFileName;
        this.url = url;
    }
}
