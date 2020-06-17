package tn.esprit.spring.service.interfaces;

import java.io.IOException;
import java.util.Optional;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.DBFile;


public interface IDBFileStorageService {

	public String storeFile(UploadedFile file, Long fileName);

	public String storeFile(MultipartFile file, Long fileName) throws IOException;

	public Optional<DBFile> getFile(Long fileName);

	public Boolean storeLocalFile(MultipartFile file);

	public Boolean storeLocalFile(UploadedFile file);

	public String getBarCode(String fileName);

	public StreamedContent getStreamedFile(Long barCode);

	public void removeFile(Long fileName);

}
