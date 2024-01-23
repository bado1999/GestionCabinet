package com.gestioncabinet.metier;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
public class ImageService {
    private final GridFsTemplate gridFsTemplate;
    public String saveImage(String name, MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String fileId = String.valueOf(gridFsTemplate.store(inputStream, name));
        inputStream.close();
        return fileId;
    }

    public GridFsResource getImage(String fileId) {
        GridFSFile file=gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
        if (file==null)return null;
        return gridFsTemplate.getResource(file);
    }
}
