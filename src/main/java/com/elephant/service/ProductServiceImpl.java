package com.elephant.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.elephant.constant.StatusCode;
import com.elephant.dao.CategoryRepository;
import com.elephant.dao.ProductDao;
import com.elephant.dao.ProductRepository;
import com.elephant.domain.Category;
import com.elephant.domain.ProductDomain;
import com.elephant.mapper.entities.CategoryMapper;
import com.elephant.mapper.entities.ProductMapper;
import com.elephant.model.ProductModel;
import com.elephant.model.ProductModel1;
import com.elephant.response.Response;
import com.elephant.utils.CommonUtils;
import com.elephant.utils.DateUtility;




@EnableJpaRepositories
@Service("uploadService")
public class ProductServiceImpl implements ProductService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	
	@Autowired
	ProductDao uploadproductdao;
	
	
	@Autowired
	ProductMapper uploadproductmapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

//	@Override
//	public List<ProductModel> getProductByCatagory(String categoryName, String colors, Float discount, Double length,
//			Double price, String materialType, String fabricPurity, String pattern, String border, String borderType,
//			String zariType, String blouse, String blouseColor,Double blouseLength) throws Exception {
//		try {
//			
//			List<ProductDomain> uploadProductdomain = uploadproductdao.getProductByCatagory( categoryName,  colors, discount, length, price, materialType, fabricPurity,
//					  pattern, border,borderType,zariType,blouse,blouseColor,blouseLength);
//			return uploadproductmapper.entityList(uploadProductdomain);
//		} catch (Exception ex) {
//			logger.info("Exception getAttendanceViewByStandard:", ex);
//		}
//		return null;
//	}

	/*----------------------------------Add Product-------------------------------------*/

	
	@Override
	public Response addproduct(ProductModel model,String categoryName) throws Exception {
		Response response=CommonUtils.getResponseObject("upload products");
	
		try {
			ProductDomain domain=new ProductDomain();
			BeanUtils.copyProperties(model, domain);
			domain.setProductId(CommonUtils.generateRandomId());
			domain.setModifiedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
            domain.setUploadDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
			domain.setActive(true);
			
			List<Category> domainList= categoryRepository.findAll();
			
			for(int i=0;i<domainList.size();i++) {
				Category categoryDomain1=categoryRepository.findByCategoryName(categoryName);
				if((categoryName.equals(domainList.get(i).getCategoryName())) && (categoryDomain1.isActive()==true)) {
					//Category categoryDomain1=categoryrepository.findByCategoryName(categoryName);
					domain.setCategory(categoryDomain1);
					productRepository.save(domain);
					response.setStatus(StatusCode.SUCCESS.name());
					response.setMessage("product upload success");
					return response;
					
				}
				
			}
			response.setStatus(StatusCode.ERROR.name());
			response.setMessage("Category is not found");
			return response;}
			/*UploadProductDomain update=new UploadProductDomain();
            	BeanUtils.copyProperties(model, update);
            	Category category=categoryrepository.findByCategoryName(categoryName);
            	if(category!=null && category.isActive()==true) {
					update.setCategory(category);
				
	            update.setProductId(CommonUtils.generateRandomId());
	            update.setSku(model.getSku());
	            update.setColors(model.getColors());
	            update.setDiscount(model.getDiscount());
	            update.setOccassion(model.getOccassion());
	            update.setPrice(model.getPrice());
                update.setQuantity(model.getQuantity());
                update.setActive(true);
                update.setModifiedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
                update.setUploadDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
                update.setMaterialType(model.getMaterialType());
                update.setCollectionDesc(model.getCollectionDesc());
                
                update.setFabricPurity(model.getFabricPurity());
                update.setPattern(model.getPattern());
                update.setBorder(model.getBorder());
                update.setBorderType(model.getBorderType());
                update.setZariType(model.getZariType());
                update.setLength(model.getLength());
                update.setBlouse(model.getBlouse());
                update.setBlouseColor(model.getBlouseColor());
                update.setBlouseLength(model.getBlouseLength());
                
                update.setMainImageUrl(model.getMainImageUrl());
                update.setOtherImageUrls(model.getOtherImageUrls());
                
                Response response1 = uploadproductdao.addproduct(update);
    			return response1;
	           
    		
    		}
				else {
					Response response2 = CommonUtils.getResponseObject("Category Name Not Available");
					response.setStatus(StatusCode.ERROR.name());
					return response2;
				
			}}*/
   		catch (Exception ex) {
    			logger.info("Exception AddProductService:" + ex.getMessage());
    		}
    		return null;
    			
    		}
	
	/*----------------------------------Update Product -------------------------------------*/


	@Override
	public Response updateProduct(ProductModel updateproduct) throws Exception {
		try {
			ProductDomain upd = new ProductDomain();
			BeanUtils.copyProperties(updateproduct, upd);

			Response response = uploadproductdao.updateProduct(upd);
			return response;

		} catch (Exception ex) {
			logger.info("Exception update product Service:" + ex.getMessage());
		}
		return null;
	}
	/*----------------------------------Upload Excel File-------------------------------------*/


	@Override
	public void exportExcel(MultipartFile file,String categoryName) throws Exception {

		XSSFWorkbook workbook = null;
			try {
				
				InputStream inputStream = file.getInputStream(); 	
				workbook = new XSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) 
			{
				Row row=rowIterator.next();
				if(row.getRowNum()==0)
					continue;

				ProductDomain uploadProduct = new ProductDomain();
				
				Iterator<Cell> cellIterator = row.cellIterator();
				int index;
				while(cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();

					index= cell.getColumnIndex();
					System.out.println(index);
					
					ProductDomain update=new ProductDomain();
	            	Category category=categoryRepository.findByCategoryName(categoryName);
	            	if(category!=null && category.isActive()==true) {
						update.setCategory(category);
					switch(index+1) 
					{
					
					case 1:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setProductId(CommonUtils.generateRandomId());
						uploadProduct.setUploadDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
						uploadProduct.setModifiedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
						uploadProduct.setActive(true);
						uploadProduct.setBlouse(cell.getStringCellValue());break;
					case 2:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setBlouseColor(cell.getStringCellValue());break;

					case 3:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setBlouseLength((double) cell.getNumericCellValue());break;
						
					case 4:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setBorder(cell.getStringCellValue());break;
						
					case 5:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setBorderType(cell.getStringCellValue());break;
						
					case 6:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setCollectionDesc(cell.getStringCellValue());break;
						
					case 7:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setColors(cell.getStringCellValue());break;
						
					case 8:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setDiscount((float)cell.getNumericCellValue());break;
							
						
					case 9:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setFabricPurity(cell.getStringCellValue());break;
						
								
					case 10:
						cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
						uploadProduct.setActive(true);break;
					
					case 11:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setLength((double)(cell.getNumericCellValue()));break;
						
					case 12:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setMainImageUrl(cell.getStringCellValue());break;
						
					case 13:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setMaterialType(cell.getStringCellValue());break;		
						
					case 14:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setOccassion(cell.getStringCellValue());break;
						
					/*case 15:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setOtherImageUrls(cell.getStringCellValue());break;*/
															
					case 17:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setPattern(cell.getStringCellValue());break;
																
					case 18:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setPrice((double)cell.getNumericCellValue());break;
						
					case 19:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setInStock((long)(cell.getNumericCellValue()));break;
					
					case 20:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setSku(cell.getStringCellValue());break;
				
									
								
					case 28:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setZariType(cell.getStringCellValue());break;
						
					
					}
					
					uploadproductdao.save(uploadProduct);
					
				}
				
			}
	}}
		
		
		
	/*@Override
	public List<UploadProductDomain> uploadFile(MultipartFile multipartFile) throws IOException {
		

		File file = convertMultiPartToFile(multipartFile);

		List<UploadProductDomain> mandatoryMissedList = new ArrayList<UploadProductDomain>();

		try (Reader reader = new FileReader(file);) {
			CsvToBean<UploadProductDomain> csvToBean = new CsvToBeanBuilder<UploadProductDomain>(reader).withType(UploadProductDomain.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<UploadProductDomain> productList = csvToBean.parse();

			Iterator<UploadProductDomain> questionListClone = productList.iterator();

			while (questionListClone.hasNext()) {

				UploadProductDomain product = questionListClone.next();
				System.out.println(product.getProductId());
				if(product.getProductId() == null) {
				mandatoryMissedList.add(product);
				questionListClone.remove();
				}
				
			}

			uploadproductdao.uploadFile(productList);
			
		}
		catch(Exception e) {
			logger.info("exception"+ e.getMessage());
			
		}
		return mandatoryMissedList;
		
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	@Override
	public void exportExcel(MultipartFile file,String categoryName) throws Exception {

		XSSFWorkbook workbook = null;
			try {
				
				InputStream inputStream = file.getInputStream(); 	
				workbook = new XSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) 
			{
				Row row=rowIterator.next();
				if(row.getRowNum()==0)
					continue;

				UploadProductDomain uploadProduct = new UploadProductDomain();
				
				Iterator<Cell> cellIterator = row.cellIterator();
				int index;
				while(cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();

					index= cell.getColumnIndex();
					System.out.println(index);
					switch(index+1) 
					{
					
					case :cell.setCellType(Cell.CELL_TYPE_STRING);
					
					uploadProduct.setProductId(CommonUtils.generateRandomId());
					uploadProduct.setUploadDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
					uploadProduct.setModifiedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
					uploadProduct.setActive(true);
					uploadProduct.setBlouseColor(cell.getStringCellValue());
					uploadProduct.setBorder(cell.getStringCellValue());
					uploadProduct.setBorderType(cell.getStringCellValue());
					uploadProduct.setCollectionDesc(cell.getStringCellValue());
					uploadProduct.setColors(cell.getStringCellValue());
					uploadProduct.setFabricPurity(cell.getStringCellValue());
					uploadProduct.setMainImageUrl(cell.getStringCellValue());
					uploadProduct.setMaterialType(cell.getStringCellValue());
					uploadProduct.setOccassion(cell.getStringCellValue());
					uploadProduct.setOtherImageUrl1(cell.getStringCellValue());
					uploadProduct.setOtherImageUrl2(cell.getStringCellValue());
					uploadProduct.setPattern(cell.getStringCellValue());
					uploadProduct.setSku(cell.getStringCellValue());
					uploadProduct.setSubImageUrl1(cell.getStringCellValue());
					uploadProduct.setSubImageUrl2(cell.getStringCellValue());
					uploadProduct.setSubImageUrl3(cell.getStringCellValue());
					uploadProduct.setSubImageUrl4(cell.getStringCellValue());
					uploadProduct.setSubImageUrl5(cell.getStringCellValue());
					uploadProduct.setSubImageUrl6(cell.getStringCellValue());
					uploadProduct.setTypes(cell.getStringCellValue());
					uploadProduct.setZariType(cell.getStringCellValue());
					uploadProduct.setBlouse(cell.getStringCellValue());break;
					
					
					
					case 2:cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					uploadProduct.setQuantity((long)(cell.getNumericCellValue()));
					uploadProduct.setPrice((double)cell.getNumericCellValue());
					uploadProduct.setLength((double)(cell.getNumericCellValue()));
					uploadProduct.setDiscount((float)cell.getNumericCellValue());
					uploadProduct.setBlouseLength((double) cell.getNumericCellValue());break;
					
					
					case 1:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setProductId(CommonUtils.generateRandomId());
						uploadProduct.setUploadDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
						uploadProduct.setModifiedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
						uploadProduct.setActive(true);
						uploadProduct.setBlouse(cell.getStringCellValue());break;
					case 2:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setBlouseColor(cell.getStringCellValue());break;

					case 3:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setBlouseLength((double) cell.getNumericCellValue());break;
						
					case 4:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setBorder(cell.getStringCellValue());break;
						
					case 5:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setBorderType(cell.getStringCellValue());break;
						
					case 6:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setCollectionDesc(cell.getStringCellValue());break;
						
					case 7:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setColors(cell.getStringCellValue());break;
						
					case 8:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setDiscount((float)cell.getNumericCellValue());break;
							
						
					case 9:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setFabricPurity(cell.getStringCellValue());break;
						
								
					case 10:
						cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
						uploadProduct.setActive(true);break;
					
					case 11:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setLength((double)(cell.getNumericCellValue()));break;
						
					case 12:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setMainImageUrl(cell.getStringCellValue());break;
						
					case 13:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setMaterialType(cell.getStringCellValue());break;		
						
					case 14:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setOccassion(cell.getStringCellValue());break;
						
					case 15:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setOtherImageUrls(cell.getStringCellValue());break;
															
					case 17:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setPattern(cell.getStringCellValue());break;
																
					case 18:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setPrice((double)cell.getNumericCellValue());break;
						
					case 19:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						uploadProduct.setQuantity((long)(cell.getNumericCellValue()));break;
					
					case 20:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setSku(cell.getStringCellValue());break;
				
									
								
					case 28:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						uploadProduct.setZariType(cell.getStringCellValue());break;
							
					
					}
					
					uploadproductdao.save(uploadProduct);
					
				}
				
			}
	}*/
		
		

	
	
	/*---------------------------------delete product by id-----------------------------------*/
	@Override
	public Response deleteproduct(String productId) throws Exception {
try {
			return uploadproductdao.deleteproduct(productId);
		} catch (Exception e) {
			logger.info("Exception deleteproduct:", e);
			return null;
		}
	}
	
	@Override
	public Response deleteProduct(String productId, boolean isActive) throws Exception {
		try {
			return uploadproductdao.deleteProduct(productId,isActive);
		} catch (Exception e) {
			logger.info("Exception deleteproduct:", e);
			return null;
		}
	}
	
	/*----------------------------------Get Product By Id-------------------------------------*/

	

	@Override
	public ProductModel getProductById(String productId) throws Exception {
		try {
			
			System.out.println(productId);
			System.out.println(productId);
			System.out.println(productId);
			System.out.println(productId);
			System.out.println(productId);
			
			ProductDomain up = uploadproductdao.getProductById(productId);
			System.out.println(productId);
			System.out.println(productId);
			System.out.println(productId);
			System.out.println(productId);
			System.out.println(productId);
			ProductModel productModel = new ProductModel();
			if (up == null)
				return null;
			BeanUtils.copyProperties(up, productModel);
			return productModel;
		} catch (Exception e) {
			logger.info("Exception getUser:", e.getMessage());
			return null;
		}
	}

	
	@Override
	public List<ProductModel> getProductByDiscount(float discount) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByDiscount(discount);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getproductDiscount:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByColor(String colors) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByColor(colors);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getproductType:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByOccassion(String occassion) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByOccassion(occassion);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByOccassion:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByPrice(double price) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByPrice(price);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getproductType:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByPriceRange(double minPrice,double maxPrice) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByPriceRange(minPrice,maxPrice);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getproductRange:", e);
		
		}
		return null;
	}
	
	@Override
	public List<ProductModel> getProductsByPriceRange(String categoryName,double minPrice,double maxPrice) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductsByPriceRange(categoryName,minPrice,maxPrice);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getproductRange:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByMaterialType(String materialType) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByMaterialType(materialType);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByMaterialType:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByBorder(String border) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByBorder(border);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByBorder:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByBorderType(String borderType) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByBorderType(borderType);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByBorderType:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByZariType(String zariType) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByZariType(zariType);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByZariType:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByBlouse(String blouse) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByBlouse(blouse);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByBlouse:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByPattern(String pattern) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByPattern(pattern);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByPattern:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByFabricPurity(String fabricPurity) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByFabricPurity(fabricPurity);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByFabricPurity:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByBlouseColor(String blouseColor) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByBlouseColor(blouseColor);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByBlouseColor:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByBlouseLength(Double blouseLength) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByBlouseLength(blouseLength);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByBlouseLength:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductBySareeLength(Double length) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductBySareeLength(length);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductBySareeLength:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProducts() throws Exception {
		try {
			List<ProductDomain> product=uploadproductdao.getProducts();
			return uploadproductmapper.entityList(product);
		}
		catch(Exception e) {logger.info("Exception getProducts:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByFilterDiscount(float discount) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByFilterDiscount(discount);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getproductDiscount:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getPriceSortingLowToHigh() throws Exception {
		try {
			List<ProductDomain> product=uploadproductdao.getPriceSortingLowToHigh();
			return uploadproductmapper.entityList(product);
		}
		catch(Exception e) {logger.info("Exception getProducts:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getPriceSortingHighToLow() throws Exception {
		try {
			List<ProductDomain> product=uploadproductdao.getPriceSortingHighToLow();
			return uploadproductmapper.entityList(product);
		}
		catch(Exception e) {logger.info("Exception getProducts:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByTypesSortingPriceLowToHigh(String types) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByTypesSortingPriceLowToHigh(types);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByCategoryNameSortingPriceLowToHigh:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductByTypesSortingPriceHighToLow(String types) throws Exception {
		try {
			List<ProductDomain> upload=uploadproductdao.getProductByTypesSortingPriceHighToLow(types);
			return uploadproductmapper.entityList(upload);
		}
		catch(Exception e) {logger.info("Exception getProductByCategoryNameSortingPriceHighToLow:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getByNewProducts() throws Exception {
		try {
			List<ProductDomain> product=uploadproductdao.getByNewProducts();
			return uploadproductmapper.entityList(product);
		}
		catch(Exception e) {logger.info("Exception getByNewProducts:", e);
		
		}
		return null;
	}

	@Override
	public List<ProductModel> getProductBycategoryId(String categoryId) throws IOException {
	try {
	List<ProductDomain> up = uploadproductdao.getProductBycategoryId(categoryId);
	return uploadproductmapper.entityList(up);
		} catch (Exception e) {
			logger.info("Exception getUser:", e.getMessage());
			return null;
		}
	}

	@Override
	public ByteArrayResource exportExcelHeaders(String fileName) throws IOException{
		String[] columnshead = {"sku","quantity","saree_length","pattern","fabric_purity","border","border_type","zari_type","material_type","price", "discount", "blouse","blouse_color","blouse_length","saree_colors","collection_desc","occassion","main_imageUrl","otherImageUrls"};
		// List<UploadProductDomain> domain =  new ArrayList<>();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Workbook workbook = new XSSFWorkbook();
		 //CreationHelper createHelper = workbook.getCreationHelper();
		XSSFSheet  sheet = (XSSFSheet) workbook.createSheet("Product_Upload_Template");
		 
		 Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());
	        
	     CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	        headerCellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
	       // headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);	       
	        headerCellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
	        
	        Row headerRow = sheet.createRow(0);
	       
	        
	        for(int i = 0; i < columnshead.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columnshead[i]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        
	        for(int i = 0; i < columnshead.length; i++) {
	            sheet.autoSizeColumn(i);
	        }
	        //String d="G:\\Mahesh\\Template.xlsx";
	        FileOutputStream fileOut = new FileOutputStream("Template.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
	        fileOut.flush();
			return null;
		
	}

	@Override
	public InputStream exportExcel() throws IOException {
		String[] columnshead = {"sku","quantity","saree_length","pattern","fabric_purity","border","border_type","zari_type","material_type","price", "discount", "blouse","blouse_color","blouse_length","saree_colors","collection_desc","occassion","main_imageUrl","otherImageUrls"};
		// List<UploadProductDomain> domain =  new ArrayList<>();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Workbook workbook = new XSSFWorkbook();
		 //CreationHelper createHelper = workbook.getCreationHelper();
		XSSFSheet  sheet = (XSSFSheet) workbook.createSheet("Product_Upload_Template");
		 
		 Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());
	        
	     CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	        headerCellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
	       // headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);	       
	        headerCellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
	        
	        Row headerRow = sheet.createRow(0);
	       
	        
	        for(int i = 0; i < columnshead.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columnshead[i]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        
	        for(int i = 0; i < columnshead.length; i++) {
	            sheet.autoSizeColumn(i);
	        }
	        //String d="G:\\Mahesh\\Template.xlsx";
	        FileOutputStream fileOut = new FileOutputStream("Template.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
			return null;
	}

	@Override
	public List<ProductModel> getProductByCatagory1(ProductModel1 pm1) {
		try {
			
			List<ProductDomain> uploadProductdomain = uploadproductdao.getProductByCatagory1(pm1);
			return uploadproductmapper.entityList(uploadProductdomain);
		} catch (Exception ex) {
			logger.info("Exception getAttendanceViewByStandard:", ex);
		}
		return null;
	}

	
}
	
	
	
	