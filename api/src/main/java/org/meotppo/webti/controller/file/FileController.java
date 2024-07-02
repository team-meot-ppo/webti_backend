package org.meotppo.webti.controller.file;

import static org.meotppo.webti.response.ResponseUtil.createSuccessResponse;

import java.io.IOException;

import org.meotppo.webti.domain.entity.jpa.file.Image;
import org.meotppo.webti.response.ResponseBody;
import org.meotppo.webti.service.file.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController { // 업로드 테스트용
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseBody<Image>> uploadFile(@RequestBody MultipartFile file) throws IOException {
        Image storedImage = fileService.storeFile(file);
        return ResponseEntity.ok(createSuccessResponse(storedImage));
    }
}