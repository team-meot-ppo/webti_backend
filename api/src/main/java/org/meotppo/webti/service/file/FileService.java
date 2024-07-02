package org.meotppo.webti.service.file;

import org.meotppo.webti.domain.entity.jpa.file.Image;
import org.meotppo.webti.domain.repository.jpa.file.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {
    private final ImageRepository imageRepository;

    @Transactional
    public Image storeImage(String url) { // 업로드 된 단일 이미지 저장 서비스 로직
        if (url.isEmpty()) {
            return null;
        }

        String originalurl = url;
        String originalFilename = url.substring(url.lastIndexOf("/") + 1); // url에서 파일명 추출
        String storeFileName = createStoreFileName(originalFilename); // 파일명 생성
        
        Image storedFile = Image.builder()
                .originalFileName(originalFilename)
                .storeFileName(storeFileName)
                .url(originalurl)
                .build();

        if (storedFile != null) {
            imageRepository.save(storedFile);
        }
        return storedFile;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
