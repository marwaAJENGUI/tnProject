package tn.esprit.spring.sevice.impl;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.clapper.util.misc.MIMETypeUtil;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tn.esprit.spring.entity.DBFile;
import tn.esprit.spring.repository.DBFileRepository;
import tn.esprit.spring.service.interfaces.IDBFileStorageService;

@Service
public class DBFileStorageService implements IDBFileStorageService {

	    @Autowired
	    private DBFileRepository dbFileRepository;
		private static final String UPLOADED_FOLDER = System.getProperty("user.dir");
		Logger log = LoggerFactory.getLogger(this.getClass());
		
		@Override
	    public String storeFile(MultipartFile file,Long fileName) throws IOException {
	        // Normalize file name
	        DBFile dbFile = new DBFile(fileName, file.getContentType(),  file.getBytes());
	         dbFileRepository.save(dbFile);
	         return "file saved";
	    }
		
	    @Override
	    public String storeFile (UploadedFile file,Long fileName) {
	        DBFile dbFile = new DBFile(fileName, file.getContentType(),  file.getContents());
	        dbFileRepository.save(dbFile);
	         return "file saved";
	    }
		
		@Override
	    public Optional<DBFile> getFile(Long  fileName) {
	        return dbFileRepository.findById(fileName);
	    }
		
		@Override
	    public Boolean storeLocalFile( MultipartFile file) {
			//upload image
					if (!file.isEmpty()) {   
						            // Get the file and save it somewhere
						            byte[] bytes;
									try {
										bytes = file.getBytes();
							            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
							            Files.write(path, bytes);
							            return true;
									} catch (IOException e) {
								           // byte[] bytes = file.getBytes();
								        	log.warn("Failed to upload attachment", e);
								        	return false;
								        }
					}
					return false;
			}
		
		@Override
	    public Boolean storeLocalFile(UploadedFile  file) {
			//upload image
					if (file.getSize()!=0L) {   
						            // Get the file and save it somewhere
						            byte[] bytes;
									try {
										bytes = file.getContents();
							            Path path = Paths.get(UPLOADED_FOLDER + file.getFileName());
							            Files.write(path, bytes);
							            return true;
									} catch (IOException e) {
								           // byte[] bytes = file.getBytes();
								        	log.warn("Failed to upload attachment", e);
								        	return false;
								        }
					}
					return false;
			}
				
		@Override
		public String getBarCode(String fileName){
			//get the barCode value from the image
			InputStream barCodeInputStream;
			try {
				barCodeInputStream = new FileInputStream(UPLOADED_FOLDER + fileName);
				BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Reader reader = new MultiFormatReader();
				Result result = reader.decode(bitmap);
				return result.getText();
	
			} catch (FileNotFoundException e) {
				//barCodeInputStream = new FileInputStream(file.);
				e.printStackTrace();
			} catch (IOException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (NotFoundException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (ChecksumException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (FormatException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			}
		return "invalid file";
	}
	
		@Override
		public StreamedContent getStreamedFile(Long barCode) {
			System.out.println("Service barCode="+barCode);
			if (dbFileRepository.findById(barCode).isPresent()) {
				DBFile file=dbFileRepository.findById(barCode).get();
				String imageName=Long.toString(barCode);
				byte[] imageByte=  file.getData();
				imageName= imageName+"."+MIMETypeUtil.fileExtensionForMIMEType(file.getFileType());
				return new DefaultStreamedContent(new ByteArrayInputStream(imageByte),file.getFileType(),imageName);
			}else return null;
		}
		
		@Override
		public void removeFile(Long fileName) {
			Optional <DBFile> file=dbFileRepository.findById(fileName);
			if ( file.isPresent()) {
				dbFileRepository.delete(file.get());				
			}
		}
}

