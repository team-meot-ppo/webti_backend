package org.meotppo.webti.service.file;

import java.io.IOException;
import java.util.List;

import org.meotppo.webti.common.FileStore;
import org.meotppo.webti.domain.entity.jpa.file.Image;
import org.meotppo.webti.domain.repository.jpa.file.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {
    private final FileStore fileStore;
    private final ImageRepository imageRepository;

    @Transactional
    public List<Image> storeFiles(List<MultipartFile> multipartFiles) throws IOException { // 여러 개 이미지 업로드 서비스 로직
        List<Image> storedFiles = fileStore.storeFiles(multipartFiles);
        imageRepository.saveAll(storedFiles);
        return storedFiles;
    }

    @Transactional
    public Image storeFile(MultipartFile multipartFile) throws IOException { // 단일 이미지 업로드 서비스 로직
        Image storedFile = fileStore.storeFile(multipartFile);
        if (storedFile != null) {
            imageRepository.save(storedFile);
        }
        return storedFile;
    }

    @Transactional
    public Image storeImage(String url) { // 업로드 된 단일 이미지 저장 서비스 로직
        Image storedFile = fileStore.storeImage(url);
        if (storedFile != null) {
            imageRepository.save(storedFile);
        }
        return storedFile;
    }
}
