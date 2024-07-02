package org.meotppo.webti.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.meotppo.webti.domain.entity.jpa.file.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class FileStore {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 amazonS3;

    public List<Image> storeFiles(List<MultipartFile> multipartFiles) throws IOException { // 여러 개 이미지 업로드
        List<Image> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public Image storeFile(MultipartFile multipartFile) throws IOException { // 단일 이미지 업로드
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);

        return Image.builder()
                .originalFileName(originalFilename)
                .storeFileName(storeFileName)
                .build();
    }

    public Image storeImage(String url) { // 업로드된 이미지 db에 저장
        if (url.isEmpty()) {
            return null;
        }

        String originalurl = url;
        String originalFilename = url.substring(url.lastIndexOf("/") + 1); // url에서 파일명 추출
        String storeFileName = createStoreFileName(originalFilename); // 파일명 생성

        return Image.builder()
                .originalFileName(originalFilename)
                .storeFileName(storeFileName)
                .url(originalurl)
                .build();
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
