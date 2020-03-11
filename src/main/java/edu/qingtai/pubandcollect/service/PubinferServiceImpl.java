package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.mapper.PubinferMapper;
import edu.qingtai.pubandcollect.util.QuireDate;
import edu.qingtai.pubandcollect.util.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class PubinferServiceImpl implements PubinferService{
    private PubinferMapper pubinferMapper;

    @Autowired
    public PubinferServiceImpl(final PubinferMapper pubinferMapper){
        this.pubinferMapper = pubinferMapper;
    }

    @Override
    public void saveInfer(Pubinfer pubinfer, List<MultipartFile> fileList){
        String images = UploadImage.uploadImagesOfInfer(fileList, pubinfer.getOpenid());
        pubinfer.setImages(images);
        pubinfer.setInserttime(QuireDate.currentDate());
        pubinfer.setUuid(UUID.randomUUID().toString().replace("-", ""));
        int status = pubinferMapper.insert(pubinfer);
        System.out.println(status);
    }
}
